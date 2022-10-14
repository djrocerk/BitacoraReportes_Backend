package com.reportesclaro.Controller;

import com.reportesclaro.Service.ImportExcelService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = {"/api/excel/import"})
public class ExcelImportController {

    @Autowired
    private ImportExcelService importExcelService;

    @PostMapping
    public ResponseEntity<?> importExcel(@RequestPart("file")MultipartFile file, @RequestParam String type)
            throws IOException, InvalidFormatException {
        return this.importExcelService.saveExcel(file, type);
    }
}
