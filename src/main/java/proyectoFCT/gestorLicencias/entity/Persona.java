package proyectoFCT.gestorLicencias.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(name = "DNI",nullable = false)
    private String DNI;

    @Column(name = "nombre y apellidos",nullable = false)
    private String nombreApellidos;

    @Column(name = "fechaNacimiento",nullable = false)
    private String fechaNacimiento;

    @Column(name = "telefono",nullable = true)
    private Long telefono;

    @Column(name = "email", nullable = true)
    @Email
    private String email;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PersonaEspecialidad> especialidadList = new ArrayList<>();


    public Persona(String dni, String nombre, String fecha, Long telefono, String email) {
        this.DNI = dni;
        this.nombreApellidos = nombre;
        this.fechaNacimiento = fecha;
        this.telefono = telefono;
        this.email = email;
    }
}
