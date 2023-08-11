package com.amplifierconsultancy.ugmk.controller;

import com.amplifierconsultancy.ugmk.dto.WbsDto;
import com.amplifierconsultancy.ugmk.service.WbsService;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wbs")
@Data
public class WbsController {
    @NonNull
    private final WbsService wbsService;

    @GetMapping
    public List<WbsDto> loadWbsList() {
        return wbsService.loadWbsList();
    }
}
