package proyectoFCT.gestorLicencias.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Especialidad")
public class Especialidad {


    @Id
    private Long idEspecialidad;

    @Column(name = "nombreEspecialidad")
    private String nombre;

    @OneToMany(cascade = CascadeType.DETACH)
    private Set<PersonaEspecialidad> personaEspecialidad;

}
