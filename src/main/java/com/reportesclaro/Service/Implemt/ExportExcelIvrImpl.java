package com.reportesclaro.Service.Implemt;

import com.reportesclaro.Entity.TableIncidentsEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class ExportExcelIvrImpl {

    private XSSFWorkbook workbook;

    private XSSFSheet sheet;

    private List<TableIncidentsEntity> tableIncidentsEntityList;

    public ExportExcelIvrImpl(List<TableIncidentsEntity> tableIncidentsEntityList1){
        this.tableIncidentsEntityList = tableIncidentsEntityList1;
        workbook = new XSSFWorkbook();
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        CreationHelper creationHelper = workbook.getCreationHelper();

        if(value instanceof Long){
            cell.setCellValue((Long) value);
        } else if(value instanceof  Integer) {
            cell.setCellValue((Integer) value);
        } else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if(value instanceof Timestamp){
            style.setDataFormat(creationHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm"));
            cell.setCellValue((Timestamp) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeHeaderLine() throws IOException{

        sheet = workbook.createSheet("ivr_Incidente");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();


        XSSFFont font = workbook.createFont();

        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        sheet.setAutoFilter(CellRangeAddress.valueOf("A1:N1"));

        style.setFont(font);
        style.setWrapText(true);
        font.setBold(true);
        font.setFontHeight(15);
        font.setFontName("Arial");

        row = sheet.createRow(0);
        createCell(row,0, "TIPO IVR", style);
        createCell(row,1, "REQUERIMIENTO", style);
        createCell(row,2, "INCIDENTE", style);
        createCell(row,3, "ID WAVENET", style);
        createCell(row,4, "DUEÑO API", style);
        createCell(row,5, "FECHA Y HORA DEL INCIDENTE", style);
        createCell(row,6, "FECHA Y HORA FINAL DEL INCIDENTE", style);
        createCell(row,7, "ESTADO", style);
        createCell(row,8, "RESOLUTOR", style);
        createCell(row,9, "SERVICIO AFECTADO", style);
        createCell(row,10, "CATEGORIZACION", style);
        createCell(row,11, "DESCRIPCION DEL NCIDENTE", style);
        createCell(row,12, "CAUSA INCIDENTE", style);
        createCell(row,13, "SOLUCIÓN", style);
        createCell(row,14, "ANALISTA", style);

    }

    private void writeDataLines() {
        CellStyle style1 = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(12);
        font.setFontName("Arial");
        style1.setFont(font);
        style1.setBorderRight(BorderStyle.MEDIUM);
        style1.setBorderLeft(BorderStyle.MEDIUM);
        style1.setBorderTop(BorderStyle.MEDIUM);
        style1.setBorderBottom(BorderStyle.MEDIUM);

        int rowCount = 1;

        for (TableIncidentsEntity tableIncidents: tableIncidentsEntityList){
            Row row = sheet.createRow(rowCount++);
            int columnCount=0;

            createCell(row, columnCount++, tableIncidents.getTipo_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getRequerimiento_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getIncidente_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getId_Wavenet_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getDuenoApi_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getFecha_Hora_Incidente_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getFechaHoraSolucion_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getEstado_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getResolutor_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getServicioAfectado_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getCategorizacion_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getDescripcionIncidente_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getCausaIncidente_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getCausaIncidente_Ivr(),style1);
            createCell(row, columnCount++, tableIncidents.getIngN2_Ivr(),style1);
        }
    }

    public void exportIvr(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

}
