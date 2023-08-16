package com.amplifierconsultancy.ugmk.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ActivityDto {
    private String id;
    private String name;
    private String wbsObjectId;
    private String wbsCode;
    private String wbsName;
    private String wbsNamePath;
    private Date plannedStartDate;
    private Date plannedFinishDate;
    private Date actualStartDate;
    private Date actualFinishDate;
}
