package com.example.EJ2DTOCrud.content.asignacion.application;

import com.example.EJ2DTOCrud.CustomError;
import com.example.EJ2DTOCrud.content.asignacion.application.port.iEstudiante;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.EstudianteInputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Estudiante.EstudianteOutputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.Estudiante.EstudianteSingularOutputDTO;
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
public class EstudianteService implements iEstudiante {

    @Autowired
    EstudianteRepositoryJpa estudianteRepositoryJpa;

    @Autowired
    ProfesorRepositoryJpa profesorRepositoryJpa;

    @Autowired
    PersonaRepositoryJpa personaRepositoryJpa;

    @Override
    public EstudianteOutputDTO addEstudiante(EstudianteInputDTO estudianteInputDTO) throws Exception {

        // Buscar el id de la persona
        Persona persona = personaRepositoryJpa.findById(estudianteInputDTO.getIdPersona()).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se puede relacionar un estudiante con un persona inexistente"));

        // Me cercioro de que el id del Estudiante y el del Profesor no coinciden (no sean la misma persona)
        Optional<Estudiante> student = estudianteRepositoryJpa.findByPersona(persona);

        if (student.isPresent()) {
            throw new Exception("El estudiante ya se encuentra en la base de datos");
        }

        Optional<Profesor> profesor = profesorRepositoryJpa.findByPersona(persona);

        if (profesor.isPresent()) {
            throw new Exception("El profesor ya se encuentra en la base de datos");
        }

        // Conversión de "EstudianteInputDTO" a entidad "Estudiante"

        Estudiante estudiante = estudianteInputAEntidad(estudianteInputDTO);
        estudianteRepositoryJpa.save(estudiante);
        EstudianteOutputDTO estudianteOutputDTO = new EstudianteOutputDTO(estudiante);

        return estudianteOutputDTO;

    }

    @Override
    public List<EstudianteOutputDTO> listaEstudiantes() {
        List<EstudianteOutputDTO> lista;
        lista = estudianteRepositoryJpa.findAll().stream().map(estudiante -> new EstudianteOutputDTO(estudiante)).collect(Collectors.toList());
        return lista;
    }

    @Override
    public EstudianteOutputDTO idEstudiante(String id, String outputType) throws Exception {
        Estudiante estudiante = estudianteRepositoryJpa.findById(id).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el id solicitado"));

        // Datos del estudiante y la persona
        EstudianteOutputDTO estudianteOutputDTO = new EstudianteOutputDTO(estudiante);
        // Datos del estudiante
        EstudianteSingularOutputDTO estudianteSingularOutputDTO = new EstudianteSingularOutputDTO(estudiante);

        if (outputType.equalsIgnoreCase("full")) {
            // Devolver los datos de la persona asociada, junto con los del estudiante
            return estudianteOutputDTO;
        }

        // Devuelvo SÓLO los datos del estudiante
        return estudianteSingularOutputDTO;
    }

    @Override
    public void modificaEstudiante(String id, EstudianteInputDTO estudianteInputDTO) throws CustomError {
        // En caso de no encontrar el "id", se lanza una excepción
        estudianteRepositoryJpa.findById(String.valueOf(id)).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el id solicitado"));

        // Recuperar la lista de las personas y recorrerla con un bucle para encontrar a la persona solicitada mediante "id" y modificarla
        List<EstudianteOutputDTO> lista = this.listaEstudiantes();

        for (int i = 0; i < lista.size(); i++) {
            EstudianteOutputDTO e;
            e = lista.get(i);

            if (e.getId() == String.valueOf(id)) {
                Estudiante estudiante = new Estudiante(estudianteInputDTO);
                // IMPORTANTE: Hay que establecer el nuevo "id" de la persona usando el que pasamos como parámetro
                estudiante.setId(String.valueOf(id));

                estudianteRepositoryJpa.save(estudiante);
            }
        }
    }

    @Override
    public void eliminaEstudiante(String id) throws CustomError {
        Estudiante estudiante = estudianteRepositoryJpa.findById(String.valueOf(id)).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el id solicitado"));
        estudianteRepositoryJpa.delete(estudiante);
    }

    public Estudiante estudianteInputAEntidad(EstudianteInputDTO estudianteInputDTO) throws CustomError {
        Estudiante estudiante = new Estudiante();

        estudiante.setNum_hours_week(estudianteInputDTO.getNum_hours_week());
        estudiante.setComents(estudianteInputDTO.getComents());
        estudiante.setBranch(estudianteInputDTO.getBranch());
        estudiante.setPersona(personaRepositoryJpa.findById(estudianteInputDTO.getIdPersona()).orElseThrow(()->new CustomError(HttpStatus.NOT_FOUND.value(), "Persona no encontrada")));

        return estudiante;
    }

}
