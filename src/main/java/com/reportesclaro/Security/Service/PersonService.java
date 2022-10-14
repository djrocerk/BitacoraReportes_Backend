package com.reportesclaro.Security.Service;

import com.reportesclaro.Security.Entity.PersonEntity;
import com.reportesclaro.Security.Entity.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PersonService {

    PersonEntity save(PersonEntity onePerson);

    public List<PersonEntity> findAll();
}
