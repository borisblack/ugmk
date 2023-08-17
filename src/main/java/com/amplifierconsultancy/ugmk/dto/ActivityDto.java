package com.amplifierconsultancy.ugmk.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ActivityDto {
    private String id;
    private String name;
    private String wbsObjectId;
    private String wbsCode;
    private String wbsName;
    private String wbsNamePath;
    private OffsetDateTime plannedStartDate;
    private OffsetDateTime plannedFinishDate;
    private OffsetDateTime actualStartDate;
    private OffsetDateTime actualFinishDate;
}
