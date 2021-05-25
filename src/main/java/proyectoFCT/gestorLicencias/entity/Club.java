package proyectoFCT.gestorLicencias.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Club")
public class Club {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long idClub;

    @Column(name = "licenciaClub")
    private String licenciaClub;

    @Column(name = "nombreClub")
    private String nombreClub;

    @Column(name = "fechaCreacion")
    private String fechaCreacion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club", orphanRemoval = true)
    private Set<Persona> personas;

    public void setPersonas(Set<Persona> newPersonas) {
        if (personas == null)
            personas = new HashSet<Persona>();
        personas.clear();
        if (newPersonas == null)
            return;
        personas.addAll(newPersonas);
    }


}
