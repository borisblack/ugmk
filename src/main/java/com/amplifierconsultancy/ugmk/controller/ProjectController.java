package com.amplifierconsultancy.ugmk.controller;

import com.amplifierconsultancy.ugmk.dto.ProjectDto;
import com.amplifierconsultancy.ugmk.service.ProjectService;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/project")
@Data
public class ProjectController {
    @NonNull
    private final ProjectService projectService;

    @GetMapping("/{id}")
    public ProjectDto loadProject(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        List<ProjectDto> projects = projectService.loadProjects(id);
        if (projects.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        return projects.get(0);
    }
}
