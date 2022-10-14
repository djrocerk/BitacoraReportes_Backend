package com.reportesclaro.Controller;


import com.reportesclaro.Entity.TableIncidentsEntity;
import com.reportesclaro.Service.TableIncidentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/tableincidents"})
public class TableIncidentsController {

    @Autowired
    private TableIncidentsService tableIncidentsService;


    @GetMapping(value = {"/list"})
    public List<TableIncidentsEntity> tableIncidentsEntityList(){
        return tableIncidentsService.findAll();
    }

    @GetMapping(value = {"/list/{id_Reporte}"})
    public ResponseEntity<TableIncidentsEntity> getTableIncidents(@PathVariable Long id_Reporte){
        try{
            TableIncidentsEntity tableIncidentsEntity = tableIncidentsService.getTableIncidentsById(id_Reporte);
            return new ResponseEntity<TableIncidentsEntity>(tableIncidentsEntity, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<TableIncidentsEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = {"/create"})
    public ResponseEntity<String> save(@RequestBody TableIncidentsEntity tableIncidentsEntity){
        tableIncidentsService.save(tableIncidentsEntity);
        return new ResponseEntity<>("Se creo con exito los datos", HttpStatus.OK);
    }

    @PostMapping(value = {"/update"})
    public ResponseEntity<TableIncidentsEntity> updateTable(@RequestBody TableIncidentsEntity tableIncidentsEntity){
        try{
            TableIncidentsEntity tableIncidentsEntityExistente = tableIncidentsService.updateTableById(tableIncidentsEntity.getId_Reporte_Ivr());
            tableIncidentsEntityExistente.setCategorizacion_Ivr(tableIncidentsEntity.getCategorizacion_Ivr());
            tableIncidentsEntityExistente.setCausaIncidente_Ivr(tableIncidentsEntity.getCausaIncidente_Ivr());
            tableIncidentsEntityExistente.setDescripcionIncidente_Ivr(tableIncidentsEntity.getDescripcionIncidente_Ivr());
            tableIncidentsEntityExistente.setDuenoApi_Ivr(tableIncidentsEntity.getDuenoApi_Ivr());
            tableIncidentsEntityExistente.setEstado_Ivr(tableIncidentsEntity.getEstado_Ivr());
            tableIncidentsEntityExistente.setFecha_Hora_Incidente_Ivr(tableIncidentsEntityExistente.getFecha_Hora_Incidente_Ivr());
            tableIncidentsEntityExistente.setFechaHoraSolucion_Ivr(tableIncidentsEntity.getFechaHoraSolucion_Ivr());
            tableIncidentsEntityExistente.setId_Wavenet_Ivr(tableIncidentsEntity.getId_Wavenet_Ivr());
            tableIncidentsEntityExistente.setIncidente_Ivr(tableIncidentsEntity.getIncidente_Ivr());
            tableIncidentsEntityExistente.setIngN2_Ivr(tableIncidentsEntity.getIngN2_Ivr());
            tableIncidentsEntityExistente.setRequerimiento_Ivr(tableIncidentsEntity.getRequerimiento_Ivr());
            tableIncidentsEntityExistente.setResolutor_Ivr(tableIncidentsEntity.getResolutor_Ivr());
            tableIncidentsEntityExistente.setServicioAfectado_Ivr(tableIncidentsEntity.getServicioAfectado_Ivr());
            tableIncidentsEntityExistente.setSolucion_Ivr(tableIncidentsEntity.getSolucion_Ivr());
            tableIncidentsEntityExistente.setTipo_Ivr(tableIncidentsEntity.getTipo_Ivr());
            tableIncidentsService.saveTable(tableIncidentsEntityExistente);
            return new ResponseEntity<TableIncidentsEntity>(HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<TableIncidentsEntity>( HttpStatus.NOT_FOUND);
        }
    }
}
