package boletosyappae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal que inicia la aplicación JavaFX.
 */
public class BoletosYaPPAE extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/FXMLInicioSesion.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("UniAir - Iniciar Sesión");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
