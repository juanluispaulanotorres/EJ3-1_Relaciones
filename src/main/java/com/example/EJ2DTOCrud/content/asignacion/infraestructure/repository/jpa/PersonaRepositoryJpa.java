package com.example.EJ2DTOCrud.content.asignacion.infraestructure.repository.jpa;

import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepositoryJpa extends JpaRepository<Persona, String> { }
