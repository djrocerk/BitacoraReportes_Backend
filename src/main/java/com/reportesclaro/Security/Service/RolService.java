package com.reportesclaro.Security.Service;

import com.reportesclaro.Security.Entity.RoleEntity;
import com.reportesclaro.Security.Entity.UserEntity;

import java.util.List;

public interface RolService {

    public List<RoleEntity> findAll();
}
