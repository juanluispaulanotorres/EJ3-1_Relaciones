package com.example.EJ2DTOCrud.content.asignacion.infraestructure.repository.jpa;

import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepositoryJpa extends JpaRepository<Estudiante, String> {

}
