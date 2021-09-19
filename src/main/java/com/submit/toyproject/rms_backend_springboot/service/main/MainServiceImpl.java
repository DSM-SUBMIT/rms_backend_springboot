package com.submit.toyproject.rms_backend_springboot.service.main;

import com.submit.toyproject.rms_backend_springboot.domain.field.ProjectField;
import com.submit.toyproject.rms_backend_springboot.domain.field.ProjectFieldRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectRepository;
import com.submit.toyproject.rms_backend_springboot.dto.response.MainFeedResponse;
import com.submit.toyproject.rms_backend_springboot.dto.response.ProjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService{

    private final ProjectRepository projectRepository;
    private final ProjectFieldRepository projectFieldRepository;

    @Override
    @Transactional
    public MainFeedResponse getMainFeed(Integer lastProjectId, int size) {

        PageRequest pageRequest = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "id"));

        List<Project> projectList;
        List<ProjectDto> projectDtoList = new ArrayList<>();

        // 맨 처음에는 0으로 받으면 전체 중에 id 내림차순
        if(lastProjectId == 0) {
            projectList = projectRepository.findAllByReportAcceptedAndPlanAccepted(pageRequest).getContent();
        } else {
            projectList = projectRepository
                    .findByIdLessThanAndReportAcceptedAndIsPlanAccepted(lastProjectId, pageRequest).getContent();
        }

        for (Project project : projectList) {
            List<ProjectField> projectFieldList = projectFieldRepository.findByProject(project);

            List<String> fieldList = projectFieldList.stream()
                    .map(projectField -> projectField.getField().getField().toString())
                    .collect(Collectors.toList());

             ProjectDto projectDto = ProjectDto.builder()
                    .id(project.getId())
                    .projectName(project.getProjectName())
                    .teamName(project.getTeamName())
                    .projectType(project.getProjectType().toString())
                    .fieldList(fieldList)
                    .build();

             projectDtoList.add(projectDto);
        }

        return new MainFeedResponse(projectDtoList);
    }
}
