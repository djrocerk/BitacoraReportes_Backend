package com.reportesclaro.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tabla_Tickes_Wavenet")
public class TableTickesWavenetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Wavenet")
    private Long id_Wavenet;

    @Column(length = 500, name = "Wev_Ticked")
    private String wev_Ticked;

    @Column(name = "Wev_Fecha_Inicial")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private Date wev_Fecha_Inicial;

    @Column(name = "Wev_Fecha_Final")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date wev_Fecha_Final;

    @Column(length = 300, name = "Wev_Servicio_Afectado")
    private String wev_Servicio_Afectado;

    @Column(length = 300, name = "Wev_Dueno_Api")
    private String wev_Dueno_Api;

    @Column(length = 2500, name = "Wev_Descripcion_Falla")
    private String wev_Descripcion_Falla;

    @Column(length = 300, name = "Wev_Masivo")
    private String wev_Masivo;

    @Column(length = 300, name = "Wev_Comen_Cierre")
    private String wev_Comen_Cierre;

    @Column(length = 5000, name = "Wev_Causa_Inicidente")
    private String wev_Causa_Inicidente;

    @Column(length = 5000, name = "Wev_Reporte_Falla")
    private String wev_Reporte_Falla;

    @Column(length = 500, name = "Wev_Planes_Accion")
    private String wev_Planes_Accion;

    @Column(length = 500, name = "Wev_Resolutor")
    private String wev_Resolutor;

    @Column(length = 300, name = "Wev_Gerencia")
    private String wev_Gerencia;

    @Column(length = 100, name = "Wev_Ing_N2")
    private String wev_Ing_N2;

}
