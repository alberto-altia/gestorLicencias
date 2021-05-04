package proyectoFCT.gestorLicencias.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(name = "DNI", nullable = false)
    private String DNI;

    @Column(name = "nombre_apellidos", nullable = false)
    private String nombreApellidos;

    @Column(name = "fechaNacimiento", nullable = false)
    private String fechaNacimiento;

    @Column(name = "telefono", nullable = true)
    private Integer telefono;

    @Column(name = "email", nullable = true)
    @Email
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<PersonaEspecialidad> personaEspecialidad;

    public Persona(String dni, String nombre, String fecha, Integer telefono, String email) {
        this.DNI = dni;
        this.nombreApellidos = nombre;
        this.fechaNacimiento = fecha;
        this.telefono = telefono;
        this.email = email;
    }
}
