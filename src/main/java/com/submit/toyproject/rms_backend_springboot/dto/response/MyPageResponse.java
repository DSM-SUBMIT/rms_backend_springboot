package com.submit.toyproject.rms_backend_springboot.dto.response;

import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponse {

    @ApiModelProperty(value = "내 이름", example = "곽도현")
    private String name;

    @ApiModelProperty(value = "내 이메일", example = "200000kkk@dsm.hs.kr")
    private String email;

    @ApiModelProperty(value = "내가 참여한 프로젝트 리스트")
    private List<ProjectListElementDto> projectList;

    public static MyPageResponse of(User user, List<ProjectListElementDto> projectList) {
        return MyPageResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .projectList(projectList)
                .build();
    }
}
