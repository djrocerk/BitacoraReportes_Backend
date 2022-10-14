package com.reportesclaro.Controller;

import com.reportesclaro.Entity.TableTickesWavenetEntity;
import com.reportesclaro.Repository.TableTickesWavenetRepository;
import com.reportesclaro.Service.Implemt.ExportExcelWavenetImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/excel/export"})
public class ExcelExportWavenetController {

    @Autowired
    private TableTickesWavenetRepository tableTickesWavenetRepository;

    @RequestMapping("/wavenet")
    @ResponseBody
    public String exportToExcelWavenet(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headervalue = "attachment; filename= BitacoraIncidenteWavenet.xlsx";

        response.setHeader(headerKey, headervalue);
        List<TableTickesWavenetEntity> tableTickesWavenetEntityList = tableTickesWavenetRepository.findAll();
        ExportExcelWavenetImpl exportExcelmpl = new ExportExcelWavenetImpl(tableTickesWavenetEntityList);
        exportExcelmpl.exportWavenet(response);
        return "todo";
    }
}
