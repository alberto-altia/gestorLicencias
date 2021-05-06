package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyectoFCT.gestorLicencias.entity.*;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<Persona,String> {

//    List<Deportista> findDeportistaByClub (Club club);
//
//    List<Entrenador> findEntrenadorByClub (Club club);

//    Deportista findByDeportista (Deportista numLicencia);
//
//    Entrenador findByEntrenador (Entrenador numLicencia);
//
//    Juez findByJuez (String numLicencia);

}
