package com.amplifierconsultancy.ugmk.service;

import com.amplifierconsultancy.ugmk.config.props.PrimaveraProps;
import com.amplifierconsultancy.ugmk.dto.*;
import com.amplifierconsultancy.ugmk.mapper.ActivityMapper;
import com.amplifierconsultancy.ugmk.mapper.ProjectMapper;
import com.amplifierconsultancy.ugmk.mapper.WbsMapper;
import com.primavera.ServerException;
import com.primavera.integration.client.ClientException;
import com.primavera.integration.client.EnterpriseLoadManager;
import com.primavera.integration.client.RMIURL;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Activity;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.client.bo.object.WBS;
import com.primavera.integration.common.DatabaseInstance;
import com.primavera.integration.network.NetworkException;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Data
@Slf4j
public class ProjectService {
    private static final String PRIMAVERA_BOOTSTRAP_HOME = "primavera.bootstrap.home";
    private static final String ASC = "ASC";
    private static final String ID = "Id";
    private static final String OBJECT_ID = "ObjectId";
    private static final String PARENT_OBJECT_ID = "ParentObjectId";
    private static final String GUID = "GUID";
    private static final String CODE = "Code";
    private static final String NAME = "Name";
    private static final String OBS_OBJECT_ID = "OBSObjectId";
    private static final String OBS_NAME = "OBSName";
    private static final String WBS_OBJECT_ID = "WBSObjectId";
    private static final String WBS_CODE = "WBSCode";
    private static final String WBS_NAME = "WBSName";
    private static final String WBS_NAME_PATH = "WBSNamePath";
    private static final String WBS_SPREAD = "WBSSpread";
    private static final String WBS_CATEGORY_OBJECT_ID = "WBSCategoryObjectId";
    private static final String WBS_HIERARCHY_LEVELS = "WBSHierarchyLevels";
    private static final String WBS_CODE_SEPARATOR = "WBSCodeSeparator";
    private static final String STATUS = "Status";
    private static final String STATUS_REVIEWER_NAME = "StatusReviewerName";
    private static final String DESCRIPTION = "Description";
    private static final String LOCATION_OBJECT_ID = "LocationObjectId";
    private static final String LOCATION_NAME = "LocationName";
    private static final String RESOURCE_NAME = "ResourceName";
    private static final String UNIFIER_PROJECT_NAME = "UnifierProjectName";
    private static final String CONTRACT_MANAGEMENT_GROUP_NAME = "ContractManagementGroupName";
    private static final String CONTRACT_MANAGEMENT_PROJECT_NAME = "ContractManagementProjectName";
    private static final String ACTIVITY_ID_PREFIX = "ActivityIdPrefix";
    private static final String ACTIVITY_DEFAULT_CALENDAR_NAME = "ActivityDefaultCalendarName";
    private static final String PARENT_EPS_NAME = "ParentEPSName";
    private static final String ADDED_BY = "AddedBy";
    private static final String START_DATE = "StartDate";
    private static final String FINISH_DATE = "FinishDate";
    private static final String PLANNED_START_DATE = "PlannedStartDate";
    private static final String PLANNED_FINISH_DATE = "PlannedFinishDate";
    private static final String ACTUAL_START_DATE = "ActualStartDate";
    private static final String ACTUAL_FINISH_DATE = "ActualFinishDate";
    private static final String SUMMARY_PLANNED_START_DATE = "SummaryPlannedStartDate";
    private static final String SUMMARY_PLANNED_FINISH_DATE = "SummaryPlannedFinishDate";
    private static final String SUMMARY_ACTUAL_START_DATE = "SummaryActualStartDate";
    private static final String SUMMARY_ACTUAL_FINISH_DATE = "SummaryActualFinishDate";

    private static final String[] PROJECT_FIELDS = {
            ID, // Код проекта
            OBJECT_ID, // ID проекта
            GUID, // GUID
            NAME, // Наименование проекта
            OBS_OBJECT_ID,
            OBS_NAME,
            WBS_OBJECT_ID, // ID WBS (ID узла в дереве)
            WBS_HIERARCHY_LEVELS, // Level WBS
            WBS_CODE_SEPARATOR,
            STATUS,
            ACTIVITY_ID_PREFIX,
            ACTIVITY_DEFAULT_CALENDAR_NAME,
            PARENT_EPS_NAME,
//            DESCRIPTION,
//            LOCATION_OBJECT_ID,
//            LOCATION_NAME,
//            RESOURCE_NAME,
//            UNIFIER_PROJECT_NAME,
//            CONTRACT_MANAGEMENT_GROUP_NAME,
//            CONTRACT_MANAGEMENT_PROJECT_NAME,
            ADDED_BY,
            START_DATE,
            FINISH_DATE,
            PLANNED_START_DATE,
            SUMMARY_PLANNED_FINISH_DATE,
            SUMMARY_ACTUAL_START_DATE,
            SUMMARY_ACTUAL_FINISH_DATE
    };

