package com.example.EJ2DTOCrud.content.asignacion.application.port;

import com.example.EJ2DTOCrud.CustomError;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.EstudianteInputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.EstudianteOutputDTO;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

public interface iEstudiante {
    EstudianteOutputDTO addEstudiante(EstudianteInputDTO estudianteInputDTO) throws Exception;
    List<EstudianteOutputDTO> listaEstudiantes();
    EstudianteOutputDTO idEstudiante(String id) throws Exception;
    void modificaEstudiante(String id, EstudianteInputDTO estudiante) throws CustomError;
    void eliminaEstudiante(@PathVariable String id) throws CustomError;
}
