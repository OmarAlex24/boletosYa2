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
 * Maneja la navegación entre las diferentes secciones y el cierre de sesión.
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

    /**
     * Inicializa el controlador.
     *
     * @param url La ubicación utilizada para resolver rutas relativas.
     * @param rb Los recursos utilizados para localizar el objeto raíz.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Inicializa la sesión del usuario en la ventana principal.
     * Muestra el nombre y rol del empleado y configura la UI según sus permisos.
     *
     * @param empleado El empleado que ha iniciado sesión.
     */
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

    /**
     * Configura la visibilidad y accesibilidad de los botones del menú según el rol del empleado.
     *
     * @param tipoEmpleado El rol del empleado logueado.
     */
    private void configurarUIPorRol(TipoEmpleado tipoEmpleado) {
        boolean esAdmin = (tipoEmpleado == TipoEmpleado.ADMINISTRATIVO);
        btnEmpleados.setDisable(!esAdmin);
        btnAerolineas.setDisable(!esAdmin);
        btnAviones.setDisable(!esAdmin);
        btnVuelos.setDisable(!esAdmin);
        btnGestionClientes.setDisable(!esAdmin);
        btnComprarBoletos.setDisable(false);
    }

    /**
     * Maneja el evento de clic del botón "Empleados", cargando la vista correspondiente.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void handleEmpleados(ActionEvent event) {
        cargarVista("/boletosyappae/views/empleado/FXMLEmpleados.fxml");
    }

    /**
     * Maneja el evento de clic del botón "Aerolíneas", cargando la vista correspondiente.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void handleAerolineas(ActionEvent event) {
        cargarVista("/boletosyappae/views/aerolinea/FXMLAerolineas.fxml");
    }

    /**
     * Carga una vista FXML en el panel central del BorderPane principal.
     *
     * @param fxmlPath La ruta al archivo FXML a cargar.
     */
    private void cargarVista(String fxmlPath) {
        try {
            Parent vista = FXMLLoader.load(getClass().getResource(fxmlPath));
            borderPanePrincipal.setCenter(vista);
        } catch (IOException e) {
            e.printStackTrace();
            Utilidad.mostrarAlertaSimple(Alert.AlertType.ERROR, "Error de Navegación", "No se pudo cargar la vista: " + fxmlPath);
        }
    }

    /**
     * Deshabilita todos los elementos de la interfaz de usuario.
     *
     * @param deshabilitar `true` para deshabilitar, `false` para habilitar.
     */
    private void deshabilitarTodaLaUI(boolean deshabilitar) {
        btnEmpleados.setDisable(deshabilitar);
        btnAerolineas.setDisable(deshabilitar);
        btnAviones.setDisable(deshabilitar);
        btnVuelos.setDisable(deshabilitar);
        btnGestionClientes.setDisable(deshabilitar);
        btnComprarBoletos.setDisable(deshabilitar);
    }

    /**
     * Maneja el evento de clic del botón "Aviones", cargando la vista correspondiente.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void handleAviones(ActionEvent event) {
        cargarVista("/boletosyappae/views/avion/FXMLAviones.fxml");
    }

    /**
     * Maneja el evento de clic del botón "Vuelos", cargando la vista correspondiente.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void handleVuelos(ActionEvent event) {
        cargarVista("/boletosyappae/views/vuelo/FXMLVuelos.fxml");
    }

    /**
     * Maneja el evento de clic del botón "Gestionar Clientes", cargando la vista correspondiente.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void handleGestionClientes(ActionEvent event) {
        cargarVista("/boletosyappae/views/cliente/FXMLClientes.fxml");
    }

    /**
     * Maneja el evento de clic del botón "Comprar Boletos", cargando la vista correspondiente.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void handleComprarBoletos(ActionEvent event) {
        cargarVista("/boletosyappae/views/boleto/FXMLCompraBoleto.fxml");
    }

    /**
     * Maneja el evento de clic del botón "Cerrar Sesión".
     * Cierra la sesión actual y regresa a la ventana de inicio de sesión.
     *
     * @param event El evento de acción.
     */
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