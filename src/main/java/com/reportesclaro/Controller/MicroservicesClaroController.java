package com.reportesclaro.Controller;

import com.reportesclaro.Entity.MicroservicesClaroEntity;
import com.reportesclaro.Service.MicroservicesClaroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/microserivesclaro"})
public class MicroservicesClaroController {

    @Autowired
    private MicroservicesClaroService microservicesClaroService;

    @GetMapping(value = {"/list"})
    public List<MicroservicesClaroEntity> microservicesClaroEntities(){
        return microservicesClaroService.findAll();

    }

    @PostMapping(value = {"/create"})
    public ResponseEntity<String> save(@RequestBody MicroservicesClaroEntity microservicesClaroEntity){
        microservicesClaroService.save(microservicesClaroEntity);
        return new ResponseEntity<>("Se creo con exito los datos", HttpStatus.OK);
    }

    @PostMapping(value = {"/update"})
    public ResponseEntity<MicroservicesClaroEntity> updateMicroservice(@RequestBody MicroservicesClaroEntity microservicesClaroEntity){
        try{
            MicroservicesClaroEntity microservicesClaroExistente = microservicesClaroService.updateMicroserviceClaro(microservicesClaroEntity.getId());

            microservicesClaroExistente.setName_MicroservicesClaro(microservicesClaroEntity.getName_MicroservicesClaro());
            microservicesClaroService.save(microservicesClaroExistente);
            return new ResponseEntity<MicroservicesClaroEntity>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<MicroservicesClaroEntity>(HttpStatus.NOT_FOUND);

        }
    }

    @DeleteMapping(value = {"/delete/{id}"})
    public ResponseEntity<String> deleteMicroservice(@PathVariable Long id){
        microservicesClaroService.deleteMicroserviceClaro(id);
        return new ResponseEntity<>("Se elimino con exito", HttpStatus.OK);
    }
}
