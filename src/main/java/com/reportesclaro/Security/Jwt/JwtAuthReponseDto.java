package com.reportesclaro.Security.Jwt;

import com.reportesclaro.Security.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthReponseDto {

    private String tokeDeAcceso;

    private String tipoDeToken = "Bearer";

    private UserEntity oneUser;


    public JwtAuthReponseDto(String tokeDeAcceso, UserEntity oneUser) {
        super();
        this.tokeDeAcceso = tokeDeAcceso;
        this.oneUser=oneUser;
    }


}
