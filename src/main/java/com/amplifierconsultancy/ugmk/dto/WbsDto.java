package com.amplifierconsultancy.ugmk.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WbsDto {
    private String name;
    private List<WbsDto> children = new ArrayList<>();

    public void addChild(WbsDto child) {
        children.add(child);
    }
}
