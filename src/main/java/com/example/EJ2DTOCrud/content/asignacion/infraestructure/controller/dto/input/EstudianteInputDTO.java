package com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input;

import lombok.Data;

import java.io.Serializable;

@Data
public class EstudianteInputDTO implements Serializable {
    private String id;
    private String idPersona;
    private int num_hours_week;
    private String coments;
    //private Profesor profesor;
    private String branch;
    //List<Estudiante_asignatura> estudios;
}
