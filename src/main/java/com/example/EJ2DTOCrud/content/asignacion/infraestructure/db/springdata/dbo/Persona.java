package com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo;

import com.example.EJ2DTOCrud.StringPrefixedSequenceIdGenerator;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.PersonaInputDTO;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPersona")
    @GenericGenerator(
            name = "idPersona",
            strategy = StringPrefixedSequenceIdGenerator.PACKAGE,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "P"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%01d")
            }
    )
    private String idPersona;
    @NonNull
    @Column(name = "usuario")
    private String usuario;
    @NonNull
    @Column(name = "password")
    private String password;
    @NonNull
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @NonNull
    @Column(name = "company_email")
    private String company_email;
    @NonNull
    @Column(name = "personal_email")
    private String personal_email;
    @NonNull
    @Column(name = "city")
    private String city;
    @NonNull
    @Column(name = "active")
    private boolean active;
    @NonNull
    @Column(name = "created_date")
    private Date created_date;
    @Column(name = "imagen_url")
    private String imagen_url;
    @Column(name = "termination_date")
    private Date termination_date;
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Estudiante estudiante;
    /*@OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Profesor profesor;*/

    public Persona() {

    }

    public Persona(PersonaInputDTO personaDTO) {
        if (personaDTO == null)
            return;

        setIdPersona(personaDTO.getId_persona());
        setUsuario(personaDTO.getUsuario());
        setPassword(personaDTO.getPassword());
        setName(personaDTO.getName());
        setSurname(personaDTO.getSurname());
        setCompany_email(personaDTO.getCompany_email());
        setPersonal_email(personaDTO.getPersonal_email());
        setCity(personaDTO.getCity());
        setActive(personaDTO.isActive());
        setCreated_date(personaDTO.getCreated_date());
        setImagen_url(personaDTO.getImagen_url());
        setTermination_date(personaDTO.getTermination_date());
        /*setEstudiante(personaDTO.getEstudiate());*/
    }
}
