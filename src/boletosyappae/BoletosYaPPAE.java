package boletosyappae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal que inicia la aplicación JavaFX "BoletosYa".
 * * Esta clase extiende {@link Application} y es el punto de entrada para la
 * interfaz gráfica. Carga la vista de inicio de sesión como la primera
 * pantalla.
 */
public class BoletosYaPPAE extends Application {

    /**
     * El método principal para una aplicación JavaFX.
     * Se llama después de que el método init() ha retornado, y después de que
     * el sistema está listo para que la aplicación comience a ejecutarse.
     *
     * @param stage El escenario principal para esta aplicación, sobre el cual
     * se puede establecer la escena de la aplicación.
     * @throws Exception Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/FXMLInicioSesion.fxml"));

        Scene scene = new Scene(root);

        stage.setTitle("UniAir - Iniciar Sesión");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * El punto de entrada principal para todas las aplicaciones Java.
     * El método `launch()` es un método estático de la clase Application que
     * crea una instancia de la clase de la aplicación y llama al método `start()`.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
}