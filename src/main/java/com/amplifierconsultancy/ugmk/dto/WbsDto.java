package com.amplifierconsultancy.ugmk.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class WbsDto {
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

    private List<ActivityDto> activities = new ArrayList<>();

    @JsonBackReference
    private List<WbsDto> childWbsList = new ArrayList<>();

    public void addActivity(ActivityDto activity) {
        activities.add(activity);
    }

    public void addChild(WbsDto childWbs) {
        childWbsList.add(childWbs);
    }
}
