package proyectoFCT.gestorLicencias.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Club {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idClub;

    @Column(name = "licenciaClub",nullable = false)
    private String licenciaClub;

    @Column(name = "fechaCreacion",nullable = false)
    private String fechaCreacion;
}
