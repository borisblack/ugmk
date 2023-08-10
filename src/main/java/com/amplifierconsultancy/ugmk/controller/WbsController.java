package com.amplifierconsultancy.ugmk.controller;

import com.amplifierconsultancy.ugmk.service.WbsService;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wbs")
@Data
public class WbsController {
    @NonNull
    private final WbsService wbsService;

    @GetMapping
    public void loadWbsList() {
        wbsService.loadWbsList();
    }
}
