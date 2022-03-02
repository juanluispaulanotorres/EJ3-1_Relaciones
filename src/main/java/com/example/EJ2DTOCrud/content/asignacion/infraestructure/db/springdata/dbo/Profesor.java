package com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo;

import com.example.EJ2DTOCrud.StringPrefixedSequenceIdGenerator;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Profesores")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   /* @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ausencias_seq")
    @GenericGenerator(
            name = "ausencias_seq",
            strategy = "com.bosonit.staffit.shared.sequences.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            }
    )*/
    @Column(name = "id_profesor")
    private String id;
    //@Column(name = "id_persona")
    /*@OneToOne(fetch = FetchType.LAZY)
    private Persona persona;*/
    @Column(name = "coments")
    private String coments;
    @Column(name = "branch")
    @NonNull
    private String branch;
    /*@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;*/

    public Profesor(){}
}