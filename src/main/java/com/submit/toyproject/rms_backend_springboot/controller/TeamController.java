package com.submit.toyproject.rms_backend_springboot.controller;

import com.submit.toyproject.rms_backend_springboot.dto.request.TeamRequest;
import com.submit.toyproject.rms_backend_springboot.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/team")
@RestController
public class TeamController {

    private final TeamService teamService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveTeam(@RequestBody @Valid TeamRequest request) {
        System.out.println(request.getName());
        teamService.saveTeam(request.getName());
    }

}
