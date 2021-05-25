package proyectoFCT.gestorLicencias.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proyectoFCT.gestorLicencias.repository.PersonaRepository;

import java.util.Random;

@Component
public class GenerarLicencias {

    @Autowired

    PersonaRepository personaRepository;

    public String generarCodigo(){
        Long numero;
        numero = (long)(Math.random() * ((10000 - 100 + 1) + 100));

        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');

        String resultado = numero.toString() + c;
        System.out.println(resultado);

        if(!personaRepository.existsPersonaByNumLicenciaDeportista(resultado) ||
                !personaRepository.existsPersonaByNumLicenciaEntrenador(resultado) ||
                !personaRepository.existsPersonaByNumLicenciaJuez(resultado)){
            return resultado;
        }else {
           return generarCodigo();
        }
    }
}
