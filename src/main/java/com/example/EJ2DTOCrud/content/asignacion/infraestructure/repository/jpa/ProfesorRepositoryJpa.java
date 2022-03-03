package com.example.EJ2DTOCrud.content.asignacion.infraestructure.repository.jpa;

import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Estudiante;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Persona;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesorRepositoryJpa extends JpaRepository<Profesor, String> {

    public Optional<Profesor> findByPersona(Persona persona);

}