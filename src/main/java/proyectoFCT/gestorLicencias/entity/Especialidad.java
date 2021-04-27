package proyectoFCT.gestorLicencias.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Especialidad {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idEspecialidad;

    @Column(name = "nombreEspecialidad",nullable = false)
    private String nombre;
}
