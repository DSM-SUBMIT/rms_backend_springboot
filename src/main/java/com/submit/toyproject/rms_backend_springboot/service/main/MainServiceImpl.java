package com.submit.toyproject.rms_backend_springboot.service.main;

import com.submit.toyproject.rms_backend_springboot.domain.field.*;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.dto.response.*;
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
    public MainFeedResponse getMainFeed(Pageable page, List<FieldEnum> filteringFields) {
        Page<Project> projectPage = projectFieldRepository.findAllByFields(filteringFields, page);

        List<ProjectListElementDto> projectDtoList = new ArrayList<>();

        for (Project project : projectPage.getContent()) {
            List<String> fieldList = projectFieldRepository.findByProject(project).stream()
                    .map(projectField -> projectField.getField().getField().toString())
                    .collect(Collectors.toList());

             ProjectListElementDto projectDto = ProjectListElementDto.builder()
                    .id(project.getId())
                    .projectName(project.getProjectName())
                    .teamName(project.getTeamName())
                    .projectType(project.getProjectType().toString())
                    .fieldList(fieldList)
                    .build();

             projectDtoList.add(projectDto);
        }

        return MainFeedResponse.builder()
                .currentPage(projectPage.getNumber())
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

}
