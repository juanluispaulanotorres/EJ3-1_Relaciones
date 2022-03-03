package com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto;

import com.example.EJ2DTOCrud.CustomError;
import com.example.EJ2DTOCrud.content.asignacion.application.EstudianteService;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.EstudianteInputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Estudiante.EstudianteOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ControladorEstudiante {
    @Autowired
    EstudianteService estudianteService;

    @PostMapping("/addEstudiante")
    public String addEstudiante(@RequestBody EstudianteInputDTO estudiante) throws Exception {
        return "Estudiante creado correctamente \n" + estudianteService.addEstudiante(estudiante);
    }

    @GetMapping("/listadoEstudiantes")
    public List<EstudianteOutputDTO> listaEstudiantes() {
        return estudianteService.listaEstudiantes();
    }

    // Mostrar persona por "id"
    @GetMapping("/estudiante/{id}")
    public EstudianteOutputDTO idEstudiante(@PathVariable String id, @RequestParam(name = "outputType", defaultValue = "simple") String outputType) throws Exception {
        return estudianteService.idEstudiante(id, outputType);
    }

    // Modificar usuario
    @PutMapping("/modificarEstudiante/{id}")
    public void modificaEstudiante(@PathVariable String id, @RequestBody EstudianteInputDTO estudianteInputDTO) throws CustomError {
        estudianteService.modificaEstudiante(id, estudianteInputDTO);
    }

    @DeleteMapping("/eliminarEstudiante/{id}")
    public String eliminaEstudiante(@PathVariable String id) throws CustomError{
        estudianteService.eliminaEstudiante(id);
        return "Estudiante eliminado de la base de datos";
    }
}
