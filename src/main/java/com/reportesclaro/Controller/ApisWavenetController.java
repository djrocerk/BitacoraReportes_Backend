package com.reportesclaro.Controller;

import com.reportesclaro.Entity.ApisWavenetEntity;
import com.reportesclaro.Service.ApisWavenetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/apiswavenet"})
public class ApisWavenetController {

    @Autowired
    private ApisWavenetService apisWavenetService;

    @GetMapping(value = {"/list"})
    public List<ApisWavenetEntity> apisWavenetEntities(){
        return apisWavenetService.findAll();
    }

    @GetMapping(value = {"/list/{id}"})
    public ResponseEntity<List<ApisWavenetEntity>> getApisWavenet(@PathVariable Long id) {
        List<ApisWavenetEntity> apisWavenetEntity = apisWavenetService.getApisWavenetById(id);
        return new ResponseEntity<>(apisWavenetEntity, HttpStatus.OK);
    }

    @PostMapping(value = {"/create"})
    public ResponseEntity<String> save(@RequestBody ApisWavenetEntity apisWavenetEntity){
        apisWavenetService.save(apisWavenetEntity);
        return new ResponseEntity<>("Se creo con exito los datos", HttpStatus.OK);
    }

    @PutMapping(value = {"/update/{id}"})
    public ResponseEntity<ApisWavenetEntity> updateApis(@RequestBody ApisWavenetEntity apisWavenetEntity, @PathVariable Long id){
        try{
            ApisWavenetEntity apisWavenetEntityExistente = apisWavenetService.updateApisWavenet(id);

            apisWavenetEntityExistente.setName_ApisWavenet(apisWavenetEntity.getName_ApisWavenet());
            apisWavenetEntityExistente.setMicroservicesClaro(apisWavenetEntity.getMicroservicesClaro());
            apisWavenetService.save(apisWavenetEntityExistente);
            return new ResponseEntity<ApisWavenetEntity>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ApisWavenetEntity>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = {"/delete/{id}"})
    public ResponseEntity<String> deleteApis(@PathVariable Long id){
        apisWavenetService.deleteApisWavenet(id);
        return new ResponseEntity<>("Se elimino con exito", HttpStatus.OK);
    }

}
