package com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto;

import com.example.EJ2DTOCrud.CustomError;
import com.example.EJ2DTOCrud.content.asignacion.application.PersonaService;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.PersonaInputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Persona.PersonaOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ControladorPersona {

    @Autowired
    PersonaService personaService;

    @PostMapping("/addPersona")
    public String addPersona(@RequestBody PersonaInputDTO persona) throws Exception {
        return "Persona creada correctamente \n" + personaService.addPersona(persona);
    }

    @GetMapping("/listadoPersonas")
    public List<PersonaOutputDTO> listaPersonas() {
        return personaService.listaPersonas();
    }

    // Mostrar persona por "id"
    @GetMapping("/persona/{id}")
    public PersonaOutputDTO idPersona(@PathVariable int id) throws CustomError{
        return personaService.idPersona(id);
    }

    // Mostrar persona por "usuario"
    @GetMapping("/personaUsuario/{usuario}")
    public PersonaOutputDTO usuarioPersona(@PathVariable String usuario) {
        return personaService.usuarioPersona(usuario);
    }

    // Modificar usuario
    @PutMapping("/modificar/{id}")
    public void modificaPersona(@PathVariable int id, @RequestBody PersonaInputDTO persona) throws CustomError {
        personaService.modificaPersona(id, persona);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminaPersona(@PathVariable int id) throws CustomError{
        personaService.eliminaPersona(id);
        return "Persona eliminada de la base de datos";
    }
}
