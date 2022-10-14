package com.reportesclaro.Security.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {

    private String usernameOrEmail;

    private String password;
}
