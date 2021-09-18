package com.submit.toyproject.rms_backend_springboot.service.project;

import com.submit.toyproject.rms_backend_springboot.dto.request.ProjectRequest;
import com.submit.toyproject.rms_backend_springboot.dto.request.ProjectUrlsRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.ProjectResponse;

public interface ProjectService {
    Integer createProject(ProjectRequest projectRequest);
    ProjectResponse getProject(Integer id);
    void updateProject(Integer id, ProjectRequest projectRequest);
    void updateUrls(Integer id, ProjectUrlsRequest request);
    void deleteProject(Integer id);
    void sendMail(String email, String teamName);
}
