package com.reportesclaro.Security.Repository;

import com.reportesclaro.Security.Entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
