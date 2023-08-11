package com.amplifierconsultancy.ugmk.service;

import com.amplifierconsultancy.ugmk.config.props.PrimaveraProps;
import com.amplifierconsultancy.ugmk.dto.WbsDto;
import com.amplifierconsultancy.ugmk.mapper.WbsMapper;
import com.primavera.ServerException;
import com.primavera.integration.client.ClientException;
import com.primavera.integration.client.EnterpriseLoadManager;
import com.primavera.integration.client.RMIURL;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.client.bo.object.WBS;
import com.primavera.integration.common.DatabaseInstance;
import com.primavera.integration.network.NetworkException;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@Slf4j
public class WbsService {
    public static final String PRIMAVERA_BOOTSTRAP_HOME = "primavera.bootstrap.home";

    @NonNull
    private final PrimaveraProps primaveraProps;

    @NonNull
    private final WbsMapper wbsMapper;

    @PostConstruct
    public void init() {
        System.setProperty(PRIMAVERA_BOOTSTRAP_HOME, primaveraProps.getBootstrapHome());
    }

    public List<WbsDto> loadWbsList() {
        final DatabaseInstance dbInstance = tryGetDbInstance();

        try (Session session = Session.login(getRmiUrl(), dbInstance.getDatabaseId(), primaveraProps.getUsername(), primaveraProps.getPassword())) {
            List<WbsDto> wbsList = new ArrayList<>();
            EnterpriseLoadManager elm = session.getEnterpriseLoadManager();
            BOIterator<Project> boi = elm.loadProjects(
                    new String[] {
                            "ObjectId", // ID проекта
                            "Name", // Наименование проекта
                            "WBSHierarchyLevels", // Level WBS
                            "WBSCodeSeparator",
                            "OBSName",
//                            "Description",
//                            "LocationName",
                            "ActivityIdPrefix",
                            "Id", // Код проекта
                            "ActivityDefaultCalendarName",
                            "AddedBy",
//                            "ContractManagementGroupName",
//                            "ContractManagementProjectName",
//                            "LocationObjectId",
                            "ParentEPSName",
//                            "ResourceName",
//                            "UnifierProjectName",
                            "WBSObjectId", // ID WBS (id узла в дереве)
                            "GUID", // GUID
                            "Status",
                            "StartDate",
                            "FinishDate",
                            "PlannedStartDate",
                            "SummaryPlannedFinishDate",
                            "SummaryActualStartDate",
                            "SummaryActualFinishDate"
                    },
                    "ObjectId = 368",
                    "ObjectId asc"
            );

            while (boi.hasNext()) {
                Project proj = boi.next();
                BOIterator<WBS> boiWbs = proj.loadAllWBS(
                        new String[] {
                                "Name",
                                "Code",
                                "GUID",
                                "OBSName",
                                "ParentObjectId",
                                //"StatusReviewerName",
                                //"WBSSpread",
                                "ObjectId",
                                "WBSCategoryObjectId",
                                "Status",
                                "StartDate",
                                "FinishDate",
                                "SummaryActualStartDate",
                                "SummaryActualFinishDate"
                        },
                        "ParentObjectId = " + proj.getWBSObjectId(),
                        "ObjectId asc"
                );

                while (boiWbs.hasNext()) {
                    WBS work = boiWbs.next();
                    wbsList.add(mapWbs(work));
                }
            }

            return wbsList;
        } catch (ServerException | NetworkException | ClientException e) {
            throw new IllegalStateException("Cannot load WBS list: " + e.getMessage());
        }
    }

    private DatabaseInstance tryGetDbInstance() {
        try {
            DatabaseInstance[] dbInstances = Session.getDatabaseInstances(getRmiUrl());
            if (dbInstances.length == 0)
                throw new IllegalStateException("There are no Primavera database instances");

            return dbInstances[0];
        } catch (ServerException | NetworkException | ClientException e) {
            throw new IllegalStateException("Cannot get Primavera database instance: " + e.getMessage());
        }
    }

    private String getRmiUrl() {
        return RMIURL.getRmiUrl(primaveraProps.getMode(), primaveraProps.getHost(), primaveraProps.getPort());
    }

    private WbsDto mapWbs(WBS work){
        try {
            WbsDto wbsDto = wbsMapper.wbsToWbsDto(work);
//            System.out.println(
//                    work.getObjectId().toString() + ", " + // ID объекта WBS
//                    work.getParentObjectId() + ", " + // ID объекта parent WBS
//                    work.getName() +  ", " + // Наименование объекта
//                    work.getGUID() + ", " // GUID
//            );
//
//            BOIterator<Activity> boiActivity = work.loadActivities(
//                    new String[] {
//                            "Name",
//                            "WBSCode",
//                            "WBSName",
//                            "WBSNamePath",
//                            "WBSObjectId",
//                            "Id",
//                            "PlannedStartDate",
//                            "PlannedFinishDate",
//                            "ActualStartDate",
//                            "ActualFinishDate"
//                    },
//                    null,
//                    null
//            );
//
//            while (boiActivity.hasNext()) {
//                Activity act = boiActivity.next();
//                System.out.println(
//                        act.getId() + ", " +
//                        act.getName() + ", " +
//                        act.getPlannedStartDate().toString() + ", " +
//                        act.getPlannedFinishDate().toString() + ", " +
//                        act.getActualStartDate() + ", " +
//                        act.getActualFinishDate()
//                );
//
//            }

            BOIterator<WBS> boiWbsChildren = work.loadWBSChildren(
                    new String[] {
                            "Name",
                            "Code",
                            "ObjectId",
                            "ParentObjectId",
                            "GUID",
                            "StartDate",
                            "FinishDate",
                            "SummaryActualStartDate",
                            "SummaryActualFinishDate"
                    },
                    null,
                    null
            );

            while (boiWbsChildren.hasNext()) {
                WBS childWork = boiWbsChildren.next();
                WbsDto childWbsDto = mapWbs(childWork); // recursive call
                wbsDto.addChild(childWbsDto);
            }

            return wbsDto;
        } catch (ServerException | NetworkException | BusinessObjectException e) {
            throw new IllegalStateException("Cannot load WBS child list: " + e.getMessage());
        }
    }
}
