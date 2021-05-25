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

    @Column(name = "numLicenciaDeportista")
    private String numLicenciaDeportista;

    @Column(name = "numLicenciaEntrenador")
    private String numLicenciaEntrenador;

    @Column(name = "numLicenciaJuez")
    private String numLicenciaJuez;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private Set<PersonaEspecialidad> personaEspecialidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codClub")
    private Club club;
}
/*
INSERT INTO PERSONA VALUES(1,'49676970Y','albertinho623@gmail.com','20/08/2001','Alberto Martinez','11254A',null,null,'1234',625541321,'alberto',1);
INSERT INTO PERSONA VALUES(2,'49676970Y','albertinho623@gmail.com','20/08/2001','Alberto Martinez','11254A',null,null,'1234',625541321,'alberto5',1);

{
    "codEspecialidad":5,
    "persona": {
        "nombreApellidos": "Alberto Martinez",
        "fechaNacimiento": "20/08/2001",
        "telefono": 625541321,
        "email": "albertinho@gmail.com",
        "numLicenciaDeportista": null,
        "numLicenciaEntrenador": "12354J",
        "numLicenciaJuez": null,
        "codClub":3 ,
        "dni": "76339373g",
        "usuario":"alberto7",
        "password":"1234"
    },
    "nivel":"medio",
    "esDeportista":"true",
    "esEntrenador":"false",
    "esJuez":"false",
    "fechaActivacion":"17/05/2021"
}
 */