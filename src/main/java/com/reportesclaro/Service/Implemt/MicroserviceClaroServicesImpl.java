package com.reportesclaro.Service.Implemt;

import com.reportesclaro.Entity.MicroservicesClaroEntity;
import com.reportesclaro.Repository.MicroservicesClaroRepository;
import com.reportesclaro.Service.MicroservicesClaroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MicroserviceClaroServicesImpl implements MicroservicesClaroService {

    @Autowired
    private MicroservicesClaroRepository microservicesClaroRepository;

    @Override
    public List<MicroservicesClaroEntity> findAll() {
        return microservicesClaroRepository.findAll();

    }

    @Override
    public MicroservicesClaroEntity save(MicroservicesClaroEntity microservicesClaroEntity) {
        return microservicesClaroRepository.save(microservicesClaroEntity);
    }

    @Override
    public MicroservicesClaroEntity updateMicroserviceClaro(Long id) {
        return microservicesClaroRepository.findById(id).get();
    }

    @Override
    public void deleteMicroserviceClaro(Long id) {
       microservicesClaroRepository.deleteById(id);
    }
}
