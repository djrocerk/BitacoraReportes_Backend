package com.reportesclaro.Service.Implemt;

import com.reportesclaro.Entity.TableIncidentsEntity;
import com.reportesclaro.Entity.TableTickesWavenetEntity;
import com.reportesclaro.Repository.TableIncidentsRepository;
import com.reportesclaro.Repository.TableTickesWavenetRepository;
import com.reportesclaro.Service.ImportExcelService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;

@Service
public class ImportExceImpl implements ImportExcelService {

    private Path tempDir;
    private MultipartFile file;
    private File tempFile;
    private Workbook workbook;

    @Autowired
    private TableTickesWavenetRepository tableTickesWavenetRepository;

    @Autowired
    private TableIncidentsRepository tableIncidentsRepository;

    public ImportExceImpl() throws IOException {
        this.tempDir = Files.createTempDirectory("");
    }


    private void excelData() throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
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

    private void saveDataWavenet(Sheet sheet){
        try {
            for (Row row : sheet) {
                if (row.getRowNum() > 0) {
                    String ticket = row.getCell(0).getStringCellValue();
                    System.out.println("ticket: " + ticket);
//                    String fechaInicio = row.getCell(1).getStringCellValue();
//                    String fechaFinal = row.getCell(2).getStringCellValue();
                    Date fechaInicio = row.getCell(1).getDateCellValue();
                    Date fechaFinal = row.getCell(2).getDateCellValue();
                    String servicioAfectado = row.getCell(3).getStringCellValue();
                    String duenoApi = row.getCell(4).getStringCellValue();
                    String descriFalla = row.getCell(5).getStringCellValue();
                    String masivo = row.getCell(6).getStringCellValue();
                    String comentarioCierre = row.getCell(7).getStringCellValue();
                    String causaIncidente = row.getCell(8).getStringCellValue();
                    String reporteFalla = row.getCell(9).getStringCellValue();
                    String planesAccion = row.getCell(10).getStringCellValue();
                    String resolutor = row.getCell(11).getStringCellValue();
                    String gerencia = row.getCell(12).getStringCellValue();
                    String analista = row.getCell(13).getStringCellValue();

                    TableTickesWavenetEntity tableTickesWavenetEntity = new TableTickesWavenetEntity();
                    tableTickesWavenetEntity.setWev_Ticked(ticket);
                    tableTickesWavenetEntity.setWev_Fecha_Inicial(fechaInicio);
                    tableTickesWavenetEntity.setWev_Fecha_Final(fechaFinal);
                    tableTickesWavenetEntity.setWev_Servicio_Afectado(servicioAfectado);
                    tableTickesWavenetEntity.setWev_Dueno_Api(duenoApi);
                    tableTickesWavenetEntity.setWev_Descripcion_Falla(descriFalla);
                    tableTickesWavenetEntity.setWev_Masivo(masivo);
                    tableTickesWavenetEntity.setWev_Comen_Cierre(comentarioCierre);
                    tableTickesWavenetEntity.setWev_Causa_Inicidente(causaIncidente);
                    tableTickesWavenetEntity.setWev_Reporte_Falla(reporteFalla);
                    tableTickesWavenetEntity.setWev_Planes_Accion(planesAccion);
                    tableTickesWavenetEntity.setWev_Resolutor(resolutor);
                    tableTickesWavenetEntity.setWev_Gerencia(gerencia);
                    tableTickesWavenetEntity.setWev_Ing_N2(analista);
                    tableTickesWavenetRepository.save(tableTickesWavenetEntity);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void saveIncidenteIvr(Sheet sheet){
        try {
            for (Row row : sheet) {
                if (row.getRowNum() > 0) {

                    String tipoIvr = row.getCell(0).getStringCellValue();
                    String requerimiento = row.getCell(1).getStringCellValue();
                    String incidente = row.getCell(2).getStringCellValue();
                    String idWavenet = row.getCell(3).getStringCellValue();
                    String duenoApi1 = row.getCell(4).getStringCellValue();
                    Date fechaHoraInicial = row.getCell(5).getDateCellValue();
                    Date fechaHoraFinal = row.getCell(6).getDateCellValue();
                    String estado = row.getCell(7).getStringCellValue();
                    String resolutor = row.getCell(8).getStringCellValue();
                    String servicioAfectado1 = row.getCell(9).getStringCellValue();
                    String categorizacion = row.getCell(10).getStringCellValue();
                    String descripcionIncidente = row.getCell(11).getStringCellValue();
                    String causaIncidente = row.getCell(12).getStringCellValue();
                    String solucion1 = row.getCell(13).getStringCellValue();
                    String analista1 = row.getCell(14).getStringCellValue();

                    TableIncidentsEntity tableIncidentsEntity = new TableIncidentsEntity();
                    tableIncidentsEntity.setTipo_Ivr(tipoIvr);
                    tableIncidentsEntity.setRequerimiento_Ivr(requerimiento);
                    tableIncidentsEntity.setIncidente_Ivr(incidente);
                    tableIncidentsEntity.setId_Wavenet_Ivr(idWavenet);
                    tableIncidentsEntity.setDuenoApi_Ivr(duenoApi1);
                    tableIncidentsEntity.setFecha_Hora_Incidente_Ivr(fechaHoraInicial);
                    tableIncidentsEntity.setFechaHoraSolucion_Ivr(fechaHoraFinal);
                    tableIncidentsEntity.setEstado_Ivr(estado);
                    tableIncidentsEntity.setResolutor_Ivr(resolutor);
                    tableIncidentsEntity.setServicioAfectado_Ivr(servicioAfectado1);
                    tableIncidentsEntity.setCategorizacion_Ivr(categorizacion);
                    tableIncidentsEntity.setDescripcionIncidente_Ivr(descripcionIncidente);
                    tableIncidentsEntity.setCausaIncidente_Ivr(causaIncidente);
                    tableIncidentsEntity.setSolucion_Ivr(solucion1);
                    tableIncidentsEntity.setIngN2_Ivr(analista1);
                    // FALTA TERMINAR PARA LLAMAR DE ENTIDAD A EXCEL

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public ResponseEntity<?> saveExcel(MultipartFile files, String type) throws IOException, InvalidFormatException {
        this.file = files;
        this.excelData();
        for (Sheet sheet1 : workbook) {
            switch (type) {
                case "wavenet":
                    this.saveDataWavenet(sheet1);
                    break;

                case "ivrIncidente":
                    this.saveIncidenteIvr(sheet1);
                    break;


            }
        }
        return new ResponseEntity<Object>("Archivo creado correctamente", HttpStatus.CREATED);
    }
}
