package com.reportesclaro.Service;

import com.reportesclaro.Entity.TableIncidentsEntity;

import java.util.List;

public interface TableIncidentsService {

    public List<TableIncidentsEntity> findAll();

    public TableIncidentsEntity getTableIncidentsById(Long id_Reporte);

    public void saveTable(TableIncidentsEntity tableIncidentsEntity);

    TableIncidentsEntity save(TableIncidentsEntity tableIncidentsEntity);

    public TableIncidentsEntity updateTableById(Long id_Reporte);
}
