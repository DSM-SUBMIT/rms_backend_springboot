package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.request.ProjectRequest;
import com.submit.toyproject.rms_backend_springboot.dto.request.ProjectUrlsRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedProjectDetailResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.MyPageProjectDetailResponse;
import com.submit.toyproject.rms_backend_springboot.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/project")
@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Integer createProject(@Valid @RequestBody ProjectRequest projectRequest) {
        return projectService.createProject(projectRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me/{id}")
    public MyPageProjectDetailResponse getProject(@PathVariable Integer id) {
        return projectService.getMyProject(id);
    }

    @GetMapping("/{id}")
    public MainFeedProjectDetailResponse getProjectDetail(@PathVariable Integer id) {
        return projectService.getProjectDetail(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void updateProject(@PathVariable Integer id, @Valid @RequestBody ProjectRequest projectRequest) {
        projectService.updateProject(id, projectRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}/url")
    public void updateProjectUrls(@PathVariable Integer id, @Valid @RequestBody ProjectUrlsRequest projectUrlsRequest) {
        projectService.updateUrls(id, projectUrlsRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
    }
}
