package com.reportesclaro.Repository;

import com.reportesclaro.Entity.ApisWavenetEntity;
import com.reportesclaro.Entity.MicroservicesClaroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApisWavenetRepository extends JpaRepository<ApisWavenetEntity, Long> {
    List<ApisWavenetEntity> findByMicroservicesClaro(MicroservicesClaroEntity microservicesClaro);
}
