package com.reportesclaro.Security.Repository;

import com.reportesclaro.Security.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface RolRepository extends JpaRepository<RoleEntity, Long> {


}
