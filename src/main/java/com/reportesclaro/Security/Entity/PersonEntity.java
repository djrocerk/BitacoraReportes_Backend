package com.reportesclaro.Security.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // Le decimos a java que esta clase es una entidad
@Table(name = "Persona") // Para que nos cree una tabla en la basa de datos
public class PersonEntity implements Serializable {

    private static final long serialVersionUID = 3499113360580787796L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Le decimos que va ser de tipo autoincrementable
    @Column(name = "id_Persona")
    private Long idPersona;

    @Column(length =60, name = "per_Nombre")
    private String per_Name;


    @Column(length =60, name = "per_Apellido")
    private String per_LastName;


    @Column(length =50, name = "per_TipoDocumento")
    private String per_DocumentType;

    @Column(length =50, name = "per_NumeroDocumento")
    private String per_DocumentNumber;

    @Column(length =60, name = "per_Ciudad")
    private String per_City;

    @Column(length =60, name = "per_Estado")
    private String per_State;

    @Column(name = "per_FechaModificacion")
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT-5")
    private Date per_Modificationdate;




}
