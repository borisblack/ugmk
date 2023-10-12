package com.amplifierconsultancy.ugmk.controller;

import com.amplifierconsultancy.ugmk.dto.FlatProjectDto;
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
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
@Data
public class ProjectController {
    @NonNull
    private final ProjectService projectService;

    @GetMapping("/{id}")
    public ProjectDto loadProject(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        Optional<ProjectDto> project = projectService.loadProject(id);
        if (!project.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        return project.get();
    }

    @GetMapping("/flat/{id}")
    public FlatProjectDto loadFlatProject(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        Optional<FlatProjectDto> project = projectService.loadFlatProject(id);
        if (!project.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        return project.get();
    }
}
