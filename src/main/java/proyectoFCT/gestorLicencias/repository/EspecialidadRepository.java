package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;
import proyectoFCT.gestorLicencias.entity.Especialidad;
@Repository
public interface EspecialidadRepository extends CrudRepository<Especialidad, Long>{

    Especialidad findEspecialidadByNombreEspecialidad (String especialidad);
}