    private static final String[] WBS_FIELDS = {
            OBJECT_ID,
            PARENT_OBJECT_ID,
            GUID,
            CODE,
            NAME,
            OBS_OBJECT_ID,
            OBS_NAME,
            WBS_CATEGORY_OBJECT_ID,
//            WBS_SPREAD,
            STATUS,
//            STATUS_REVIEWER_NAME,
            START_DATE,
            FINISH_DATE,
            SUMMARY_ACTUAL_START_DATE,
            SUMMARY_ACTUAL_FINISH_DATE
    };

    private static final String[] ACTIVITY_FIELDS = {
            ID,
            NAME,
            WBS_OBJECT_ID,
            WBS_CODE,
            WBS_NAME,
            WBS_NAME_PATH,
            PLANNED_START_DATE,
            PLANNED_FINISH_DATE,
            ACTUAL_START_DATE,
            ACTUAL_FINISH_DATE
    };

    @NonNull
    private final PrimaveraProps primaveraProps;

    @NonNull
    private final ProjectMapper projectMapper;

    @NonNull
    private final ActivityMapper activityMapper;

    @NonNull
    private final WbsMapper wbsMapper;

    @PostConstruct
    public void init() {
        System.setProperty(PRIMAVERA_BOOTSTRAP_HOME, primaveraProps.getBootstrapHome());
    }

    public Optional<ProjectDto> loadProject(String id) {
        final DatabaseInstance dbInstance = tryGetDbInstance();

        try (Session session = Session.login(getRmiUrl(), dbInstance.getDatabaseId(), primaveraProps.getUsername(), primaveraProps.getPassword())) {
            List<ProjectDto> projects = new ArrayList<>();
            EnterpriseLoadManager elm = session.getEnterpriseLoadManager();

            log.info("Start loading...");
            BOIterator<Project> boi = elm.loadProjects(
                    PROJECT_FIELDS,
                    ID + " = " + "'" + id + "'",
                    OBJECT_ID + " " + ASC
            );

            while (boi.hasNext()) {
                Project proj = boi.next();
                ProjectDto projectDto = projectMapper.toProjectDto(proj);
                projects.add(projectDto);

                BOIterator<WBS> boiWbs = proj.loadAllWBS(
                        WBS_FIELDS,
                        PARENT_OBJECT_ID + " = " + proj.getWBSObjectId(),
                        OBJECT_ID + " " + ASC
                );

                while (boiWbs.hasNext()) {
                    WBS work = boiWbs.next();
                    projectDto.addWbs(mapWbs(work, proj.getId() + '.'));
                }
            }
            log.info("Loading completed.");

            return  (projects.isEmpty()) ? Optional.empty() : Optional.of(projects.get(0));
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

    private WbsDto mapWbs(WBS work, String prefix){
        try {
            WbsDto wbsDto = wbsMapper.toWbsDto(work, prefix);

            // Handle activities
            BOIterator<Activity> boiActivity = work.loadActivities(
                    ACTIVITY_FIELDS,
                    null,
                    null
            );

            while (boiActivity.hasNext()) {
                Activity act = boiActivity.next();
                ActivityDto activityDto = activityMapper.toActivityDto(act);
                wbsDto.addActivity(activityDto);
            }

            // Handle children WBS
            BOIterator<WBS> boiWbsChildren = work.loadWBSChildren(
                    WBS_FIELDS,
                    null,
                    null
            );

            while (boiWbsChildren.hasNext()) {
                WBS childWork = boiWbsChildren.next();
                WbsDto childWbsDto = mapWbs(childWork, prefix + work.getCode() + '.'); // recursive call
                wbsDto.addChild(childWbsDto);
            }

            return wbsDto;
        } catch (ServerException | NetworkException | BusinessObjectException e) {
            throw new IllegalStateException("Cannot load activities or WBS child list: " + e.getMessage());
        }
    }

    public Optional<FlatProjectDto> loadFlatProject(String id) {
        Optional<ProjectDto> projectOpt = loadProject(id);
        if (!projectOpt.isPresent())
            return Optional.empty();

        ProjectDto project = projectOpt.get();
        FlatProjectDto flatProject = projectMapper.toFlatProjectDto(project);
        fillLists(project.getWbsList(), Collections.emptyList(), flatProject.getWbsList(), flatProject.getActivities());

        return Optional.of(flatProject);
    }

    private void fillLists(List<WbsDto> sourceWbsList, List<ActivityDto> sourceActivities, List<FlatWbsDto> targetWbsList, List<ActivityDto> targetActivities) {
        for (WbsDto sourceWbs : sourceWbsList) {
            targetWbsList.add(wbsMapper.toFlatWbsDto(sourceWbs));
            fillLists(sourceWbs.getChildWbsList(), sourceWbs.getActivities(), targetWbsList, targetActivities); // recursive call
        }

        targetActivities.addAll(sourceActivities);
    }
}
