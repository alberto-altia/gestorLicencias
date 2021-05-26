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

    @Query(value = "select e.nombreEspecialidad, p.nivel ,p.fechaActivacion from Especialidad e inner join PersonaEspecialidad p on e.idEspecialidad= p.especialidad.idEspecialidad where p.persona.idPersona = ?1 " )
    //@Query(value = "SELECT e.nombre_especialidad ,p.nivel ,p.fecha_activacion FROM especialidad e INNER JOIN persona_especialidad p ON e.id_especialidad = p.cod_especialidad WHERE  p.cod_persona = ?1 ",nativeQuery = true)
    List<PersonaEspecialidad> licenciasActivas(Long cod_persona);
}
