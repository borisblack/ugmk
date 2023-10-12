package com.amplifierconsultancy.ugmk.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class FlatProjectDto extends AbstractProjectDto {
    private List<FlatWbsDto> wbsList = new ArrayList<>();
    private List<ActivityDto> activities = new ArrayList<>();
}
