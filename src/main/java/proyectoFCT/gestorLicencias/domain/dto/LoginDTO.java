package proyectoFCT.gestorLicencias.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {

    private String usuario;

    private String password;
}
