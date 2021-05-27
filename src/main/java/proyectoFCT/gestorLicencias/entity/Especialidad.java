package proyectoFCT.gestorLicencias.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "Especialidad")
@AllArgsConstructor
@NoArgsConstructor
public class Especialidad {


    @Id
    @Column(name = "idEspecialidad")
    private Long idEspecialidad;

    @Column(name = "nombreEspecialidad")
    private String nombreEspecialidad;

    @OneToMany(mappedBy = "especialidad", fetch = FetchType.LAZY)
    private Set<PersonaEspecialidad> personaEspecialidad;

    public Especialidad(Long idEspecialidad, String nombreEspecialidad) {
        this.idEspecialidad = idEspecialidad;
        this.nombreEspecialidad = nombreEspecialidad;
        personaEspecialidad = null;
    }
}
/*
INSERT INTO ESPECIALIDAD VALUES(1,'Estandard');
INSERT INTO ESPECIALIDAD VALUES(2,'Latinos');
INSERT INTO ESPECIALIDAD VALUES(3,'Caribeños');
INSERT INTO ESPECIALIDAD VALUES(4,'Breaking');
INSERT INTO ESPECIALIDAD VALUES(5,'Hip Hop');
INSERT INTO ESPECIALIDAD VALUES(6,'Fit Kid');
INSERT INTO ESPECIALIDAD VALUES(7,'Line Dance');
INSERT INTO ESPECIALIDAD VALUES(8,'Twirling');

*/
