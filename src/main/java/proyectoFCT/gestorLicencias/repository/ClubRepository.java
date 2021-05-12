package proyectoFCT.gestorLicencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proyectoFCT.gestorLicencias.entity.Club;
import proyectoFCT.gestorLicencias.entity.Deportista;
import proyectoFCT.gestorLicencias.entity.Entrenador;

import java.util.List;

@Repository
public interface ClubRepository extends CrudRepository<Club,String> {


}
