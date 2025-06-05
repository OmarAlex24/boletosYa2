/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package boletosyappae.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.exceptions.EmpleadoNoEncontradoException;
import boletosyappae.models.dao.EmpleadoDAO;
import boletosyappae.models.pojo.Empleado;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OmarAlex
 */
public class FXMLInicioSesionController implements Initializable {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContraseña;
    @FXML private Button btnIngresar;
    @FXML private Label lblMensaje;

    private EmpleadoDAO empleadoDAO;
    private static Empleado empleadoActual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleadoDAO = new EmpleadoDAO();
    }

    @FXML
    private void handleIngresar(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String contraseña = txtContraseña.getText();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            mostrarMensaje("Por favor complete todos los campos", "error");
            return;
        }

        try {
            empleadoActual = empleadoDAO.autenticar(usuario, contraseña);

            // Cargar ventana principal
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/MenuPrincipal.fxml"));
            Parent root = loader.load();

            // Crear nueva escena
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("UniAir - Sistema de Administración");
            stage.show();

        } catch (EmpleadoNoEncontradoException e) {
            mostrarMensaje("Credenciales incorrectas", "error");
        } catch (DatosInvalidosException e) {
            mostrarMensaje("Error en el sistema: " + e.getMessage(), "error");
        } catch (IOException e) {
            mostrarMensaje("Error al cargar la aplicación", "error");
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String mensaje, String tipo) {
        lblMensaje.setText(mensaje);
        if ("error".equals(tipo)) {
            lblMensaje.setStyle("-fx-text-fill: red;");
        } else {
            lblMensaje.setStyle("-fx-text-fill: green;");
        }
    }

    public static Empleado getEmpleadoActual() {
        return empleadoActual;
    }

    public static void cerrarSesion() {
        empleadoActual = null;
    }

    public static boolean esAdministrador() {
        return empleadoActual != null && "Administrador".equals(empleadoActual.getTipoEmpleado());
    }
    
}
