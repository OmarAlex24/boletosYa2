package boletosyappae.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un empleado específico en la fuente de datos.
 */
public class EmpleadoNoEncontradoException extends RuntimeException {
    /**
     * Construye una nueva excepción `EmpleadoNoEncontradoException` con el mensaje de detalle especificado.
     * @param message el mensaje de detalle.
     */
    public EmpleadoNoEncontradoException(String message) {
        super(message);
    }
}