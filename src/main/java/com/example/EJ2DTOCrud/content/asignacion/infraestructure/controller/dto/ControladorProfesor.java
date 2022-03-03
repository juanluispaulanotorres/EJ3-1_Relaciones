package com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto;

import com.example.EJ2DTOCrud.CustomError;
import com.example.EJ2DTOCrud.content.asignacion.application.ProfesorService;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.ProfesorInputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Profesor.ProfesorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ControladorProfesor {
    @Autowired
    ProfesorService profesorService;

    @PostMapping("/addProfesor")
    public String addProfesor(@RequestBody ProfesorInputDTO profesorInputDTO) throws Exception {
        return "Profesor creado correctamente \n" + profesorService.addProfesor(profesorInputDTO);
    }

    @GetMapping("/listadoProfesores")
    public List<ProfesorOutputDTO> listaProfesores() {
        return profesorService.listaProfesores();
    }

    // Mostrar persona por "id"
    @GetMapping("/profesor/{id}")
    public ProfesorOutputDTO idProfesor(@PathVariable String id, @RequestParam(name = "outputType", defaultValue = "simple") String outputType) throws Exception {
        return profesorService.idProfesor(id, outputType);
    }

    // Modificar usuario
    @PutMapping("/modificarProfesor/{id}")
    public void modificaProfesor(@PathVariable String id, @RequestBody ProfesorInputDTO profesorInputDTO) throws CustomError {
        profesorService.modificaProfesor(id, profesorInputDTO);
    }

    @DeleteMapping("/eliminarProfesor/{id}")
    public String eliminarProfesor(@PathVariable String id) throws CustomError{
        profesorService.eliminaProfesor(id);
        return "Profesor eliminado de la base de datos";
    }
}
