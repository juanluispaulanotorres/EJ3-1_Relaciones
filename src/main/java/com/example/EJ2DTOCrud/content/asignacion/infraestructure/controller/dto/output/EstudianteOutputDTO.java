package com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output;

import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Estudiante;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Estudiante_asignatura;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Persona;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Profesor;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class EstudianteOutputDTO implements Serializable {
    private String id;
    //private Persona persona;
    private int num_hours_week;
    private String coments;
    //private Profesor profesor;
    private String branch;
    //List<Estudiante_asignatura> estudios;

    public EstudianteOutputDTO(Estudiante estudiante) {
        setId(estudiante.getId());
        //setPersona(estudiante.getPersona());
        setNum_hours_week(estudiante.getNum_hours_week());
        setComents(estudiante.getComents());
        //setProfesor(estudiante.getProfesor());
        setBranch(estudiante.getBranch());
        //setEstudios(estudiante.getEstudios());
    }
}