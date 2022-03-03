package com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input;

import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Persona;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProfesorInputDTO implements Serializable {
    private String id;
    private String idPersona;
    private String coments;
    private String branch;
    private Persona persona;
}
