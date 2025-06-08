package boletosyappae.controllers;

import boletosyappae.models.dao.EmpleadoDAO;
import boletosyappae.models.pojo.Empleado;
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

/**
 * Controlador para la vista de inicio de sesión.
 * Maneja la autenticación del usuario y la transición a la ventana principal.
 */
public class FXMLInicioSesionController implements Initializable {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContraseña;
    @FXML private Button btnIngresar;
    @FXML private Label lblMensaje;

    private EmpleadoDAO empleadoDAO;

    /**
     * Inicializa el controlador después de que su elemento raíz ha sido completamente procesado.
     *
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto raíz, o null si la ubicación no es conocida.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null si el objeto raíz no fue localizado.
     */
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
     * @param event El evento de acción que dispara el método.
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
     * Abre la ventana principal de la aplicación después de una autenticación exitosa.
     * @param empleadoAutenticado El objeto Empleado que ha iniciado sesión.
     */
    private void abrirVentanaPrincipal(Empleado empleadoAutenticado) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/boletosyappae/views/FXMLVentanaPrincipal.fxml"));
            Parent root = loader.load();

            FXMLVentanaPrincipalController controller = loader.getController();
            controller.inicializarSesion(empleadoAutenticado);

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

    /**
     * Muestra un mensaje en la interfaz de usuario, coloreado según si es un error o no.
     * @param mensaje El texto del mensaje a mostrar.
     * @param esError `true` si el mensaje representa un error, `false` en caso contrario.
     */
    private void mostrarMensaje(String mensaje, boolean esError) {
        if (lblMensaje == null) return;

        lblMensaje.setText(mensaje);
        if (esError) {
            lblMensaje.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        } else {
            lblMensaje.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        }
    }

    /**
     * Limpia el campo de contraseña y pone el foco en el campo de usuario para un nuevo intento de inicio de sesión.
     */
    private void limpiarCamposParaNuevoIntento() {
        if (txtContraseña != null) txtContraseña.clear();
        if (txtUsuario != null) txtUsuario.requestFocus();
    }
}