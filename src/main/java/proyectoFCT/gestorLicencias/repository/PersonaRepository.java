package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.entity.Persona;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Query(value = "SELECT NUM_LICENCIA_ENTRENADOR FROM Persona WHERE NUM_LICENCIA_ENTRENADOR = ?1 ;", nativeQuery = true)
    String findNumLicenciaEntrenador(String numLicencia);

    Boolean existsPersonaByUsuario(String usuario);

    Boolean existsPersonaByUsuarioAndPassword(String usuario, String password);

    Persona findPersonaByUsuarioAndPassword(String usuario, String password);

    Persona findPersonaByIdPersona(Long id);

    Boolean existsPersonaByIdPersona(Long id);

    Boolean existsPersonaByNumLicenciaDeportista(String licenciaDeportista);

    Boolean existsPersonaByNumLicenciaEntrenador(String lincenciaEntrenador);

    Boolean existsPersonaByNumLicenciaJuez(String licenciaJuez);

    List<Persona> findPersonaByClub (Club idClub);
}
