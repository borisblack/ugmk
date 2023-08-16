package com.amplifierconsultancy.ugmk.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ProjectDto {
    private String id;
    private String objectId;
    private String guid;
    private String name;
    private String obsObjectId;
    private String obsName;
    private String wbsObjectId;
    private Integer wbsHierarchyLevels;
    private String wbsCodeSeparator;
    private String status;
    private String activityIdPrefix;
    private String activityDefaultCalendarName;
    private String parentEPSName;
    private String addedBy;
    private Date startDate;
    private Date finishDate;
    private Date plannedStartDate;
    private Date summaryPlannedFinishDate;
    private Date summaryActualStartDate;
    private Date summaryActualFinishDate;

    private List<WbsDto> wbsList = new ArrayList<>();

    public void addWbs(WbsDto wbs) {
        wbsList.add(wbs);
    }
}
