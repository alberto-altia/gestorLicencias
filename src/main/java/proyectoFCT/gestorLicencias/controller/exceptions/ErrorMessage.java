package proyectoFCT.gestorLicencias.controller.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorMessage {

    @JsonProperty("exception")
    private String exception;

    @JsonProperty("message")
    private String mensaje;

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("path")
    private String path;

    public ErrorMessage(Exception exception,String path){

        this.exception = exception.getClass().getSimpleName();
        this.mensaje = exception.getMessage();
        this.path = path;
    }
    public ErrorMessage(int statusCode, String message, String uriRequested) {
        this.mensaje = message;
        this.statusCode = statusCode;
        this.path = uriRequested;
    }
}
