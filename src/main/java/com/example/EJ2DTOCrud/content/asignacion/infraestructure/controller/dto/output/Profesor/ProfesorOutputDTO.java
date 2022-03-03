package com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Profesor;

import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Persona.PersonaProfesorOutput;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Persona;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Profesor;
import lombok.Data;
import java.io.Serializable;

@Data
public class ProfesorOutputDTO implements Serializable {
    private String id;
    private String coments;
    private String branch;
    private Persona persona;
    private PersonaProfesorOutput personaProfesorOutput;

    public ProfesorOutputDTO(Profesor profesor) {
        setId(profesor.getId());
        setComents(profesor.getComents());
        setBranch(profesor.getBranch());
        setPersonaProfesorOutput(new PersonaProfesorOutput(profesor.getPersona()));
    }
}