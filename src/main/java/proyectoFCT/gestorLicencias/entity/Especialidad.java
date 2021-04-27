package proyectoFCT.gestorLicencias.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Especialidad {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idEspecialidad;

    @Column(name = "nombreEspecialidad",nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "especialidad",orphanRemoval = true)
    private List<PersonaEspecialidad> personasList = new ArrayList<>();

    public Especialidad(String nombreEspecialidad) {
        this.nombre = nombreEspecialidad;
    }

    public void addPersona(Persona persona,String nivel,Boolean esDeportista,Boolean esEntrenador,Boolean esJuez) {
        PersonaEspecialidad pesonaEspecialidad = new PersonaEspecialidad(persona, this,nivel,esDeportista,esEntrenador,esJuez );
        personasList.add( pesonaEspecialidad );
        persona.getEspecialidadList().add(pesonaEspecialidad);
    }

    public void removePersona(Persona persona) {
        PersonaEspecialidad personaEspecialidad = new PersonaEspecialidad(persona);
        persona.getEspecialidadList().remove( personaEspecialidad );
        personasList.remove( personaEspecialidad );
    }
}
