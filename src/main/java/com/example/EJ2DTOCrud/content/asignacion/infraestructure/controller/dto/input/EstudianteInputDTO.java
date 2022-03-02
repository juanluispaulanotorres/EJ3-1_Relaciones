package com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input;

import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Estudiante_asignatura;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Persona;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Profesor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class EstudianteInputDTO implements Serializable {
    private String id;
    //private Persona persona;
    private int num_hours_week;
    private String coments;
    //private Profesor profesor;
    private String branch;
    //List<Estudiante_asignatura> estudios;
}
