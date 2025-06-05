package boletosyappae.controllers;

import boletosyappae.models.dao.EmpleadoDAO;
import boletosyappae.models.pojo.Administrativo;
import boletosyappae.exceptions.CredencialesInvalidasException;
import boletosyappae.utils.Utilidad;
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

    @FXML private TextField tfUsuario;
    @FXML private PasswordField pfContrasena;
    @FXML private Button btnIniciarSesion;
    @FXML private Label lblMensaje;

    private EmpleadoDAO empleadoDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleadoDAO = new EmpleadoDAO();
        lblMensaje.setText("");
    }

    @FXML
    private void iniciarSesion(ActionEvent event) {
        String usuario = tfUsuario.getText().trim();
        String contrasena = pfContrasena.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarMensaje("Por favor, ingrese usuario y contraseña", true);
            return;
        }

        try {
            // Only Administrativo employees can log in
            Administrativo adminAutenticado = empleadoDAO.autenticarUsuario(usuario, contrasena);

            if (adminAutenticado != null) {
                mostrarMensaje("Inicio de sesión exitoso", false);
                abrirVentanaPrincipal(adminAutenticado);
            }

        } catch (CredencialesInvalidasException e) {
            mostrarMensaje(e.getMessage(), true);
            limpiarCampos();
        } catch (Exception e) {
            mostrarMensaje("Error al iniciar sesión: " + e.getMessage(), true);
            e.printStackTrace();
        }
    }

    private void abrirVentanaPrincipal(Administrativo empleadoAutenticado) {
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

            // Close login window
            Stage loginStage = (Stage) btnIniciarSesion.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            mostrarMensaje("Error al abrir la ventana principal: " + e.getMessage(), true);
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String mensaje, boolean esError) {
        lblMensaje.setText(mensaje);
        if (esError) {
            lblMensaje.setStyle("-fx-text-fill: red;");
        } else {
            lblMensaje.setStyle("-fx-text-fill: green;");
        }
    }

    private void limpiarCampos() {
        tfUsuario.clear();
        pfContrasena.clear();
        tfUsuario.requestFocus();
    }

    @FXML
    private void salir(ActionEvent event) {
        Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
        stage.close();
    }
}