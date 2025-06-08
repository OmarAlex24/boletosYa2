package boletosyappae.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.stage.Stage;

/**
 * Clase de utilidad para funciones comunes de la interfaz de usuario.
 */
public class Utilidad {
    /**
     * Muestra una alerta simple.
     * @param tipo El tipo de alerta.
     * @param titulo El título de la alerta.
     * @param contenido El contenido de la alerta.
     */
    public static void mostrarAlertaSimple (Alert.AlertType tipo, String titulo, String contenido){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

    /**
     * Obtiene el escenario (Stage) de un componente.
     * @param componente El componente del cual se quiere obtener el escenario.
     * @return El escenario del componente.
     */
    public static Stage getEscenarioComponente(Control componente){
        return (Stage) componente.getScene().getWindow();
    }

    /**
     * Muestra una alerta de confirmación.
     * @param titulo El título de la alerta.
     * @param contenido El contenido de la alerta.
     * @return `true` si el usuario presiona "OK", `false` en caso contrario.
     */
    public static boolean mostrarAlertaConfirmacion(String titulo, String contenido){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        return (alerta.showAndWait().get()) == ButtonType.OK;
    }
}