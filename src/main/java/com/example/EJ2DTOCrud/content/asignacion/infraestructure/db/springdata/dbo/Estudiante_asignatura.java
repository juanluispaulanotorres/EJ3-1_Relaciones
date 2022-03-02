package com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo;

import com.example.EJ2DTOCrud.StringPrefixedSequenceIdGenerator;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Estudios")
public class Estudiante_asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ausencias_seq")
    @GenericGenerator(
            name = "ausencias_seq",
            strategy = "com.bosonit.staffit.shared.sequences.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AUS"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d")
            }
    )*/
    @Column(name = "id_asignatura")
    private String id_asignatura;
   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    private int id_profesor;
    @JoinColumn(name = "id_estudiante")
    @ManyToOne(cascade = CascadeType.ALL)
    private Estudiante estudiante;*/
    @Column(name = "asignatura")
    private String asignatura;
    @Column(name = "coments")
    private String coments;
    @Column(name = "initial_date")
    @NotNull
    private Date initial_date;
    @Column(name = "finish_date")
    private Date finish_date;

    public Estudiante_asignatura() {

    }
}
