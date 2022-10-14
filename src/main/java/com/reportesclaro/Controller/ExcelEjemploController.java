package com.reportesclaro.Controller;

import com.reportesclaro.Service.EjemploExcelService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/ejemplo")
public class ExcelEjemploController {

    @Autowired
    private EjemploExcelService ejemploExcelService;

    @PostMapping
    public ResponseEntity<?> cargarExcel(@RequestPart("file")MultipartFile file, @RequestParam String type)
           throws IOException, InvalidFormatException {
        return this.ejemploExcelService.saveExcelEjemplo(file, type);
    }

}
