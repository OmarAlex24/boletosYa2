package boletosyappae.models.dao;

import boletosyappae.models.pojo.*;
import boletosyappae.exceptions.*;
import boletosyappae.utils.Seguridad;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.Optional;

/**
 * DAO para la gestión de empleados, maneja la persistencia en JSON.
 */
public class EmpleadoDAO extends GsonDAO<Empleado> {

    private static final String NOMBRE_ARCHIVO = "datos/empleados.json";

    public EmpleadoDAO() {
        super(NOMBRE_ARCHIVO, new TypeToken<List<Empleado>>() {
        }.getType());
    }

    /**
     * Autentica a un usuario comparando el hash de la contraseña ingresada.
     *
     * @param nombreUsuario El nombre de usuario.
     * @param contrasenaPlana La contraseña en texto plano ingresada por el usuario.
     * @return El objeto Empleado si la autenticación es exitosa.
     * @throws CredencialesInvalidasException si las credenciales son incorrectas.
     * @throws DatosInvalidosException si hay un problema al cargar los datos.
     */
    public Empleado autenticarUsuario(String nombreUsuario, String contrasenaPlana)
            throws CredencialesInvalidasException, DatosInvalidosException {
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty() || contrasenaPlana == null || contrasenaPlana.isEmpty()) {
            throw new CredencialesInvalidasException("Usuario y contraseña son requeridos.");
        }

        String contrasenaHasheada = Seguridad.sha256(contrasenaPlana);
        List<Empleado> empleados = obtenerTodos();

        for (Empleado empleado : empleados) {
            if (empleado.getNombreUsuario().equalsIgnoreCase(nombreUsuario)
                    && empleado.getContraseña().equals(contrasenaHasheada)) {
                return empleado;
            }
        }

        throw new CredencialesInvalidasException("Credenciales incorrectas.");
    }

    @Override
    public List<Empleado> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    @Override
    public Empleado obtenerPorId(String numEmpleado) throws EmpleadoNoEncontradoException, DatosInvalidosException {
        if (numEmpleado == null || numEmpleado.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de empleado no puede ser nulo o vacío.");
        }
        Optional<Empleado> empleadoOpt = obtenerTodos().stream()
                .filter(e -> e.getNumEmpleado().equalsIgnoreCase(numEmpleado.trim()))
                .findFirst();
        if (!empleadoOpt.isPresent()) {
            throw new EmpleadoNoEncontradoException("Empleado con número '" + numEmpleado + "' no encontrado.");
        }
        return empleadoOpt.get();
    }

    @Override
    public void guardar(Empleado empleado) throws DatosInvalidosException {
        List<Empleado> empleados = obtenerTodos();
        boolean existe = empleados.stream().anyMatch(e -> e.getNumEmpleado().equalsIgnoreCase(empleado.getNumEmpleado()));
        if (existe) {
            throw new DatosInvalidosException("Ya existe un empleado con el número " + empleado.getNumEmpleado());
        }
        empleados.add(empleado);
        guardarDatos(empleados);
    }

    @Override
    public void actualizar(Empleado empleado) throws DatosInvalidosException, EmpleadoNoEncontradoException {
        List<Empleado> empleados = obtenerTodos();
        boolean encontrado = false;
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getNumEmpleado().equalsIgnoreCase(empleado.getNumEmpleado())) {
                empleados.set(i, empleado);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new EmpleadoNoEncontradoException("Empleado con número " + empleado.getNumEmpleado() + " no encontrado para actualizar.");
        }
        guardarDatos(empleados);
    }

    @Override
    public void eliminar(String numEmpleado) throws DatosInvalidosException, EmpleadoNoEncontradoException {
        List<Empleado> empleados = obtenerTodos();
        boolean eliminado = empleados.removeIf(e -> e.getNumEmpleado().equalsIgnoreCase(numEmpleado.trim()));
        if (!eliminado) {
            throw new EmpleadoNoEncontradoException("Empleado con número '" + numEmpleado + "' no encontrado para eliminar.");
        }
        guardarDatos(empleados);
    }

    /**
     * No aplicable para Empleado, ya que su ID es String.
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public Empleado obtenerPorId(int id) {
        throw new UnsupportedOperationException("El ID de Empleado es String. Use obtenerPorId(String).");
    }

    /**
     * No aplicable para Empleado, ya que su ID es String.
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("El ID de Empleado es String. Use eliminar(String).");
    }

    /**
     * No aplicable para Empleado, ya que su ID es String.
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public int obtenerSiguienteIdNumerico() {
        throw new UnsupportedOperationException("Los IDs de Empleado son Strings y no se generan numéricamente.");
    }
}