package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.exceptions.EmpleadoNoEncontradoException;
import boletosyappae.models.pojo.Empleado;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.stream.Collectors;

public class EmpleadoDAO extends GsonDAO<Empleado> {
    private static final String ARCHIVO = "datos/empleados.json";

    public EmpleadoDAO() {
        super(ARCHIVO, new TypeToken<List<Empleado>>(){}.getType());
    }

    @Override
    public List<Empleado> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    @Override
    public Empleado obtenerPorId(int id) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void guardar(Empleado empleado) throws DatosInvalidosException {
        List<Empleado> empleados = obtenerTodos();
        empleado.setId(obtenerSiguienteId());
        empleados.add(empleado);
        guardarDatos(empleados);
    }

    @Override
    public void actualizar(Empleado empleado) throws DatosInvalidosException {
        List<Empleado> empleados = obtenerTodos();
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId() == empleado.getId()) {
                empleados.set(i, empleado);
                break;
            }
        }
        guardarDatos(empleados);
    }

    @Override
    public void eliminar(int id) throws DatosInvalidosException {
        List<Empleado> empleados = obtenerTodos();
        empleados.removeIf(e -> e.getId() == id);
        guardarDatos(empleados);
    }

    @Override
    public int obtenerSiguienteId() throws DatosInvalidosException {
        List<Empleado> empleados = obtenerTodos();
        return empleados.stream()
                .mapToInt(Empleado::getId)
                .max()
                .orElse(0) + 1;
    }

    public Empleado autenticar(String usuario, String contraseña) throws EmpleadoNoEncontradoException, DatosInvalidosException {
        Empleado empleado = obtenerTodos().stream()
                .filter(e -> e.getUsuario().equals(usuario) && e.getContrasena().equals(contraseña))
                .findFirst()
                .orElse(null);

        if (empleado == null) {
            throw new EmpleadoNoEncontradoException("Credenciales incorrectas");
        }

        return empleado;
    }

    public List<Empleado> obtenerPilotos() throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(e -> e.getTipoEmpleado().equals("Piloto")).collect(Collectors.toList());
    }

    public List<Empleado> obtenerAsistentes() throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(e -> e.getTipoEmpleado().equals("AsistenteVuelo")).collect(Collectors.toList());
    }

    public Empleado buscarPorUsuario(String usuario) throws DatosInvalidosException {
        List<Empleado> empleados = obtenerTodos();

        for (Empleado empleado : empleados) {
            if(empleado.getNombreUsuario().equals(usuario)) {
                return empleado;
            }
        }

        return null;
    }
}