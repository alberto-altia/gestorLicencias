package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.CrearLicenciaDTO;
import proyectoFCT.gestorLicencias.domain.dto.LicenciasActivasDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadCrearDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadFindAllDto;

import java.util.List;

public interface PersonaEspecialidadService {

    PersonaEspecialidadCrearDTO crearDeportista(PersonaEspecialidadCrearDTO deportista);

    PersonaEspecialidadCrearDTO crearEntrenador(PersonaEspecialidadCrearDTO entrenador);

    PersonaEspecialidadCrearDTO crearJuez(PersonaEspecialidadCrearDTO juez);

    List<PersonaEspecialidadFindAllDto> findAll();

    List<LicenciasActivasDTO> licenciasActivas(Long idPersona);

    List<LicenciasActivasDTO> todasLicencias();

    CrearLicenciaDTO crearNuevaLicencia(CrearLicenciaDTO crearLicenciaDTO);

    void eliminarLicencia(Long id);

}
