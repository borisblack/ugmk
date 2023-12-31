package com.amplifierconsultancy.ugmk.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectDto extends AbstractProjectDto {
    private List<WbsDto> wbsList = new ArrayList<>();

    public void addWbs(WbsDto wbs) {
        wbsList.add(wbs);
    }
}
