package proyectoFCT.gestorLicencias.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Persona")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(name = "DNI")
    private String DNI;

    @Column(name = "nombre_apellidos")
    private String nombreApellidos;

    @Column(name = "fechaNacimiento")
    private String fechaNacimiento;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "email")
    private String email;

    @Column(name= "numLicenciaDeportista")
    private String numLicenciaDeportista;

    @Column(name = "numLicenciaEntrenador")
    private String numLicenciaEntrenador;

    @Column(name = "numLicenciaJuez")
    private String numLicenciaJuez;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PersonaEspecialidad> personaEspecialidad;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "club")
    private Club club;
}
