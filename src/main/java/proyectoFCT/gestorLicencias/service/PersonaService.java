package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.DeportistaDTO;
import proyectoFCT.gestorLicencias.domain.dto.EntrenadorDTO;
import proyectoFCT.gestorLicencias.domain.dto.JuezDTO;
import proyectoFCT.gestorLicencias.entity.Deportista;

public interface PersonaService {

    DeportistaDTO crearDeportista(DeportistaDTO deportistaDTO);

    EntrenadorDTO crearEntrenador(EntrenadorDTO entrenadorDTO);

    JuezDTO crearJuez(JuezDTO juezDTO);
}
