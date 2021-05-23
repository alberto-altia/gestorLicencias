package proyectoFCT.gestorLicencias.controller.exceptions;

public class NotFoundException extends RuntimeException {

    private static final String DESCRIPCION = "Not Found Exception (404)";

    public NotFoundException(String detalles) {
        super(DESCRIPCION + ". " + detalles);
    }
}
