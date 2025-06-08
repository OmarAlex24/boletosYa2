package boletosyappae.exceptions;

/**
 * Excepción lanzada cuando se intenta realizar una operación que requiere capacidad en un vuelo y este no la tiene.
 */
public class VueloSinCapacidadException extends RuntimeException {
    /**
     * Construye una nueva excepción `VueloSinCapacidadException` con el mensaje de detalle especificado.
     * @param message el mensaje de detalle.
     */
    public VueloSinCapacidadException(String message) {
        super(message);
    }
}