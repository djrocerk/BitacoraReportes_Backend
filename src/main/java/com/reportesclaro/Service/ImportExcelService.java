package com.reportesclaro.Service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImportExcelService {

    ResponseEntity<?> saveExcel(MultipartFile file, String type) throws IOException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException;
}
