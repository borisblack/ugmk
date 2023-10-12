package com.amplifierconsultancy.ugmk.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public abstract class AbstractProjectDto {
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
    private OffsetDateTime startDate;
    private OffsetDateTime finishDate;
    private OffsetDateTime plannedStartDate;
    private OffsetDateTime summaryPlannedFinishDate;
    private OffsetDateTime summaryActualStartDate;
    private OffsetDateTime summaryActualFinishDate;
}
