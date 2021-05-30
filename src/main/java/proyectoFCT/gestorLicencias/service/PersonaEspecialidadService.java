package proyectoFCT.gestorLicencias.service;

import proyectoFCT.gestorLicencias.domain.dto.*;
import proyectoFCT.gestorLicencias.entity.PersonaEspecialidad;

import java.io.IOException;
import java.util.List;

public interface PersonaEspecialidadService {

    PersonaEspecialidadCrearDTO crearDeportista(PersonaEspecialidadCrearDTO deportista);

    PersonaEspecialidadCrearDTO crearEntrenador(PersonaEspecialidadCrearDTO entrenador);

    PersonaEspecialidadCrearDTO crearJuez(PersonaEspecialidadCrearDTO juez);

    List<PersonaEspecialidadFindAllDto> findAll();

    List<LicenciasActivasDTO> licenciasActivas(Long idPersona);

    List<LicenciasActivasDTO> todasLicencias();

    CrearLicenciaDTO crearNuevaLicencia(CrearLicenciaDTO crearLicenciaDTO);

    void eliminarLicencia (Long id);

}
