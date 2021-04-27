package proyectoFCT.gestorLicencias.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "idPersona")
public class Deportista extends Persona{

    @Column(name = "numLicenciaDeportista")
    private String numLicenciaDeportista;

    public Deportista(String numLicenciaDeportista,String dni,String nombre,String fecha,Long telefono, String email){
        super(dni,nombre,fecha,telefono,email);
        this.numLicenciaDeportista = numLicenciaDeportista;
    }
}
