package com.submit.toyproject.rms_backend_springboot.dto.response;

import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @ApiModelProperty(example = "<유저 id>")
    private Integer id;

    @ApiModelProperty(example = "<이름>")
    private String name;

    @ApiModelProperty(example = "<이메일>")
    private String email;

    public static UserDto of(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
