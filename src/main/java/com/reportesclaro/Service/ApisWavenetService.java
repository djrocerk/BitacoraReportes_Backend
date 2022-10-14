package com.reportesclaro.Service;

import com.reportesclaro.Entity.ApisWavenetEntity;

import java.util.List;

public interface ApisWavenetService {

    List<ApisWavenetEntity> findAll();

    List<ApisWavenetEntity> getApisWavenetById(Long id);

    ApisWavenetEntity save(ApisWavenetEntity apisWavenetEntity);

    ApisWavenetEntity updateApisWavenet(Long id);

    void deleteApisWavenet(Long id);
}
