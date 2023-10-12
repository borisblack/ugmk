package com.amplifierconsultancy.ugmk.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class WbsDto extends AbstractWbsDto {
    private List<ActivityDto> activities = new ArrayList<>();

    @JsonManagedReference
    private List<WbsDto> childWbsList = new ArrayList<>();

    public void addActivity(ActivityDto activity) {
        activities.add(activity);
    }

    public void addChild(WbsDto childWbs) {
        childWbsList.add(childWbs);
    }
}
