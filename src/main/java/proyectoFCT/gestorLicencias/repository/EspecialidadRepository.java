package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyectoFCT.gestorLicencias.entity.Especialidad;
@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

    Especialidad findEspecialidadByNombreEspecialidad (String especialidad);
}
