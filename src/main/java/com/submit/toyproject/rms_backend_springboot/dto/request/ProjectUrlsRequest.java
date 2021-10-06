package com.submit.toyproject.rms_backend_springboot.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUrlsRequest {

    @Schema(name = "깃허브 url1, 깃허브 url2 ...", example = "https://github.com/DSM-SUBMIT/rms_backend_springboot, https://github.com/DSM-SUBMIT/rms_admin_client")
    private String githubUrl;

    @Schema(name = "문서 url", example = "https://www.notion.so/RMS-42638bca344447aa8a8674fa044c3db6")
    private String docsUrl;

    @Schema(name = "서비스 url", example = "https://dsm-rms.com")
    private String serviceUrl;
}
