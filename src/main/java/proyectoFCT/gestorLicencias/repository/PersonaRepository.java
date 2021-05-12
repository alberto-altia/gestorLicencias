package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyectoFCT.gestorLicencias.entity.*;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<Persona,String> {



}
