package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyectoFCT.gestorLicencias.entity.Especialidad;
import proyectoFCT.gestorLicencias.entity.Persona;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;

import java.util.List;

@Repository
public interface PersonaEspecialidadRepository extends JpaRepository<PersonaEspecialidad, Long> {

    void deletePersonaEspecialidadByPersona(Persona persona);

    @Query(value = "SELECT * FROM especialidad e , persona_especialidad p WHERE e.id_especialidad = p.cod_especialidad and p.cod_persona = ?1 ", nativeQuery = true)
    List<PersonaEspecialidad> licenciasActivas(Long id);

    Boolean existsPersonaEspecialidadByEspecialidadAndPersonaAndEsDeportistaAndAndEsEntrenadorAndEsJuez(Especialidad especialidad, Persona persona, Boolean esDeportista, Boolean esEntrenador, Boolean esJuez);

    Boolean existsPersonaEspecialidadById(Long id);

    void deletePersonaEspecialidadById(Long id);
}
