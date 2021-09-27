package com.submit.toyproject.rms_backend_springboot.service.main;

import com.submit.toyproject.rms_backend_springboot.domain.field.*;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.dto.response.*;
import com.submit.toyproject.rms_backend_springboot.exception.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService{

    private final ProjectFieldRepository projectFieldRepository;

    @Override
    @Transactional
    public MainFeedResponse getMainFeed(Pageable page, List<String> filteringFieldsStr) {

        List<FieldEnum> filteringFields = new ArrayList<>();

        if(filteringFieldsStr != null)
        try {
            filteringFields.addAll(filteringFieldsStr.stream().map(FieldEnum::valueOf).collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            // 필터링 query parameter가 FieldEnum이 아닌 경우 무시
        }

        Page<Project> projectPage = projectFieldRepository.findAllByFields(filteringFields, page);

        List<ProjectListElementDto> projectDtoList = createProjectListResponse(projectPage.getContent());

        return MainFeedResponse.builder()
                .currentPage(projectPage.getNumber()+1) // 1부터 시작
                .size(projectPage.getSize())
                .totalPages(projectPage.getTotalPages())
                .currentPageElements(projectPage.getNumberOfElements())
                .totalElements(projectPage.getTotalElements())
                .hasPreviousPage(projectPage.hasPrevious())
                .isFirstPage(projectPage.isFirst())
                .hasNextPage(projectPage.hasNext())
                .isLastPage(projectPage.isLast())
                .projectList(projectDtoList)
                .build();
    }

    private List<ProjectListElementDto> createProjectListResponse(List<Project> projectList) {
        List<ProjectListElementDto> projectDtoList = new ArrayList<>();

        for (Project project : projectList) {
            List<FieldEnum> fieldList = projectFieldRepository.findByProject(project).stream()
                    .map(projectField -> projectField.getField().getField())
                    .collect(Collectors.toList());

            ProjectListElementDto projectDto = ProjectListElementDto.builder()
                    .id(project.getId())
                    .projectName(project.getProjectName())
                    .teamName(project.getTeamName())
                    .projectType(project.getProjectType().getDivision())
                    .fieldList(fieldList)
                    .build();

            projectDtoList.add(projectDto);
        }
        return projectDtoList;
    }

}
