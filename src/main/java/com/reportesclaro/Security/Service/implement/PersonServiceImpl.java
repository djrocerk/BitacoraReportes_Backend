package com.reportesclaro.Security.Service.implement;

import com.reportesclaro.Security.Entity.PersonEntity;
import com.reportesclaro.Security.Entity.RoleEntity;
import com.reportesclaro.Security.Repository.PersonRepository;
import com.reportesclaro.Security.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonEntity save(PersonEntity onePerson) {
        return personRepository.save(onePerson);
    }

    @Override
    public List<PersonEntity> findAll() {
        return personRepository.findAll() ;
    }
}
