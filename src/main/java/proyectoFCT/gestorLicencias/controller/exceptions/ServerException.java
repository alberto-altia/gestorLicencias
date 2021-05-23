package proyectoFCT.gestorLicencias.controller.exceptions;

public class ServerException extends RuntimeException {
    private static final String DESCRIPCION = "Server Exception (500)";

    public ServerException(String detalles) {
        super(DESCRIPCION + ". " + detalles);
    }
}
