package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyectoFCT.gestorLicencias.entity.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona,Long> {

    @Query(value = "SELECT NUM_LICENCIA_ENTRENADOR FROM Persona WHERE NUM_LICENCIA_ENTRENADOR = ?1 ;",nativeQuery = true)
    String findNumLicenciaEntrenador(String numLicencia);

    Boolean existsPersonaByUsuario (String usuario);

    Boolean existsPersonaByUsuarioAndPassword (String usuario, String password);

    Persona findPersonaByUsuarioAndPassword (String usuario,String password);

    Persona findPersonaByIdPersona(Long id);

    Persona findPersonaByUsuario (String usuario);

    Boolean existsPersonaByIdPersona(Long id);

    Boolean existsPersonaByNumLicenciaDeportista (String licenciaDeportista);

    Boolean existsPersonaByNumLicenciaEntrenador (String lincenciaEntrenador);

    Boolean existsPersonaByNumLicenciaJuez (String licenciaJuez);
}
