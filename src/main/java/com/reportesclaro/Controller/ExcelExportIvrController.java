package com.reportesclaro.Controller;

import com.reportesclaro.Entity.TableIncidentsEntity;
import com.reportesclaro.Repository.TableIncidentsRepository;
import com.reportesclaro.Service.Implemt.ExportExcelIvrImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/excel/export"})
public class ExcelExportIvrController {

    @Autowired
    private TableIncidentsRepository tableIncidentsRepository;

    @RequestMapping("/ivr")
    @ResponseBody
    public String exportToExcelIvr(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headervalue = "attachment; filename= BitacoraIncidenteIvr.xlsx";

        response.setHeader(headerKey, headervalue);
        List<TableIncidentsEntity> tableIncidentsEntityList = tableIncidentsRepository.findAll();
        ExportExcelIvrImpl exportExcelIvr = new ExportExcelIvrImpl(tableIncidentsEntityList);
        exportExcelIvr.exportIvr(response);
        return "all good";
    }
}
