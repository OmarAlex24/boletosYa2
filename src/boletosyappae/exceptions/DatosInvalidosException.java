package boletosyappae.exceptions;

/**
 * Excepción personalizada para errores relacionados con datos inválidos o corruptos.
 */
public class DatosInvalidosException extends Exception { // Cambiado a Exception para que sea una checked exception si se prefiere

    /**
     * Constructor que acepta un mensaje de error.
     * @param message El mensaje detallando el error.
     */
    public DatosInvalidosException(String message) {
        super(message);
    }

    /**
     * Constructor que acepta un mensaje de error y la causa original.
     * Esto permite encadenar excepciones y no perder la traza del error original.
     * @param message El mensaje detallando el error.
     * @param cause La excepción original que causó esta DatosInvalidosException.
     */
    public DatosInvalidosException(String message, Throwable cause) {
        super(message, cause);
    }
}
