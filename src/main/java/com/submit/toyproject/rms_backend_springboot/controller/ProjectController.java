package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.request.ProjectRequest;
import com.submit.toyproject.rms_backend_springboot.dto.request.ProjectUrlsRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedProjectDetailResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.MyPageProjectDetailResponse;
import com.submit.toyproject.rms_backend_springboot.service.project.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "프로젝트")
@RequiredArgsConstructor
@RequestMapping("/project")
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "프로젝트 생성하기")
    @ApiResponse(responseCode = "201", description = "성공적으로 프로젝트가 생성되었고 프로젝트 id를 반환")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Integer createProject(@Valid @RequestBody ProjectRequest request) {
        return projectService.createProject(request);
    }

    @Operation(summary = "마이페이지에서 프로젝트 정보 상세보기")
    @Parameter(name = "id", description = "프로젝트 아이디")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me/{id}")
    public MyPageProjectDetailResponse getProject(@PathVariable Integer id) {
        return projectService.getMyProject(id);
    }

    @Operation(summary = "프로젝트 상세보기(마이페이지 x)")
    @Parameter(name = "id", description = "프로젝트 아이디")
    @GetMapping("/{id}")
    public MainFeedProjectDetailResponse getProjectDetail(@PathVariable Integer id) {
        return projectService.getProjectDetail(id);
    }

    @Operation(summary = "프로젝트 수정하기")
    @Parameter(name = "id", description = "프로젝트 아이디")
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void updateProject(@PathVariable Integer id, @Valid @RequestBody ProjectRequest request) {
        projectService.updateProject(id, request);
    }

    @Operation(summary = "프로젝트의 url들 수정하기")
    @Parameter(name = "id", description = "프로젝트 아이디")
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}/url")
    public void updateProjectUrls(@PathVariable Integer id, @Valid @RequestBody ProjectUrlsRequest request) {
        projectService.updateUrls(id, request);
    }

    @Operation(summary = "프로젝트 삭제하기")
    @Parameter(name = "id", description = "프로젝트 아이디")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
    }
}
