package com.reportesclaro.Service.Implemt;

import com.reportesclaro.Entity.TableIncidentsEntity;
import com.reportesclaro.Repository.TableIncidentsRepository;
import com.reportesclaro.Service.TableIncidentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableIncidentsServiceImpl implements TableIncidentsService {

    @Autowired
    private TableIncidentsRepository tableIncidentsRepository;


    @Override
    public List<TableIncidentsEntity> findAll() {
        return tableIncidentsRepository.findAll();
    }

    @Override
    public TableIncidentsEntity getTableIncidentsById(Long id_Reporte) {
        return tableIncidentsRepository.findById(id_Reporte).get();
    }

    @Override
    public void saveTable(TableIncidentsEntity tableIncidentsEntity) {
        tableIncidentsRepository.save(tableIncidentsEntity);
    }

    @Override
    public TableIncidentsEntity save(TableIncidentsEntity tableIncidentsEntity) {
        return tableIncidentsRepository.save(tableIncidentsEntity);
    }

    @Override
    public TableIncidentsEntity updateTableById(Long id_Reporte) {
        return tableIncidentsRepository.findById(id_Reporte).get();
    }
}
