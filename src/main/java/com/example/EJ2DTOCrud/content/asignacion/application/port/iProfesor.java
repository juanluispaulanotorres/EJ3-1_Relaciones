package com.example.EJ2DTOCrud.content.asignacion.application.port;

import com.example.EJ2DTOCrud.CustomError;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.ProfesorInputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Profesor.ProfesorOutputDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface iProfesor {
    ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO) throws Exception;
    List<ProfesorOutputDTO> listaProfesores();
    ProfesorOutputDTO idProfesor(String id, String outputType) throws Exception;
    void modificaProfesor(String id, ProfesorInputDTO profesorInputDTO) throws CustomError;
    void eliminaProfesor(@PathVariable String id) throws CustomError;
}
