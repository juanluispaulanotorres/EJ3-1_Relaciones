package com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Estudiante;

import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Estudiante;
import lombok.Data;
import java.io.Serializable;

@Data
public class EstudianteSingularOutputDTO extends EstudianteOutputDTO implements Serializable {
    private String id;
    private int num_hours_week;
    private String coments;
    private String branch;
    private String idPersona;

    public EstudianteSingularOutputDTO(Estudiante estudiante) {
        setId(estudiante.getId());
        setNum_hours_week(estudiante.getNum_hours_week());
        setComents(estudiante.getComents());
        setBranch(estudiante.getBranch());
        setIdPersona(estudiante.getPersona().getIdPersona());
    }
}
