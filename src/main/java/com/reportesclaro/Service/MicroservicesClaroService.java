package com.reportesclaro.Service;

import com.reportesclaro.Entity.MicroservicesClaroEntity;

import java.util.List;

public interface MicroservicesClaroService {

    public List<MicroservicesClaroEntity> findAll();

    MicroservicesClaroEntity save(MicroservicesClaroEntity microservicesClaroEntity);

    public MicroservicesClaroEntity updateMicroserviceClaro(Long id);

    public void deleteMicroserviceClaro(Long id);
}
