package proyectoFCT.gestorLicencias.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyectoFCT.gestorLicencias.aspects.AnotacionLogMetodos;
import proyectoFCT.gestorLicencias.domain.dto.EspecialidadFinAllDTO;
import proyectoFCT.gestorLicencias.entity.Especialidad;
import proyectoFCT.gestorLicencias.repository.EspecialidadRepository;
import proyectoFCT.gestorLicencias.service.EspecialidadesService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspecialidadesServiceImpl implements EspecialidadesService {

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Override
    @AnotacionLogMetodos(operacion = "findAllEspecialidades")
    public List<EspecialidadFinAllDTO> findAll() {
        return especialidadRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public EspecialidadFinAllDTO toDto(Especialidad entity) {
        EspecialidadFinAllDTO dto = new EspecialidadFinAllDTO();
        dto.setNombreEspecialidad(entity.getNombreEspecialidad());
        return dto;
    }
}
