package com.reportesclaro.Security.Service.implement;

import com.reportesclaro.Security.Entity.RoleEntity;
import com.reportesclaro.Security.Repository.RolRepository;
import com.reportesclaro.Security.Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<RoleEntity> findAll() {
        return rolRepository.findAll();
    }

}
