package proyectoFCT.gestorLicencias.entity;

import lombok.Data;
import proyectoFCT.gestorLicencias.domain.dto.PersonaDTO;

import javax.persistence.*;

@Data
@Entity
public class Club {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idClub;

    @Column(name = "licenciaClub")
    private String licenciaClub;

    @Column(name = "nombreClub")
    private String nombreClub;

    @Column(name = "fechaCreacion")
    private String fechaCreacion;


}
