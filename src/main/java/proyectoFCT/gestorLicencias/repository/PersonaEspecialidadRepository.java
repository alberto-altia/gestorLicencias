package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;

public interface PersonaEspecialidadRepository extends JpaRepository<PersonaEspecialidad,Long> {

    PersonaEspecialidad existsPersonaEspecialidadById(Long id);
}
