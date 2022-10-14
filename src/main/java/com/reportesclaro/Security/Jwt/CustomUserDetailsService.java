package com.reportesclaro.Security.Jwt;

import com.reportesclaro.Security.Entity.RoleEntity;
import com.reportesclaro.Security.Entity.UserEntity;
import com.reportesclaro.Security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameorEmail) throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findByUsernameOrEmail(usernameorEmail, usernameorEmail)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado con ese username o email: "+usernameorEmail));
        return new User(userEntity.getEmail(),userEntity.getPassword(), mapearRoles(userEntity.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapearRoles(Set<RoleEntity> roles){
        return roles.stream().map(rol->new SimpleGrantedAuthority(rol.getRol_Name())).collect(Collectors.toList());
    }

}
