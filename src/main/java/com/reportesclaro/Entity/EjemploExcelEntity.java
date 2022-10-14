package com.reportesclaro.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EjemploExcel")
public class EjemploExcelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Excel;

    private String nombre_Excel;

    private String apellido_Excel;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fecha_Nacimiento;

    private String telefono;

    private String lugar_Nacimiento;

}
