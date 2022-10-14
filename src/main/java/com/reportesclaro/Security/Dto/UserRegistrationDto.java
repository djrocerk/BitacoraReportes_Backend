package com.reportesclaro.Security.Dto;

import com.reportesclaro.Security.Entity.PersonEntity;
import com.reportesclaro.Security.Entity.RoleEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationDto {

    private String username;

    private String email;

    private PersonEntity personEntity;

    private RoleEntity oneRol;
}
