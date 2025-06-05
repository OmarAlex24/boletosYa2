package boletosyappae.controllers;

import boletosyappae.models.dao.InicioSesionDAO;
import boletosyappae.models.pojo.Empleado;
import boletosyappae.exceptions.CredencialesInvalidasException;
import boletosyappae.exceptions.EmpleadoNoEncontradoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class FXMLInicioSesionController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Label lblMensaje;

    private InicioSesionDAO inicioSesionDAO;

    public void initialize() {
        inicioSesionDAO = new InicioSesionDAO();
    }

    @FXML
    private void iniciarSesion(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String contraseña = txtContraseña.getText();

        try {
            Empleado empleadoAutenticado = inicioSesionDAO.autenticarEmpleado(usuario, contraseña);

            // Guardar empleado en sesión (puedes usar una clase Session)
            // Session.setEmpleadoActual(empleadoAutenticado);

            // Abrir ventana principal
            abrirVentanaPrincipal(empleadoAutenticado);

        } catch (CredencialesInvalidasException | EmpleadoNoEncontradoException e) {
            mostrarError(e.getMessage());
        } catch (Exception e) {
            mostrarError("Error inesperado: " + e.getMessage());
        }
    }

    private void abrirVentanaPrincipal(Empleado empleado) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/boletosyappae/views/FXMLVentanaPrincipal.fxml"));
            Parent root = loader.load();

            // Pasar el empleado al controller de la ventana principal
            FXMLVentanaPrincipalController controller = loader.getController();
            controller.set(empleado);

            Stage stage = new Stage();
            stage.setTitle("Sistema UniAir - " + empleado.getNombre());
            stage.setScene(new Scene(root));
            stage.show();

            // Cerrar ventana de login
            ((Stage) btnIniciarSesion.getScene().getWindow()).close();

        } catch (IOException e) {
            mostrarError("Error al abrir la ventana principal: " + e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        lblMensaje.setText(mensaje);
        lblMensaje.setStyle("-fx-text-fill: red;");
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        txtUsuario.clear();
        txtContraseña.clear();
        lblMensaje.setText("");
    }
}