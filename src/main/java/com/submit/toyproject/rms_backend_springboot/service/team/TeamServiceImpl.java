package com.submit.toyproject.rms_backend_springboot.service.team;

import com.submit.toyproject.rms_backend_springboot.domain.team.Team;
import com.submit.toyproject.rms_backend_springboot.domain.team.TeamRepository;
import com.submit.toyproject.rms_backend_springboot.exception.TeamAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public void saveTeam(String name) {
        //토큰 확인
        if (teamRepository.existsByName(name)) {
            throw new TeamAlreadyExistsException();
        } else {
            teamRepository.save(new Team(name));
        }
    }

}
