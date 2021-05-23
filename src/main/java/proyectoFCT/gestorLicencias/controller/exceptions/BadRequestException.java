package proyectoFCT.gestorLicencias.controller.exceptions;

public class BadRequestException extends RuntimeException {

    private static final String DESCRIPCION = "Bad Request Exception (400)";

    public BadRequestException(String detalles) {
        super(DESCRIPCION + ". " + detalles);
    }
}
