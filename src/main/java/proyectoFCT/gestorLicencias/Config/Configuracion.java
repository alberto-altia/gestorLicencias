package proyectoFCT.gestorLicencias.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({ "proyectoFCT.gestorLicencias","proyectoFCT.gestorLicencias" })
@Import({SwaggerConfig.class})
public class Configuracion {}
