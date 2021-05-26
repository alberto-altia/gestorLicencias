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

    @Query(value = "SELECT nombre_especialidad ,nivel ,fecha_activacion\n" +
            "FROM especialidad \n" +
            "INNER JOIN persona_especialidad \n" +
            "ON id_especialidad = cod_especialidad \n" +
            "WHERE  cod_persona = :id ;",nativeQuery = true)
    List<PersonaEspecialidad> licenciasActivas(@Param("id") Long cod_persona);
}
