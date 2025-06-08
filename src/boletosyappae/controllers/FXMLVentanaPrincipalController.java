package boletosyappae.controllers;

import boletosyappae.models.pojo.Empleado;
import boletosyappae.models.pojo.TipoEmpleado;
import boletosyappae.utils.Utilidad;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controlador para la ventana principal de la aplicación.
 */
public class FXMLVentanaPrincipalController implements Initializable {

    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Label lblTipoEmpleado;
    @FXML
    private BorderPane borderPanePrincipal;
    @FXML
    private Button btnEmpleados;
    @FXML
    private Button btnAerolineas;
    @FXML
    private Button btnAviones;
    @FXML
    private Button btnVuelos;
    @FXML
    private Button btnGestionClientes;
    @FXML
    private Button btnComprarBoletos;
    @FXML
    private Button btnCerrarSesion;

    private Empleado empleadoLogueado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void inicializarSesion(Empleado empleado) {
        this.empleadoLogueado = empleado;
        if (empleadoLogueado != null) {
            lblNombreUsuario.setText("Usuario: " + empleadoLogueado.getNombre());
            lblTipoEmpleado.setText("Rol: " + empleadoLogueado.getTipo().toString());
            configurarUIPorRol(empleadoLogueado.getTipo());
        } else {
            lblNombreUsuario.setText("Usuario: Desconocido");
            lblTipoEmpleado.setText("Rol: Desconocido");
            deshabilitarTodaLaUI(true);
        }
    }

    private void configurarUIPorRol(TipoEmpleado tipoEmpleado) {
        boolean esAdmin = (tipoEmpleado == TipoEmpleado.ADMINISTRATIVO);
        btnEmpleados.setDisable(!esAdmin);
        btnAerolineas.setDisable(!esAdmin);
        btnAviones.setDisable(!esAdmin);
        btnVuelos.setDisable(!esAdmin);
        btnGestionClientes.setDisable(!esAdmin);
        btnComprarBoletos.setDisable(false);
    }

    @FXML
    private void handleEmpleados(ActionEvent event) {
        cargarVista("/boletosyappae/views/empleado/FXMLEmpleados.fxml");
    }

    @FXML
    private void handleAerolineas(ActionEvent event) {
        cargarVista("/boletosyappae/views/aerolinea/FXMLAerolineas.fxml");
    }

    private void cargarVista(String fxmlPath) {
        try {
            Parent vista = FXMLLoader.load(getClass().getResource(fxmlPath));
            borderPanePrincipal.setCenter(vista);
        } catch (IOException e) {
            e.printStackTrace();
            Utilidad.mostrarAlertaSimple(Alert.AlertType.ERROR, "Error de Navegación", "No se pudo cargar la vista: " + fxmlPath);
        }
    }

    private void deshabilitarTodaLaUI(boolean deshabilitar) {
        // Implementación...
    }

    @FXML
    private void handleAviones(ActionEvent event) {
        cargarVista("/boletosyappae/views/avion/FXMLAviones.fxml");
    }

    @FXML
    private void handleVuelos(ActionEvent event) {
        cargarVista("/boletosyappae/views/vuelo/FXMLVuelos.fxml");
    }

    @FXML
    private void handleGestionClientes(ActionEvent event) {
        cargarVista("/boletosyappae/views/cliente/FXMLClientes.fxml");
    }

    @FXML
    private void handleComprarBoletos(ActionEvent event) {
        cargarVista("/boletosyappae/views/boleto/FXMLCompraBoleto.fxml");
    }

    @FXML
    private void handleCerrarSesion(ActionEvent event) {
        if (Utilidad.mostrarAlertaConfirmacion("Cerrar Sesión", "¿Estás seguro de que deseas cerrar la sesión?")) {
            try {
                Stage stageActual = (Stage) btnCerrarSesion.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/boletosyappae/views/FXMLInicioSesion.fxml"));
                stageActual.setScene(new Scene(root));
            } catch (IOException e) {
                Utilidad.mostrarAlertaSimple(Alert.AlertType.ERROR, "Error", "No se pudo volver a la ventana de inicio de sesión.");
            }
        }
    }
}
