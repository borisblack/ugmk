package com.amplifierconsultancy.ugmk.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public abstract class AbstractWbsDto {
    private String objectId;
    private String parentObjectId;
    private String guid;
    private String code;
    private String fullCode;
    private String name;
    private String obsObjectId;
    private String obsName;
    private String wbsCategoryObjectId;
    private String status;
    private OffsetDateTime startDate;
    private OffsetDateTime finishDate;
    private OffsetDateTime summaryActualStartDate;
    private OffsetDateTime summaryActualFinishDate;
}
