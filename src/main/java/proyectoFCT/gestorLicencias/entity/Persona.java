package proyectoFCT.gestorLicencias.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
public class Persona {


    @Id
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
}
