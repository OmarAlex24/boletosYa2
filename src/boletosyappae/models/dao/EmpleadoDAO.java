package boletosyappae.models.dao;

import boletosyappae.models.pojo.*;
import boletosyappae.exceptions.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EmpleadoDAO extends GsonDAO {
    private static final String ARCHIVO_EMPLEADOS = "empleados.json";

    public EmpleadoDAO() {
        super();
        // Crear archivo si no existe
        if (!archivoExiste(ARCHIVO_EMPLEADOS)) {
            crearArchivoVacio(ARCHIVO_EMPLEADOS);
        }
    }

    public List<Empleado> obtenerTodosLosEmpleados() {
        Type tipoLista = new TypeToken<List<Empleado>>(){}.getType();
        List<Empleado> empleados = leerArchivo(ARCHIVO_EMPLEADOS, tipoLista);
        return empleados != null ? empleados : new ArrayList<>();
    }

    public Empleado obtenerEmpleadoPorId(int idEmpleado) throws EmpleadoNoEncontradoException {
        List<Empleado> empleados = obtenerTodosLosEmpleados();
        return empleados.stream()
                .filter(e -> e.getIdEmpleado() == idEmpleado)
                .findFirst()
                .orElseThrow(() -> new EmpleadoNoEncontradoException("Empleado con ID " + idEmpleado + " no encontrado"));
    }

    public List<Administrativo> obtenerAdministrativos() {
        List<Empleado> empleados = obtenerTodosLosEmpleados();
        return empleados.stream()
                .filter(e -> e instanceof Administrativo)
                .map(e -> (Administrativo) e)
                .collect(Collectors.toList());
    }

    public List<Piloto> obtenerPilotos() {
        List<Empleado> empleados = obtenerTodosLosEmpleados();
        return empleados.stream()
                .filter(e -> e instanceof Piloto)
                .map(e -> (Piloto) e)
                .collect(Collectors.toList());
    }

    public List<AsistenteVuelo> obtenerAsistentesVuelo() {
        List<Empleado> empleados = obtenerTodosLosEmpleados();
        return empleados.stream()
                .filter(e -> e instanceof AsistenteVuelo)
                .map(e -> (AsistenteVuelo) e)
                .collect(Collectors.toList());
    }

    // ONLY Administrativo can authenticate
    public Administrativo autenticarUsuario(String usuario, String contrasena)
            throws CredencialesInvalidasException {
        if (usuario == null || usuario.trim().isEmpty() ||
                contrasena == null || contrasena.trim().isEmpty()) {
            throw new CredencialesInvalidasException("Usuario y contraseña son requeridos");
        }

        List<Administrativo> administrativos = obtenerAdministrativos();

        for (Administrativo admin : administrativos) {
            if (admin.validarCredenciales(usuario, contrasena)) {
                return admin;
            }
        }

        throw new CredencialesInvalidasException("Credenciales incorrectas.");
    }

    public Administrativo buscarPorUsuario(String usuario) throws CredencialesInvalidasException {
        List<Empleado> empleados = obtenerTodosLosEmpleados();

        for (Empleado emp : empleados) {
            if(emp.getTipoEmpleado().equals(TipoEmpleado.ADMINISTRATIVO)) {
                Administrativo admin = (Administrativo) emp;

                if(admin.getUsuario().equals(usuario)) return admin;
            }
        }
        throw new CredencialesInvalidasException("Usuario no encontrado.");
    }

    public boolean guardarEmpleado(Empleado empleado) throws DatosInvalidosException {
        if (empleado == null) {
            throw new DatosInvalidosException("El empleado no puede ser nulo");
        }

        validarDatosEmpleado(empleado);

        List<Empleado> empleados = obtenerTodosLosEmpleados();

        // Check if employee already exists
        empleados.removeIf(e -> e.getIdEmpleado() == empleado.getIdEmpleado());
        empleados.add(empleado);

        return escribirArchivo(ARCHIVO_EMPLEADOS, empleados);
    }

    public boolean actualizarEmpleado(Empleado empleado) throws EmpleadoNoEncontradoException, DatosInvalidosException {
        if (empleado == null) {
            throw new DatosInvalidosException("El empleado no puede ser nulo");
        }

        // Verificar que el empleado existe
        obtenerEmpleadoPorId(empleado.getIdEmpleado());

        validarDatosEmpleado(empleado);

        List<Empleado> empleados = obtenerTodosLosEmpleados();

        // Actualizar empleado existente
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getIdEmpleado() == empleado.getIdEmpleado()) {
                empleados.set(i, empleado);
                break;
            }
        }

        return escribirArchivo(ARCHIVO_EMPLEADOS, empleados);
    }

    public boolean eliminarEmpleado(int idEmpleado) throws EmpleadoNoEncontradoException {
        List<Empleado> empleados = obtenerTodosLosEmpleados();

        boolean eliminado = empleados.removeIf(e -> e.getIdEmpleado() == idEmpleado);

        if (!eliminado) {
            throw new EmpleadoNoEncontradoException("Empleado con ID " + idEmpleado + " no encontrado");
        }

        return escribirArchivo(ARCHIVO_EMPLEADOS, empleados);
    }

    private void validarDatosEmpleado(Empleado empleado) throws DatosInvalidosException {
        if (empleado.getNombre() == null || empleado.getNombre().trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre del empleado es requerido");
        }

        if (empleado.getApellidoPaterno() == null || empleado.getApellidoPaterno().trim().isEmpty()) {
            throw new DatosInvalidosException("El apellido paterno es requerido");
        }

        if (empleado.getEmail() == null || empleado.getEmail().trim().isEmpty()) {
            throw new DatosInvalidosException("El email es requerido");
        }

        if (!esEmailValido(empleado.getEmail())) {
            throw new DatosInvalidosException("El formato del email no es válido");
        }

        if (empleado.getSalario() <= 0) {
            throw new DatosInvalidosException("El salario debe ser mayor a 0");
        }

        if (empleado.getFechaContratacion() == null) {
            throw new DatosInvalidosException("La fecha de contratación es requerida");
        }

        // Validate Administrativo specific fields
        if (empleado instanceof Administrativo) {
            Administrativo admin = (Administrativo) empleado;
            if (admin.getUsuario() == null || admin.getUsuario().trim().isEmpty()) {
                throw new DatosInvalidosException("El usuario es requerido para empleados administrativos");
            }
            if (admin.getContrasena() == null || admin.getContrasena().trim().isEmpty()) {
                throw new DatosInvalidosException("La contraseña es requerida para empleados administrativos");
            }
            if (admin.getDepartamento() == null) {
                throw new DatosInvalidosException("El departamento es requerido para empleados administrativos");
            }

            // Verificar que el usuario no esté duplicado
            if (existeUsuario(admin.getUsuario(), admin.getIdEmpleado())) {
                throw new DatosInvalidosException("El usuario '" + admin.getUsuario() + "' ya existe");
            }
        }

        // Validate Piloto specific fields
        if (empleado instanceof Piloto) {
            Piloto piloto = (Piloto) empleado;
            if (piloto.getHorasVuelo() < 0) {
                throw new DatosInvalidosException("Las horas de vuelo no pueden ser negativas");
            }
        }
    }

    private boolean esEmailValido(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean existeUsuario(String usuario, int idEmpleadoActual) {
        List<Administrativo> administrativos = obtenerAdministrativos();
        return administrativos.stream()
                .anyMatch(admin -> admin.getUsuario().equals(usuario) &&
                        admin.getIdEmpleado() != idEmpleadoActual);
    }

    public List<Empleado> buscarEmpleadosPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<Empleado> empleados = obtenerTodosLosEmpleados();
        String nombreBusqueda = nombre.toLowerCase().trim();

        return empleados.stream()
                .filter(e -> e.getNombre().toLowerCase().contains(nombreBusqueda) ||
                        e.getApellidoPaterno().toLowerCase().contains(nombreBusqueda) ||
                        (e.getApellidoMaterno() != null &&
                                e.getApellidoMaterno().toLowerCase().contains(nombreBusqueda)))
                .collect(Collectors.toList());
    }

    public List<Empleado> obtenerEmpleadosPorAerolinea(int idAerolinea) {
        List<Empleado> empleados = obtenerTodosLosEmpleados();
        return empleados.stream()
                .filter(e -> e.getIdAerolinea() == idAerolinea)
                .collect(Collectors.toList());
    }

    public List<Empleado> obtenerEmpleadosPorTipo(TipoEmpleado tipo) {
        List<Empleado> empleados = obtenerTodosLosEmpleados();
        return empleados.stream()
                .filter(e -> e.getTipoEmpleado() == tipo)
                .collect(Collectors.toList());
    }

    public int obtenerSiguienteId() {
        List<Empleado> empleados = obtenerTodosLosEmpleados();
        return empleados.stream()
                .mapToInt(Empleado::getIdEmpleado)
                .max()
                .orElse(0) + 1;
    }

    public long contarEmpleados() {
        return obtenerTodosLosEmpleados().size();
    }

    public long contarEmpleadosPorTipo(TipoEmpleado tipo) {
        return obtenerTodosLosEmpleados().stream()
                .filter(e -> e.getTipoEmpleado() == tipo)
                .count();
    }
}