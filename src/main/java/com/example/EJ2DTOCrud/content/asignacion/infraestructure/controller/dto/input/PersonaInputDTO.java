package com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input;

import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Estudiante;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class PersonaInputDTO implements Serializable {
    private String id_persona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    private Estudiante estudiate;
}


