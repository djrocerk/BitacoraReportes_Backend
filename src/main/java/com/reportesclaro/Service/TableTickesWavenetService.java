package com.reportesclaro.Service;

import com.reportesclaro.Entity.TableTickesWavenetEntity;

import java.util.List;

public interface TableTickesWavenetService {

    public List<TableTickesWavenetEntity> findAll();

    public TableTickesWavenetEntity getTableWavenetById(Long id_Wavenet);

    public void saveTableWavenet(TableTickesWavenetEntity tableTickesWavenetEntity);

    TableTickesWavenetEntity save(TableTickesWavenetEntity tableTickesWavenetEntity);

    public TableTickesWavenetEntity updateTableWavenetById(Long id_Wavenet);

}
