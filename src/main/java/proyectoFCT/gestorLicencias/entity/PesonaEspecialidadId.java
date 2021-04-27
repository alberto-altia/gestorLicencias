package proyectoFCT.gestorLicencias.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PesonaEspecialidadId implements Serializable {

    private Long persona;

    private Long especialidad;

}
