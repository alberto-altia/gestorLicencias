package proyectoFCT.gestorLicencias.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -104420413625153136L;
	
	String jwt;
}
