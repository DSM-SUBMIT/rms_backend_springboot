package com.submit.toyproject.rms_backend_springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamsResponse {

    private List<TeamDto> teams;

}
