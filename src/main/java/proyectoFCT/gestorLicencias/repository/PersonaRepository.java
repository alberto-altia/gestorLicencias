package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyectoFCT.gestorLicencias.entity.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona,String> {

    @Query(value = "SELECT NUM_LICENCIA_ENTRENADOR FROM Persona WHERE NUM_LICENCIA_ENTRENADOR = ?1 ;",nativeQuery = true)
    String findNumLicenciaEntrenador(String numLicencia);
}
