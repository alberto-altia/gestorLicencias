package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.repository.CrudRepository;

import proyectoFCT.gestorLicencias.entity.Especialidad;

public interface EspecialidadRepository extends CrudRepository<Especialidad, Long>{

    Especialidad findEspecialidadByNombreEspecialidad (String especialidad);
}
