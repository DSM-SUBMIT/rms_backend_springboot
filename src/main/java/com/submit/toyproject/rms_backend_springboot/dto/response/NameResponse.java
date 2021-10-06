package com.submit.toyproject.rms_backend_springboot.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NameResponse {

    @Schema(description = "내 이름", example = "곽도현")
    private String name;

}
