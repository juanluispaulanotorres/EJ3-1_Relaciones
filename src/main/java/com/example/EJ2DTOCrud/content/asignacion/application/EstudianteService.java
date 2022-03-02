package com.example.EJ2DTOCrud.content.asignacion.application;

import com.example.EJ2DTOCrud.CustomError;
import com.example.EJ2DTOCrud.content.asignacion.application.port.iEstudiante;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.input.EstudianteInputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.controller.dto.output.EstudianteOutputDTO;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.db.springdata.dbo.Estudiante;
import com.example.EJ2DTOCrud.content.asignacion.infraestructure.repository.jpa.EstudianteRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService implements iEstudiante {

    @Autowired
    EstudianteRepositoryJpa estudianteRepositoryJpa;

    @Override
    public EstudianteOutputDTO addEstudiante(EstudianteInputDTO estudianteInputDTO) throws Exception {
        /*if (estudianteInputDTO.getPersona() == null) {
            throw new CustomError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "No hay personas añadidas");*/

        //} else {
            Estudiante estudiante = new Estudiante(estudianteInputDTO);
            estudianteRepositoryJpa.save(estudiante);
            EstudianteOutputDTO estudianteOutputDTO = new EstudianteOutputDTO(estudiante);

            return estudianteOutputDTO;
       // }
    }

    @Override
    public List<EstudianteOutputDTO> listaEstudiantes() {
        List<EstudianteOutputDTO> lista;
        lista = estudianteRepositoryJpa.findAll().stream().map(estudiante -> new EstudianteOutputDTO(estudiante)).collect(Collectors.toList());
        return lista;
    }

    @Override
    public EstudianteOutputDTO idEstudiante(String id) throws Exception {
        Estudiante estudiante = estudianteRepositoryJpa.findById(id).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el id solicitado"));

        EstudianteOutputDTO estudianteOutputDTO = new EstudianteOutputDTO(estudiante);
        return estudianteOutputDTO;
    }

    @Override
    public void modificaEstudiante(String id, EstudianteInputDTO estudianteInputDTO) throws CustomError {
        // En caso de no encontrar el "id", se lanza una excepción
        estudianteRepositoryJpa.findById(String.valueOf(id)).orElseThrow(()-> new CustomError(HttpStatus.NOT_FOUND.value(), "No se ha encontrado el id solicitado"));

        // En caso de que el "usuario" no sea válido, se lanzará las siguientes excepciones
        /*if (estudianteInputDTO.getPersona() == null)
            throw new CustomError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Debe haber una persona");*/

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
}
