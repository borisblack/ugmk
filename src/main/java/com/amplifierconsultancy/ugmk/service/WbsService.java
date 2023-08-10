package com.amplifierconsultancy.ugmk.service;

//import com.primavera.common.value.spread.WBSSpread;
//import com.primavera.integration.client.GlobalObjectManager;
//import com.primavera.integration.client.Session;
//import com.primavera.integration.client.EnterpriseLoadManager;
//import com.primavera.integration.client.RMIURL;
//import com.primavera.integration.client.bo.object.*;
//import com.primavera.integration.common.DatabaseInstance;
//import com.primavera.integration.client.bo.BOIterator;

import com.amplifierconsultancy.ugmk.mapper.WbsMapper;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class WbsService {
    @NonNull
    private final WbsMapper wbsMapper;

    public void loadWbsList() {
//        System.setProperty("primavera.bootstrap.home","D:\\ProgramJPS\\P6IntegrationAPI_1");
//        //System.setProperty("primavera.bootstrap.home","C:\\P6IntegrationAPI_1");
//        Session session = null;
//
//
//        try
//        {
//            DatabaseInstance[] dbInstances = Session.getDatabaseInstances(
//                    RMIURL.getRmiUrl( RMIURL.LOCAL_SERVICE ) );
//
//            // Assume only one database instance for now, and hardcode the username and
//            // password for this sample code
//            session = Session.login( RMIURL.getRmiUrl( RMIURL.LOCAL_SERVICE ),
//                    dbInstances[0].getDatabaseId(), "admin", "Admin712" );
//
//
//
///*
//            GlobalObjectManager glb = session.getGlobalObjectManager();
//            BOIterator<EPS> boi_EPS = glb.loadEPS( new String[]{"Name"}, null, null);
//            System.out.println("EPS start");
//            while ( boi_EPS.hasNext() ) {
//                EPS o = boi_EPS.next();
//                System.out.println(o.getName());
//            }
//
//            BOIterator<OBS> boi_OBS = glb.loadOBS( new String[]{"Name"}, null, null);
//            System.out.println("OBS start");
//            while ( boi_OBS.hasNext() ) {
//                OBS o = boi_OBS.next();
//                System.out.println(o.getName());
//            }*/
//
//
//            //String tab_s = "          ";
//            //String tab_ss = "                                                                                                  ";
//
//            EnterpriseLoadManager elm = session.getEnterpriseLoadManager();
//            System.out.println("Project start");
//            BOIterator<Project> boi = elm.loadProjects( new String[]{
//                            "ObjectId",
//                            "Name",
//                            "WBSHierarchyLevels",
//                            "WBSCodeSeparator",
//                            "OBSName",
//                            //"Description",
//                            //"LocationName",
//                            "ActivityIdPrefix",
//                            "Id",
//                            "ActivityDefaultCalendarName",
//                            "AddedBy",
//                            //"ContractManagementGroupName",
//                            //"ContractManagementProjectName",
//                            //"LocationObjectId",
//                            "ParentEPSName",
//                            //"ResourceName",
//                            //"UnifierProjectName",
//                            "WBSObjectId",
//                            "GUID",
//                            "Status",
//                            "StartDate",
//                            "FinishDate",
//                            "PlannedStartDate",
//                            "SummaryPlannedFinishDate",
//                            "SummaryActualStartDate",
//                            "SummaryActualFinishDate"},
//                    //"Name like '%кустовой%' ",null /*"Name asc"*/ );
//                    //"ObjectId = 4825 ","ObjectId asc" );
//                    "ObjectId = 368 ","ObjectId asc" );
//
//            System.out.println("Код проекта  id проекта  id WBS   level WBS  Наименование проекта                GUID                                     плановая стартовая дата  плановая общая дата окончания  актуальная стартовая дата  актуальная дата окончания");
//            while ( boi.hasNext() )
//            {
//                Project proj = boi.next();
//                /*if ( proj.getDescription() != null ) {
//                    System.out.println(proj.getDescription());
//                }*/
//                /*if ( proj.getLocationName() != null ) {
//                    System.out.println(proj.getLocationName());
//                }*/
//                System.out.println(proj.getId() + "         " +                   // код проекта
//                        proj.getObjectId().toString() + "        " +   // id проекта
//                        proj.getWBSObjectId() + "    " +               // id WBS (id узла в дереве)
//                        proj.getWBSHierarchyLevels() + "          " +  // level WBS
//                        proj.getName() + "  " +                        // Наименование проекта
//                        proj.getGUID() + "   " +                        // GUID
//                        ((proj.getPlannedStartDate() == null) ? "не задана                 " : proj.getPlannedStartDate().toString()+ "               ") +
//                        ((proj.getSummaryPlannedFinishDate() == null) ? "не задана                      " : proj.getSummaryPlannedFinishDate().toString()+ "               ") +
//                        ((proj.getSummaryActualStartDate() == null) ? "не задана                  " : proj.getSummaryActualStartDate().toString()+ "            ") +
//                        ((proj.getSummaryActualFinishDate() == null) ? "не задана" : proj.getSummaryActualFinishDate().toString()));
//
//                System.out.println();
//                System.out.println();
//
//
//                //System.out.println(proj.getActivityIdPrefix()); //КП12.
//                //System.out.println(proj.getActivityDefaultCalendarName()); //какой-то период
//                //System.out.println(proj.getAddedBy()); //кем добавлено
//
//                //System.out.println(proj.getContractManagementGroupName()); //null
//                //System.out.println(proj.getContractManagementProjectName()); //null
//
//                //System.out.println(proj.getLocationObjectId()); //null
//
//                //System.out.println(proj.getParentEPSName()); //All  Initiatives
//
//                //System.out.println(proj.getResourceName()); //null
//                //System.out.println(proj.getUnifierProjectName()); //null
//
//                //if ( proj.getOBSName() != null ) {
//                //    System.out.println(proj.getOBSName()); //CEPTR
//                // }
//
//                //if ( proj.getWBSCodeSeparator() != null ) {
//                //    System.out.println(proj.getWBSCodeSeparator()); // (.) "точка" разделитель
//                //}
//
//                //if ( proj.getWBSHierarchyLevels() != null ) {
//                //    System.out.println(proj.getWBSHierarchyLevels()); // 0 - level в дереве
//                //}
//
//                //System.out.println( proj.getName() + " id проекта:" + proj.getObjectId().toString() ); //наименование проекта
//
//                /*BOIterator<Activity> boi_a = proj.loadAllActivities( new String[]{ "Id", "Name" }, null, "Name desc" );
//                while ( boi.hasNext() )
//                {
//                    Activity act = boi_a.next();
//                    System.out.println( act.getName() );
//                    // Код для каждой задачи...
//                }*/
//
//                BOIterator<WBS> boi_wbs = proj.loadAllWBS( new String[]{
//                        "Name",
//                        "Code",
//                        "GUID",
//                        "OBSName",
//                        "ParentObjectId",
//                        //"StatusReviewerName",
//                        //"WBSSpread",
//                        "ObjectId",
//                        "WBSCategoryObjectId",
//                        "Status",
//                        "StartDate",
//                        "FinishDate",
//                        "SummaryActualStartDate",
//                        "SummaryActualFinishDate"}, "ParentObjectId = " + proj.getWBSObjectId(), "ObjectId asc"  /*"WBSCategoryObjectId asc"*/);
//
//                System.out.println("Код               id объекта WBS  id parent WBS  Наименование объекта                                                                              GUID                                    плановая стартовая дата  плановая общая дата окончания  актуальная стартовая дата  актуальная дата окончания");
//
//                String id_ = "";
//                StringBuilder code_ = new StringBuilder(proj.getId());
//                while ( boi_wbs.hasNext() )
//                {
//                    WBS work = boi_wbs.next();
//                    //work.getObjectId()
//                    wbs_load(work,code_.toString());
//
//                    /* 17.07.23
//                    id_ = work.getObjectId().toString();
//                    code_.append(work.getCode());
//
//                    BOIterator<WBS> boi_wbs_child =  work.loadWBSChildren( new String[]{"Name", "Code", "ObjectId"}, null, null);
//                    while ( boi_wbs_child.hasNext() ){
//                        WBS work_c = boi_wbs_child.next();
//                        id_ = work_c.getObjectId().toString();
//                        code_.append(work_c.getCode());
//                    }
//
//
//                    System.out.println( work.getCode() + tab_s.substring(work.getCode().length()) + // Код
//                                        work.getObjectId().toString() + "           " + // id объекта WBS
//                                        work.getParentObjectId() + "          " + // id объекта parent WBS
//                                        work.getName() + tab_ss.substring(work.getName().length()) + // Наименование объекта
//                                        work.getGUID());
//                    */
//
//
//
//                    //if ( work.getOBSName() != null) {
//                    //    System.out.println( "    OBSName:" + work.getOBSName()); // CEPTR
//                    //}
//                    /*if ( work.getStatusReviewerName() != null) {
//                        System.out.println( "    StatusReviewerName:" + work.getStatusReviewerName()); // null
//                    }*/
//                    /*if ( work.getWBSSpread() != null) {
//                        WBSSpread spr = work.getWBSSpread();
//                        System.out.println( "    spr:" + spr.toString() );
//                    }*/
//
//
//                  /*  System.out.println( "       " + work.getValue(0).toString() +
//                                            "   " + work.getValue(1).toString() +
//                                            "   " + work.getValue(2).toString() +
//                                            "   " + work.getValue("Status").toString() +
//                                            //"   " + work.getValue(3).toString() +
//                                            //"   " + work.getValue(4).toString() +
//                                            //"   " + work.getValue(5).toString() +
//                                            //"   " + work.getValue(6).toString() +
//                                            //"   " + work.getValue(7).toString() +
//                                            "   " + work.getName() +
//                                   " id объекта:" + work.getObjectId().toString() );*/
//
//                   /* BOIterator<Activity> boi_a = work.loadAllActivities( new String[]{ "Name" }, null, null  );
//
//                    while ( boi_a.hasNext() )
//                    {
//                        Activity act = boi_a.next();
//                        System.out.println(//"                         " + act.getValue(0).toString() +
//                                           //"   " + act.getValue("Status").toString() +
//                                           "                          работы:" + act.getName() );
//                        // Код для каждой задачи...
//                    }*/
//
//                    // Код для каждой задачи...
//                }
//
//
//
//
//
//
//            }
//        }
//        catch ( Exception e )
//        {
//            // Best practices would involve catching specific exceptions.  To keep this
//            // sample code short, we catch Exception
//            System.out.printf("что то пошло не так!");
//            e.printStackTrace();
//        }
//        finally
//        {
//            if ( session != null )
//                session.logout();
//        }
//
//
//
//
//        System.out.printf("Всё ок!");
//        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
//        for (int i = 1; i <= 5; i++) {
//
//            // Press Shift+F9 to start debugging your code. We have set one breakpoint
//            // for you, but you can always add more by pressing Ctrl+F8.
//            System.out.println("i = " + i);
//        }
    }

//    private void printWbsList(WBS work, String cod_){
//        StringBuilder code_ = new StringBuilder(cod_);
//        String tab_s = "                  ";
//        String tab_ss = "                                                                                                  ";
//        try {
//            code_.append("." + work.getCode()); // Код узла собираем по кускам, т.к. каждый узел хранит свой код, надо все коды узлов собирать в конечный
//            System.out.println();
//            System.out.println(code_ + tab_s.substring(code_.toString().length()) + // Код
//                    work.getObjectId().toString() + "           " +                            // id объекта WBS
//                    work.getParentObjectId() + "          " +                                  // id объекта parent WBS
//                    work.getName() + tab_ss.substring(work.getName().length()) +               // Наименование объекта
//                    work.getGUID() + "  " +                                                    // GUID
//                    ((work.getStartDate() == null) ? "не задана                " : work.getSummaryPlannedStartDate().toString()+ "   ")  +
//                    ((work.getFinishDate() == null) ? "не задана                      " : work.getSummaryPlannedFinishDate().toString()+ "   ") +
//                    ((work.getSummaryActualStartDate() == null) ? "не задана                  " : work.getSummaryActualStartDate().toString()+ "   ") +
//                    ((work.getSummaryActualFinishDate() == null) ? "не задана" : work.getSummaryActualFinishDate().toString())
//            );
//
//            BOIterator<Activity> boi_a = work.loadActivities( new String[]{ "Name", "WBSCode", "WBSName","WBSNamePath","WBSObjectId","Id" ,"PlannedStartDate", "PlannedFinishDate", "ActualStartDate", "ActualFinishDate"}, null, null  );
//
//            while (boi_a.hasNext()) {
//                Activity act = boi_a.next();
//                System.out.println(//"                         " + act.getValue(0).toString() +
//                        //"   " + act.getValue("Status").toString() +
//                        "                                                   работы: " + //act.getWBSCode() + "  " +
//                                //act.getWBSName() + "  " +
//                                //act.getWBSNamePath() +"  " +
//                                //act.getWBSObjectId() + "  " +
//                                act.getId() + "  " +
//                                act.getName() + "  плановая дата начала: " +
//                                act.getPlannedStartDate().toString() + " плановая дата окончания: " +
//                                act.getPlannedFinishDate().toString() + "   актуальная дата начала: " +
//                                ( (act.getActualStartDate() == null) ? " не задана " : act.getActualStartDate().toString()) + " актуальная дата окончания: " +
//                                ( (act.getActualFinishDate() == null) ? " не задана " : act.getActualFinishDate().toString() ) );
//
//            }
//
//            BOIterator<WBS> boi_wbs_child = work.loadWBSChildren(new String[]{"Name",
//                    "Code",
//                    "ObjectId",
//                    "ParentObjectId",
//                    "GUID",
//                    "StartDate",
//                    "FinishDate",
//                    "SummaryActualStartDate",
//                    "SummaryActualFinishDate"}, null, null);
//            while (boi_wbs_child.hasNext()) {
//                WBS work_c = boi_wbs_child.next();
//                wbs_load(work_c, code_.toString()); //рекурсия пока boi_wbs_child.hasNext()
//            }
//        }
//        catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }
}
