package com.reportesclaro.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tabla_IncidentesIvr")
public class TableIncidentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Reportes_Ivr")
    private Long id_Reporte_Ivr;

    @Column(length = 500 , name = "Tipo_Ivr")
    private String tipo_Ivr;

    @Column(length = 500, name = "Requerimiento_Ivr")
    private String requerimiento_Ivr;

    @Column(length = 500, name = "Incidente_Ivr")
    private String incidente_Ivr;

    @Column(length = 500, name = "Id_Wavenet_Ivr")
    private String id_Wavenet_Ivr;

    @Column(length = 500, name = "DueñoApi_Ivr")
    private String duenoApi_Ivr;

    @Column(length = 500, name = "MicroservicioClaro_Ivr")
    private String microservicioClaro_Ivr;

    @Column(length = 500, name = "ApisWavenet_Ivr")
    private String apisWavenet_Ivr;

    @Column(name = "Fecha_Hora_Incidente_Ivr")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fecha_Hora_Incidente_Ivr;

    @Column(name = "Fecha_Hora_Solucion_Ivr")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaHoraSolucion_Ivr;

    @Column(length = 500, name = "Estado_Ivr")
    private String estado_Ivr;

    @Column(length = 500, name = "Resolutor_Ivr")
    private String resolutor_Ivr;

    @Column(length = 50, name = "Servicio_Afectado_Ivr")
    private String servicioAfectado_Ivr;

    @Column(length = 500, name = "Categorizacion_Ivr")
    private String categorizacion_Ivr;

    @Column(length = 5000, name = "Descripcion_Incidente_Ivr")
    private String descripcionIncidente_Ivr;

    @Column(length = 600, name = "CausaIncidente_Ivr")
    private String causaIncidente_Ivr;

    @Column(length = 5000, name = "Solucion_Ivr")
    private String solucion_Ivr;

    @Column(length = 100, name = "IngN2_Ivr")
    private String ingN2_Ivr;

}
