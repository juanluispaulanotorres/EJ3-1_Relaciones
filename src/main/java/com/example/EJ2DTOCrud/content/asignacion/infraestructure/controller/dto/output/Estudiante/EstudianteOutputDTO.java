package com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Estudiante;

import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Persona.PersonaEstudianteOutputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Estudiante;
import lombok.Data;
import java.io.Serializable;

@Data
public class EstudianteOutputDTO implements Serializable {
    private String id;
    private int num_hours_week;
    private String coments;
    //private Profesor profesor;
    private String branch;
    private PersonaEstudianteOutputDTO personaEstudianteOutputDTO;
    //List<Estudiante_asignatura> estudios;

    public EstudianteOutputDTO(Estudiante estudiante) {
        setId(estudiante.getId());
        setPersonaEstudianteOutputDTO(new PersonaEstudianteOutputDTO(estudiante.getPersona()));
        setNum_hours_week(estudiante.getNum_hours_week());
        setComents(estudiante.getComents());
        //setProfesor(estudiante.getProfesor());
        setBranch(estudiante.getBranch());
        //setEstudios(estudiante.getEstudios());
    }

    public EstudianteOutputDTO() {
    }
}