package com.example.EJ2DTOCrud.content.asignacion.application.port;

import com.example.EJ2DTOCrud.CustomError;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.PersonaInputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.PersonaOutputDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface iPersona {
    PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;
    List<PersonaOutputDTO>  listaPersonas();
    PersonaOutputDTO idPersona(int id) throws Exception;
    PersonaOutputDTO usuarioPersona(String usuario);
    void modificaPersona(int id, PersonaInputDTO persona) throws CustomError;
    void eliminaPersona(@PathVariable int id) throws CustomError;
}
