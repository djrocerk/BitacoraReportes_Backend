package com.reportesclaro.Security.Repository;

import com.reportesclaro.Security.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {



    public Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);
}
