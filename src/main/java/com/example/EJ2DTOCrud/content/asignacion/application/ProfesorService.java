package com.example.EJ2DTOCrud.content.asignacion.application;

import com.example.EJ2DTOCrud.CustomError;
import com.example.EJ2DTOCrud.content.asignacion.application.port.iProfesor;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.ProfesorInputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Profesor.ProfesorOutputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Estudiante;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Persona;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Profesor;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.repository.jpa.EstudianteRepositoryJpa;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.repository.jpa.PersonaRepositoryJpa;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.repository.jpa.ProfesorRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesorService implements iProfesor {

    @Autowired
    ProfesorRepositoryJpa profesorRepositoryJpa;

    @Autowired
    EstudianteRepositoryJpa estudianteRepositoryJpa;

    @Autowired
    PersonaRepositoryJpa personaRepositoryJpa;

    @Override
    public ProfesorOutputDTO addProfesor(ProfesorInputDTO profesorInputDTO) throws Exception {
        // Buscar el id de la persona
        Persona persona = personaRepositoryJpa.findById(profesorInputDTO.getIdPersona()).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se puede relacionar un estudiante con un persona inexistente"));

        // Me cercioro de que el id del Estudiante y el del Profesor no coinciden (no sean la misma persona)
        Optional<Estudiante> student = estudianteRepositoryJpa.findByPersona(persona);

        if (student.isPresent()) {
            throw new Exception("La persona solicitada es un estudiante");
        }

        Optional<Profesor> prof = profesorRepositoryJpa.findByPersona(persona);

        if (prof.isPresent()) {
            throw new Exception("La persona solicitada es un profesor");
        }

        // Conversión de "EstudianteInputDTO" a entidad "Estudiante"

        Profesor profesor = profesorInputAEntidad(profesorInputDTO);
        profesorRepositoryJpa.save(profesor);
        ProfesorOutputDTO profesorOutputDTO = new ProfesorOutputDTO(profesor);

        return profesorOutputDTO;
    }

    @Override
    public List<ProfesorOutputDTO> listaProfesores() {
        List<ProfesorOutputDTO> lista;
        lista = profesorRepositoryJpa.findAll().stream().map(profesor -> new ProfesorOutputDTO(profesor)).collect(Collectors.toList());
        return lista;
    }

    @Override
    public ProfesorOutputDTO idProfesor(String id, String outputType) throws Exception {
        Profesor profesor = profesorRepositoryJpa.findById(id).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el id solicitado"));

        ProfesorOutputDTO profesorOutputDTO = new ProfesorOutputDTO(profesor);

        return profesorOutputDTO;
    }

    @Override
    public void modificaProfesor(String id, ProfesorInputDTO profesorInputDTO) throws CustomError {
        profesorRepositoryJpa.findById(String.valueOf(id)).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el id solicitado"));

        List<ProfesorOutputDTO> lista = this.listaProfesores();

        for (int i = 0; i < lista.size(); i++) {
            ProfesorOutputDTO p;
            p = lista.get(i);

            if (p.getId() == String.valueOf(id)) {
                Profesor profesor = new Profesor(profesorInputDTO);

                profesor.setId(String.valueOf(id));

                profesorRepositoryJpa.save(profesor);
            }
        }
    }

    @Override
    public void eliminaProfesor(String id) throws CustomError {
        Profesor profesor = profesorRepositoryJpa.findById(String.valueOf(id)).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el id solicitado"));
        profesorRepositoryJpa.deleteById(id);
    }

    public Profesor profesorInputAEntidad(ProfesorInputDTO profesorInputDTO) throws CustomError {
        Profesor profesor = new Profesor();

        profesor.setComents(profesorInputDTO.getComents());
        profesor.setBranch(profesorInputDTO.getBranch());
        profesor.setPersona(personaRepositoryJpa.findById(profesorInputDTO.getIdPersona()).orElseThrow(()->new CustomError(HttpStatus.NOT_FOUND.value(), "Persona no encontrada")));

        return profesor;
    }
}
