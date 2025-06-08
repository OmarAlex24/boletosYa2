package boletosyappae.exceptions;

/**
 * Excepción lanzada cuando las credenciales proporcionadas por un usuario son incorrectas.
 */
public class CredencialesInvalidasException extends RuntimeException {
  /**
   * Construye una nueva excepción `CredencialesInvalidasException` con el mensaje de detalle especificado.
   * @param message el mensaje de detalle.
   */
  public CredencialesInvalidasException(String message) {
    super(message);
  }
}