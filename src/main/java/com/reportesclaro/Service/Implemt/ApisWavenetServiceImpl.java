package com.reportesclaro.Service.Implemt;

import com.reportesclaro.Entity.ApisWavenetEntity;
import com.reportesclaro.Entity.MicroservicesClaroEntity;
import com.reportesclaro.Repository.ApisWavenetRepository;
import com.reportesclaro.Repository.MicroservicesClaroRepository;
import com.reportesclaro.Service.ApisWavenetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApisWavenetServiceImpl implements ApisWavenetService {

    @Autowired
    private ApisWavenetRepository apisWavenetRepository;

    @Autowired
    private MicroservicesClaroRepository microservicesClaroRepository;

    @Override
    public List<ApisWavenetEntity> findAll() {
        return apisWavenetRepository.findAll();
    }

    @Override
    public List<ApisWavenetEntity> getApisWavenetById(Long id) {
        Optional<MicroservicesClaroEntity> microservicesClaroEntityOptional = microservicesClaroRepository.findById(id);
        MicroservicesClaroEntity microservicesClaroEntity = null;
        if(microservicesClaroEntityOptional.isPresent()) microservicesClaroEntity = microservicesClaroEntityOptional.get();
        List<ApisWavenetEntity> apisWavenetEntity = apisWavenetRepository.findByMicroservicesClaro(microservicesClaroEntity);
        System.out.println("apisWavenetEntity " + apisWavenetEntity.size());
        return apisWavenetEntity;
    }

    @Override
    public ApisWavenetEntity save(ApisWavenetEntity apisWavenetEntity) {
        return apisWavenetRepository.save(apisWavenetEntity);
    }

    @Override
    public ApisWavenetEntity updateApisWavenet(Long id) {
        return apisWavenetRepository.findById(id).get();
    }

    @Override
    public void deleteApisWavenet(Long id) {
        apisWavenetRepository.deleteById(id);
    }
}
