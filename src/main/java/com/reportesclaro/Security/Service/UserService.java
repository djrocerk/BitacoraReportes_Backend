package com.reportesclaro.Security.Service;

import com.reportesclaro.Security.Dto.UserDto;
import com.reportesclaro.Security.Entity.UserEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface UserService {

    public List<UserEntity> findAll();

    public UserEntity updateEmailById(Long id);

    public void saveUser (UserEntity userEntity);

    UserEntity save(UserEntity userEntity);

    HttpStatus updatePassword(UserDto userDto);



}
