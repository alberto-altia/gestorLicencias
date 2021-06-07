package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.CrearLicenciaDTO;
import proyectoFCT.gestorLicencias.domain.dto.LicenciasActivasDTO;
import proyectoFCT.gestorLicencias.domain.dto.PersonaEspecialidadFindAllDto;

import java.util.List;

public interface PersonaEspecialidadService {

    List<PersonaEspecialidadFindAllDto> findAll();

    List<LicenciasActivasDTO> licenciasActivas(String usuario);

    List<LicenciasActivasDTO> licenciasActivasOf(Long usuario);

    List<LicenciasActivasDTO> todasLicencias();

    CrearLicenciaDTO crearNuevaLicencia(CrearLicenciaDTO crearLicenciaDTO);

    void eliminarLicencia(Long id);

}
