package com.example.EJ2DTOCrud.content.asignacion.application;

import com.example.EJ2DTOCrud.CustomError;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Persona;
import com.example.EJ2DTOCrud.content.asignacion.application.port.iPersona;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.PersonaInputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.PersonaOutputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.repository.jpa.PersonaRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService implements iPersona {

    @Autowired
    PersonaRepositoryJpa personaRepositoryJpa;

    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception {
        if (personaInputDTO.getUsuario() == null) {
            throw new CustomError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "El usuario no puede ser nulo");

        } else if (personaInputDTO.getUsuario().length() > 10) {
            throw new CustomError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "El usuario no puede tener más de 10 caracteres");

        } else {
            Persona persona = new Persona(personaInputDTO);
            personaRepositoryJpa.save(persona);
            PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(persona);

            return personaOutputDTO;
        }
    }

    @Override
    public List<PersonaOutputDTO> listaPersonas() {
        List<PersonaOutputDTO> lista;
        lista = personaRepositoryJpa.findAll().stream().map(persona -> new PersonaOutputDTO(persona)).collect(Collectors.toList());
        return lista;
    }

    @Override
    public PersonaOutputDTO idPersona(int id) throws CustomError {
        Persona persona = personaRepositoryJpa.findById(id).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el id solicitado"));

        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(persona);
        return personaOutputDTO;
    }

    @Override
    public PersonaOutputDTO usuarioPersona(String usuario) {
        List<PersonaOutputDTO> lista = this.listaPersonas();

        for (int i = 0; i < lista.size(); i++) {
            PersonaOutputDTO personaOutputDTO;
            personaOutputDTO = lista.get(i);

            if (personaOutputDTO.getUsuario().equalsIgnoreCase(usuario)) {
                return personaOutputDTO;
            }
        }
        return null;
    }

    @Override
    public void modificaPersona(int id, PersonaInputDTO personaInputDTO) throws CustomError {

        // En caso de no encontrar el "id", se lanza una excepción
        personaRepositoryJpa.findById(id).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el id solicitado"));

        // En caso de que el "usuario" no sea válido, se lanzará las siguientes excepciones
        if (personaInputDTO.getUsuario() == null)
            throw new CustomError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "El usuario no puede ser nulo");
        else if (personaInputDTO.getUsuario().length() > 10)
            throw new CustomError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "El usuario no puede tener más de 10 caracteres");

        // Recuperar la lista de las personas y recorrerla con un bucle para encontrar a la persona solicitada mediante "id" y modificarla
        List<PersonaOutputDTO> lista = this.listaPersonas();

        for (int i = 0; i < lista.size(); i++) {
            PersonaOutputDTO p;
            p = lista.get(i);

            if (p.getId_persona() == id) {
                Persona persona = new Persona(personaInputDTO);
                // IMPORTANTE: Hay que establecer el nuevo "id" de la persona usando el que pasamos como parámetro
                persona.setId_persona(id);

                personaRepositoryJpa.save(persona);
            }
        }
    }

    @Override
    public void eliminaPersona(int id) throws CustomError{
        Persona persona = personaRepositoryJpa.findById(id).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el id solicitado"));
        personaRepositoryJpa.delete(persona);
    }
}
