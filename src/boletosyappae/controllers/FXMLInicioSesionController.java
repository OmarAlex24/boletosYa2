package boletosyappae.controllers;

import boletosyappae.models.dao.EmpleadoDAO;
import boletosyappae.models.pojo.Administrativo;
import boletosyappae.models.pojo.Empleado; // Importar la clase base Empleado
import boletosyappae.exceptions.CredencialesInvalidasException;
import boletosyappae.exceptions.DatosInvalidosException; 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLInicioSesionController implements Initializable {

    // Componentes FXML inyectados (asegúrate que los fx:id en el FXML coincidan)
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContraseña; 
    @FXML private Button btnIngresar;        
    @FXML private Label lblMensaje;

    private EmpleadoDAO empleadoDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            empleadoDAO = new EmpleadoDAO(); 
        } catch (Exception e) { 
            mostrarMensaje("Error crítico al inicializar acceso a datos: " + e.getMessage(), true);
            if (txtUsuario != null) txtUsuario.setDisable(true);
            if (txtContraseña != null) txtContraseña.setDisable(true);
            if (btnIngresar != null) btnIngresar.setDisable(true);
            e.printStackTrace();
        }
        if (lblMensaje != null) { 
           lblMensaje.setText("");
        }
    }

    /**
     * Maneja el evento de clic del botón "Ingresar".
     * Intenta autenticar al usuario y abrir la ventana principal si tiene éxito.
     * @param event El evento de acción.
     */
    @FXML
    private void handleIngresar(ActionEvent event) { 
        if (empleadoDAO == null) { 
            mostrarMensaje("Error: El sistema de acceso a datos no está disponible.", true);
            return;
        }

        String usuario = txtUsuario.getText().trim();       
        String contrasena = txtContraseña.getText(); 

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarMensaje("Por favor, ingrese usuario y contraseña.", true);
            return;
        }

        try {
            Empleado empleadoAutenticado = empleadoDAO.autenticarUsuario(usuario, contrasena);
            
            mostrarMensaje("Inicio de sesión exitoso. Abriendo sistema...", false);
            abrirVentanaPrincipal(empleadoAutenticado); 

            Stage loginStage = (Stage) btnIngresar.getScene().getWindow();
            loginStage.close();

        } catch (CredencialesInvalidasException e) {
            mostrarMensaje(e.getMessage(), true);
            limpiarCamposParaNuevoIntento();
        } catch (DatosInvalidosException e) { 
            mostrarMensaje("Error de datos: " + e.getMessage(), true);
            e.printStackTrace();
        } catch (Exception e) { 
            mostrarMensaje("Error inesperado al iniciar sesión: " + e.getMessage(), true);
            e.printStackTrace();
        }
    }

    /**
     * Abre la ventana principal después de una autenticación exitosa.
     * @param empleadoAutenticado El Empleado que ha iniciado sesión.
     */
    private void abrirVentanaPrincipal(Empleado empleadoAutenticado) { // Acepta Empleado
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/boletosyappae/views/FXMLVentanaPrincipal.fxml"));
            Parent root = loader.load();

            FXMLVentanaPrincipalController controller = loader.getController();
            controller.inicializarSesion(empleadoAutenticado); // Pasa el Empleado genérico

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("BoletosYa - Sistema Principal");
            stage.show();

        } catch (IOException e) {
            mostrarMensaje("Error al abrir la ventana principal: " + e.getMessage(), true);
            e.printStackTrace();
        } catch (Exception e) { 
            mostrarMensaje("Error inesperado al cargar la ventana principal: " + e.getMessage(), true);
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String mensaje, boolean esError) {
        if (lblMensaje == null) return; 

        lblMensaje.setText(mensaje);
        if (esError) {
            lblMensaje.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        } else {
            lblMensaje.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        }
    }

    private void limpiarCamposParaNuevoIntento() {
        if (txtContraseña != null) txtContraseña.clear();
        if (txtUsuario != null) txtUsuario.requestFocus(); 
    }

    
        
}
