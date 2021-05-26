package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import proyectoFCT.gestorLicencias.entity.Persona;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;

import java.util.List;

public interface PersonaEspecialidadRepository extends JpaRepository<PersonaEspecialidad,Long> {

    PersonaEspecialidad existsPersonaEspecialidadById(Long id);

    void deletePersonaEspecialidadByPersona (Persona persona);

    @Query(value = "SELECT e.nombreEspecialidad ,p.nivel ,p.fechaActivacion FROM Especialidad e INNER JOIN PersonaEspecialidad p ON e.idEspecialidad = p.especialidad.idEspecialidad WHERE  p.persona.idPersona = ?1 ")
    List<PersonaEspecialidad> licenciasActivas(Long cod_persona);
}
