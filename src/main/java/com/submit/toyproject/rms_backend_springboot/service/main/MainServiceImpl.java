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
    public MainFeedResponse getMainFeed(Pageable page, List<String> filteringFieldsStr) {

        List<FieldEnum> filteringFields = new ArrayList<>();

        if(filteringFieldsStr != null)
        try {
            filteringFields.addAll(filteringFieldsStr.stream()
                    .map(FieldEnum::valueOf)
                    .collect(Collectors.toList()));
        } catch (IllegalArgumentException e) {
            // 필터링 파라미터가 FieldEnum(WEB, APP, GAME, EMBEDDED, AI_BIGDATA, SECURITY)
            // 값이 아닌 경우 파라미터 무시
        }

        Page<Project> projectPage = projectFieldRepository.findAllByFields(filteringFields, page);

        List<ProjectListElementDto> projectDtoList = projectPage.getContent().stream()
                .map(project -> ProjectListElementDto.of(project, getFieldList(project)))
                .collect(Collectors.toList());

        return MainFeedResponse.of(projectPage, projectDtoList);
    }

    private List<FieldEnum> getFieldList(Project project) {
        return projectFieldRepository.findFieldEnumByProject(project);
    }

}
