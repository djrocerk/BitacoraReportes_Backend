package com.reportesclaro.Service.Implemt;

import com.reportesclaro.Entity.EjemploExcelEntity;
import com.reportesclaro.Repository.EjemploExcelRepository;
import com.reportesclaro.Service.EjemploExcelService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;

@Service
public class EjemploExcelImpl implements EjemploExcelService {

    private Path tempDir;

    private MultipartFile file;

    private File tempFile;

    private Workbook workbook;

    @Autowired
    private EjemploExcelRepository ejemploExcelRepository;

    public EjemploExcelImpl() throws IOException {
        this.tempDir = Files.createTempDirectory("");
    }

    private void copiarData() throws IOException, InvalidFormatException {
        Arrays.asList(file).stream().forEach(fil -> {
            try {
                Files.copy(fil.getInputStream(), this.tempDir.resolve(fil.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.tempFile = this.tempDir.resolve(this.file.getOriginalFilename()).toFile();
        this.workbook = WorkbookFactory.create(tempFile);
    }

    private void saveExcelbitacora(Sheet sheet) {
        for (Row row : sheet){
            if (row.getRowNum() > 0) {

                String nombre = row.getCell(0).getStringCellValue();
                String apellido = row.getCell(1).getStringCellValue();
                Date nacimiento = row.getCell(2).getDateCellValue();
                Cell cell = row.getCell(3);
                cell.setCellType(CellType.STRING);
                String phone = cell.getStringCellValue();
                String lugar = row.getCell(4).getStringCellValue();

                EjemploExcelEntity excel = new EjemploExcelEntity();
                excel.setNombre_Excel(nombre);
                excel.setApellido_Excel(apellido);
                excel.setFecha_Nacimiento(nacimiento);
                excel.setTelefono(phone);
                excel.setLugar_Nacimiento(lugar);

                ejemploExcelRepository.save(excel);
            }
        }


    }

    @Override
    public ResponseEntity<?> saveExcelEjemplo(MultipartFile files, String type) throws IOException, InvalidFormatException {
        this.file = files;
        this.copiarData();
        for (Sheet sheet1 : workbook) {
            switch (type) {
                case "Ejemplo":
                    this.saveExcelbitacora(sheet1);
                    break;
            }
        }
        return new ResponseEntity<Object>("Archivo creado correctamente", HttpStatus.CREATED);
    }
}
