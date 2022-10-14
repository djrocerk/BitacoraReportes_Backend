package com.reportesclaro.Security.Controller;

import com.reportesclaro.Security.Dto.UserDto;
import com.reportesclaro.Security.Entity.RoleEntity;
import com.reportesclaro.Security.Entity.UserEntity;
import com.reportesclaro.Security.Service.RolService;
import com.reportesclaro.Security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/user"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RolService rolService;

    @GetMapping(value = {"/list"})
    public List<UserEntity> userEntities(){
        return userService.findAll();
    }

    @GetMapping(value = {"/rol/list"})
    public List<RoleEntity> Rol(){
        return rolService.findAll();
    }


    @PutMapping(value = {"/update/password/{id}"})
    public ResponseEntity<?> updatePassword1(@RequestBody UserDto userDto, @PathVariable Long id){
        userDto.setId(id);
        return new ResponseEntity<UserEntity>(this.userService.updatePassword(userDto));
    }
}
