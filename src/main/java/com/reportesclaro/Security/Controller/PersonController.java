package com.reportesclaro.Security.Controller;

import com.reportesclaro.Security.Entity.PersonEntity;
import com.reportesclaro.Security.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/person"})
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = {"/list"})
    public List<PersonEntity> personEntityList(){
        return personService.findAll();
    }


}
