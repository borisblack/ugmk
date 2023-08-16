package com.amplifierconsultancy.ugmk.controller;

import com.amplifierconsultancy.ugmk.dto.ProjectDto;
import com.amplifierconsultancy.ugmk.service.ProjectService;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@Data
public class ProjectController {
    @NonNull
    private final ProjectService projectService;

    @GetMapping
    public List<ProjectDto> loadProjects() {
        return projectService.loadProjects("EC02016");
    }
}
