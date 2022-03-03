package com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo;

import com.example.EJ2DTOCrud.StringPrefixedSequenceIdGenerator;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.ProfesorInputDTO;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Profesores")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idProfesor")
    @GenericGenerator(
            name = "idProfesor",
            strategy = StringPrefixedSequenceIdGenerator.PACKAGE,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "Prof"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%01d")
            }
    )
    @Column(name = "idProfesor")
    private String id;

    @Column(name = "coments")
    private String coments;

    @Column(name = "branch")
    @NonNull
    private String branch;

    //@Column(name = "id_persona")
    @OneToOne(fetch = FetchType.LAZY)
    private Persona persona;

    /*@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;*/

    public Profesor(){}

    public Profesor(ProfesorInputDTO profesorInputDTO) {
        if (profesorInputDTO == null)
            return;

        setId(profesorInputDTO.getId());
        setComents(profesorInputDTO.getComents());
        setBranch(profesorInputDTO.getBranch());
        //setPersona(profesorInputDTO.getPersona());
    }
}