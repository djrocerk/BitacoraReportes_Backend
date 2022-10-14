package com.reportesclaro.Controller;


import com.reportesclaro.Entity.TableTickesWavenetEntity;
import com.reportesclaro.Service.TableTickesWavenetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/tickesWavenet"})
public class TableTickesWavenetController {

    @Autowired
    private TableTickesWavenetService tableTickesWavenetService;

    @GetMapping(value = {"/list"})
    public List<TableTickesWavenetEntity> tableTickesWavenetEntityList(){
        return tableTickesWavenetService.findAll();
    }

    @GetMapping(value = {"/list/{id_wavenet}"})
    public ResponseEntity<TableTickesWavenetEntity> getTableWavenet(@PathVariable Long id_wavenet){
        try{
            TableTickesWavenetEntity tableTickesWavenetEntity = tableTickesWavenetService.getTableWavenetById(id_wavenet);
            return new ResponseEntity<TableTickesWavenetEntity>(tableTickesWavenetEntity, HttpStatus.OK);
        }catch (Exception exception) {
            return new ResponseEntity<TableTickesWavenetEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = {"/create"})
    public ResponseEntity<String> save(@RequestBody TableTickesWavenetEntity tableTickesWavenetEntity){
        System.out.println(tableTickesWavenetEntity.getWev_Fecha_Inicial());
        System.out.println(tableTickesWavenetEntity.getWev_Fecha_Final());
        tableTickesWavenetService.save(tableTickesWavenetEntity);
        return new ResponseEntity<>("Se registraron con exito", HttpStatus.OK);
    }

    @PostMapping(value = {"/update"})
     public ResponseEntity<?> updateTablet(@RequestBody TableTickesWavenetEntity tableTickesWavenetEntity){
        try {
            TableTickesWavenetEntity tableTickesWavenet = tableTickesWavenetService.updateTableWavenetById(tableTickesWavenetEntity.getId_Wavenet());
            tableTickesWavenet.setWev_Ticked(tableTickesWavenetEntity.getWev_Ticked());
            tableTickesWavenet.setWev_Fecha_Inicial(tableTickesWavenetEntity.getWev_Fecha_Inicial());
            tableTickesWavenet.setWev_Fecha_Final(tableTickesWavenetEntity.getWev_Fecha_Final());
            tableTickesWavenet.setWev_Servicio_Afectado(tableTickesWavenetEntity.getWev_Servicio_Afectado());
            tableTickesWavenet.setWev_Dueno_Api(tableTickesWavenetEntity.getWev_Dueno_Api());
            tableTickesWavenet.setWev_Descripcion_Falla(tableTickesWavenetEntity.getWev_Descripcion_Falla());
            tableTickesWavenet.setWev_Masivo(tableTickesWavenetEntity.getWev_Masivo());
            tableTickesWavenet.setWev_Comen_Cierre(tableTickesWavenetEntity.getWev_Comen_Cierre());
            tableTickesWavenet.setWev_Causa_Inicidente(tableTickesWavenetEntity.getWev_Causa_Inicidente());
            tableTickesWavenet.setWev_Reporte_Falla(tableTickesWavenetEntity.getWev_Reporte_Falla());
            tableTickesWavenet.setWev_Planes_Accion(tableTickesWavenetEntity.getWev_Planes_Accion());
            tableTickesWavenet.setWev_Resolutor(tableTickesWavenetEntity.getWev_Resolutor());
            tableTickesWavenet.setWev_Gerencia(tableTickesWavenetEntity.getWev_Gerencia());
            tableTickesWavenet.setWev_Ing_N2(tableTickesWavenetEntity.getWev_Ing_N2());
            tableTickesWavenetService.save(tableTickesWavenet);
            return new ResponseEntity<TableTickesWavenetEntity>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<TableTickesWavenetEntity>(HttpStatus.NOT_FOUND);
        }
   }

}
