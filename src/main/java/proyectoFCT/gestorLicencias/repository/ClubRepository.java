package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.entity.Persona;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {

    Club findClubByPersonas (Persona persona);

    Club findClubByIdClub (Long id);

    List<Club> findClubsByPersonas (Persona idPersona);


}
