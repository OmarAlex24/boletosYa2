package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Vuelo;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.Optional;

/**
 * DAO para la gestión de vuelos.
 */
public class VueloDAO extends GsonDAO<Vuelo> {
    private static final String NOMBRE_ARCHIVO = "datos/vuelos.json";

    public VueloDAO() {
        super(NOMBRE_ARCHIVO, new TypeToken<List<Vuelo>>(){}.getType());
    }

    @Override
    public List<Vuelo> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    /**
     * No aplicable para Vuelo, ya que su ID es String ("idVuelo").
     * Lanza UnsupportedOperationException.
     * @param id El ID del vuelo (no utilizado).
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public Vuelo obtenerPorId(int id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("El ID de Vuelo es String. Use obtenerPorId(String).");
    }

    /**
     * Obtiene un vuelo por su ID (String, ej: "UA001").
     * @param idVuelo El ID String del vuelo.
     * @return El vuelo encontrado.
     * @throws DatosInvalidosException si el ID no corresponde a ningún vuelo.
     */
    @Override
    public Vuelo obtenerPorId(String idVuelo) throws DatosInvalidosException {
        if (idVuelo == null || idVuelo.trim().isEmpty()) {
            throw new DatosInvalidosException("El ID del vuelo no puede ser nulo o vacío.");
        }
        Optional<Vuelo> vueloOpt = obtenerTodos().stream()
                .filter(v -> v.getIdVuelo().equalsIgnoreCase(idVuelo.trim()))
                .findFirst();
        if (!vueloOpt.isPresent()) {
            throw new DatosInvalidosException("Vuelo con ID '" + idVuelo + "' no encontrado.");
        }
        return vueloOpt.get();
    }


    @Override
    public void guardar(Vuelo vuelo) throws DatosInvalidosException {
        if (vuelo == null || vuelo.getIdVuelo() == null || vuelo.getIdVuelo().trim().isEmpty()) {
            throw new DatosInvalidosException("El vuelo o su ID no pueden ser nulos o vacíos.");
        }
        List<Vuelo> vuelos = obtenerTodos();
        boolean existe = vuelos.stream().anyMatch(v -> v.getIdVuelo().equalsIgnoreCase(vuelo.getIdVuelo()));
        if (existe) {
            throw new DatosInvalidosException("Ya existe un vuelo con el ID " + vuelo.getIdVuelo() + ". Use actualizar en su lugar.");
        }
        vuelos.add(vuelo);
        guardarDatos(vuelos);
    }

    @Override
    public void actualizar(Vuelo vuelo) throws DatosInvalidosException {
        if (vuelo == null || vuelo.getIdVuelo() == null || vuelo.getIdVuelo().trim().isEmpty()) {
            throw new DatosInvalidosException("El vuelo a actualizar o su ID no pueden ser nulos o vacíos.");
        }
        List<Vuelo> vuelos = obtenerTodos();
        boolean encontrado = false;
        for (int i = 0; i < vuelos.size(); i++) {
            if (vuelos.get(i).getIdVuelo().equalsIgnoreCase(vuelo.getIdVuelo())) {
                vuelos.set(i, vuelo);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new DatosInvalidosException("Vuelo con ID " + vuelo.getIdVuelo() + " no encontrado para actualizar.");
        }
        guardarDatos(vuelos);
    }

    /**
     * No aplicable para Vuelo, ya que su ID es String ("idVuelo").
     * Lanza UnsupportedOperationException.
     * @param id El ID del vuelo (no utilizado).
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public void eliminar(int id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("El ID de Vuelo es String. Use eliminar(String).");
    }

    @Override
    public void eliminar(String idVuelo) throws DatosInvalidosException {
        if (idVuelo == null || idVuelo.trim().isEmpty()) {
            throw new DatosInvalidosException("El ID del vuelo para eliminar no puede ser nulo o vacío.");
        }
        List<Vuelo> vuelos = obtenerTodos();
        boolean eliminado = vuelos.removeIf(v -> v.getIdVuelo().equalsIgnoreCase(idVuelo.trim()));
        if (!eliminado) {
            throw new DatosInvalidosException("Vuelo con ID '" + idVuelo + "' no encontrado para eliminar.");
        }
        guardarDatos(vuelos);
    }

    /**
     * Los IDs de Vuelo son Strings y no son numéricos secuenciales simples.
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public int obtenerSiguienteIdNumerico() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Los IDs de Vuelo son Strings y no se generan numéricamente de forma secuencial simple por este DAO.");
    }
}