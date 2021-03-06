package com.example.EJ2DTOCrud.content.asignacion.infraestructure.repository.jpa;

import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Estudiante;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepositoryJpa extends JpaRepository<Estudiante, String> {

    public Optional<Estudiante> findByPersona(Persona persona);

}
