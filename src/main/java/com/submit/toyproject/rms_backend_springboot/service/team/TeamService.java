package com.submit.toyproject.rms_backend_springboot.service.team;

import com.submit.toyproject.rms_backend_springboot.dto.response.TeamsResponse;

public interface TeamService {
    void saveTeam(String name);
    TeamsResponse getTeams(String keyword);
}
