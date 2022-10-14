package com.reportesclaro.Security.Service.implement;

import com.reportesclaro.Security.Dto.UserDto;
import com.reportesclaro.Security.Entity.UserEntity;
import com.reportesclaro.Security.Repository.UserRepository;
import com.reportesclaro.Security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity updateEmailById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void saveUser(UserEntity userEntity) {
         userRepository.save(userEntity);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return null;
    }

    @Override
    public HttpStatus updatePassword(UserDto userDto) {

        HttpStatus httpStatus;
        try{
            if (!userDto.getNewPassword().equals(userDto.getConfirmPassword())){
                throw new Exception("¡La nueva contraseña y Confirmar contraseña no coinciden!");
            }
            if (userDto.getCurrentPassword().equals(userDto.getNewPassword())){
                throw new Exception("¡La nueva contraseña debe ser diferente a la contraseña actual!");
            }
            UserEntity currentUser = userRepository.findById(userDto.getId())
                    .orElseThrow(() -> new Exception("Usuario no encontrado"));

            boolean statusPass = passwordEncoder.matches(userDto.getCurrentPassword(), currentUser.getPassword());
            if(statusPass) {
                currentUser.setPassword(passwordEncoder.encode(userDto.getNewPassword()));
                userRepository.save(currentUser);
                httpStatus = HttpStatus.OK;
            }else httpStatus = HttpStatus.BAD_REQUEST;

        }catch (Exception e){
            System.out.println(e);
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return httpStatus;
    }

}
