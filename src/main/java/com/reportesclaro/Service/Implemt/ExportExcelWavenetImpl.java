package com.reportesclaro.Service.Implemt;

import com.reportesclaro.Entity.TableTickesWavenetEntity;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ExportExcelWavenetImpl {


    private XSSFWorkbook workbook;

    private XSSFSheet sheet;

    private List<TableTickesWavenetEntity> tableTickesWavenetEntityList;


    public ExportExcelWavenetImpl(List<TableTickesWavenetEntity> tableTickesWavenetEntityList1){
        this.tableTickesWavenetEntityList=tableTickesWavenetEntityList1;
        workbook = new XSSFWorkbook();
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell=row.createCell(columnCount);
        CreationHelper creationHelper = workbook.getCreationHelper();

        if(value instanceof Long) {
            cell.setCellValue((Long) value);
        }else if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if(value instanceof Timestamp) {
            style.setDataFormat(creationHelper.createDataFormat().getFormat("dd/mm/YYYY hh:mm"));
            cell.setCellValue((Timestamp) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeHeaderLine() throws IOException{

        sheet=workbook.createSheet("wavenet");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        CellStyle style2 = workbook.createCellStyle();



        XSSFFont font=workbook.createFont();
        XSSFFont fuente=workbook.createFont();
        XSSFFont fuenteD=workbook.createFont();

        style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setBorderBottom(BorderStyle.MEDIUM);
        sheet.setAutoFilter(CellRangeAddress.valueOf("A1:N1"));

//        sheet.autoSizeColumn(x);
//
//        if (sheet.getColumnWidth(x) == 0) {
//            // autosize failed use MIN_WIDTH
//            sheet.setColumnWidth(x, MIN_WIDTH);
//        }



        style.setFont(font);
        style.setWrapText(true);
        font.setBold(true);
        font.setFontHeight(15);
        font.setFontName("Arial");

        row = sheet.createRow(0);
        createCell(row,0,"TICKET",style);
        createCell(row,1,"FECHA Y HORA DEL INCIDENTE", style);
        createCell(row,2,"FECHA Y HORA FINAL DEL INCIDENTE",style);
        createCell(row,3,"SERVICIO-AFECTADO",style);
        createCell(row,4,"DUEÑO API",style);
        createCell(row,5,"DESCRIPCIÓN DE LA FALLA",style);
        createCell(row,6,"MASIVO",style);
        createCell(row,7,"COMENTARIO DE CIERRE",style);
        createCell(row,8,"CAUSA DEL INCIDENTE",style);
        createCell(row,9,"REPORTE DE FALLA",style);
        createCell(row,10,"PLANES DE ACCION",style);
        createCell(row,11,"RESOLUTOR",style);
        createCell(row,12,"GERENCIA",style);
        createCell(row,13,"ANALISTA",style);

    }

    private void writeDataLines() {
        CellStyle style1=workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setFontHeight(12);
        font.setFontName("Arial");
        style1.setBorderRight(BorderStyle.MEDIUM);
        style1.setBorderLeft(BorderStyle.MEDIUM);
        style1.setBorderTop(BorderStyle.MEDIUM);
        style1.setBorderBottom(BorderStyle.MEDIUM);
        //style1.setAlignment(HorizontalAlignment.LEFT);

        style1.setFont(font);

        int rowCount=1;

        for(TableTickesWavenetEntity wavenetEntity: tableTickesWavenetEntityList ){
            Row row = sheet.createRow(rowCount++);
            int columnCount=0;

            createCell(row, columnCount++, wavenetEntity.getWev_Ticked(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Fecha_Inicial(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Fecha_Final(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Servicio_Afectado(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Dueno_Api(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Descripcion_Falla(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Masivo(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Comen_Cierre(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Causa_Inicidente(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Reporte_Falla(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Planes_Accion(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Resolutor(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Gerencia(),style1);
            createCell(row, columnCount++, wavenetEntity.getWev_Ing_N2(),style1);
        }
    }

    public void exportWavenet(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


}
