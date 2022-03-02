package com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo;

import com.example.EJ2DTOCrud.StringPrefixedSequenceIdGenerator;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.EstudianteInputDTO;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Estudiantes")
public class Estudiante {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idEstudiante")
    @GenericGenerator(
            name = "idEstudiante",
            strategy = StringPrefixedSequenceIdGenerator.PACKAGE,
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "E"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%01d")
            }
    )
    @Column(name = "id")
    private String id;
    // NOTA: El "id" de la tabla persona es "int".       A TENER EN CUENTA
/*
    @JoinColumn(name = "persona_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Persona persona;
*/
    @Column(name = "num_horas")
    @NonNull
    private int num_hours_week;
    @Column(name = "coments")
    private String coments;
    /*@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private Profesor profesor;*/
    @Column(name = "branch")
    @NonNull
    private String branch;
    /*@ManyToMany
    List<Estudiante_asignatura> estudios;*/

    public Estudiante(){}

    public Estudiante (EstudianteInputDTO estudianteInputDTO) {
        setId(estudianteInputDTO.getId());
        //setPersona(estudianteInputDTO.getPersona());
        setNum_hours_week(estudianteInputDTO.getNum_hours_week());
        setComents(estudianteInputDTO.getComents());
        //setProfesor(estudianteInputDTO.getProfesor());
        setBranch(estudianteInputDTO.getBranch());
        //setEstudios(estudianteInputDTO.getEstudios());
    }
}
