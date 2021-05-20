package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyectoFCT.gestorLicencias.entity.Club;

@Repository
public interface ClubRepository<P, S> extends CrudRepository<Club,String> {
}
