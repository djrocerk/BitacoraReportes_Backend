package com.reportesclaro.Service.Implemt;

import com.reportesclaro.Entity.TableTickesWavenetEntity;
import com.reportesclaro.Repository.TableTickesWavenetRepository;
import com.reportesclaro.Service.TableTickesWavenetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TableTickesWavenetImpl implements TableTickesWavenetService {


    @Autowired
    private TableTickesWavenetRepository tableTickesWavenetRepository;


    @Override
    public List<TableTickesWavenetEntity> findAll() {
        return tableTickesWavenetRepository.findAll();
    }

    @Override
    public TableTickesWavenetEntity getTableWavenetById(Long id_Wavenet) {
        return tableTickesWavenetRepository.findById(id_Wavenet).get();
    }

    @Override
    public void saveTableWavenet(TableTickesWavenetEntity tableTickesWavenetEntity) {
        tableTickesWavenetRepository.save(tableTickesWavenetEntity);
    }

    @Override
    public TableTickesWavenetEntity save(TableTickesWavenetEntity tableTickesWavenetEntity) {
        return tableTickesWavenetRepository.save(tableTickesWavenetEntity);
    }

    @Override
    public TableTickesWavenetEntity updateTableWavenetById(Long id_Wavenet) {
        return tableTickesWavenetRepository.findById(id_Wavenet).get();
    }
}
