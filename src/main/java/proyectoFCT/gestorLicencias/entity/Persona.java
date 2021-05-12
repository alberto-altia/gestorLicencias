package proyectoFCT.gestorLicencias.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Persona")
@Getter
@Setter
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
    @Email
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PersonaEspecialidad> personaEspecialidad;
}
