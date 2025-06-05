package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Vuelo;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class VueloDAO extends GsonDAO<Vuelo> {
    private static final String ARCHIVO = "datos/vuelos.json";

    public VueloDAO() {
        super(ARCHIVO, new TypeToken<List<Vuelo>>(){}.getType());
    }

    @Override
    public List<Vuelo> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    @Override
    public Vuelo obtenerPorId(int id) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(v -> v.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void guardar(Vuelo vuelo) throws DatosInvalidosException {
        List<Vuelo> vuelos = obtenerTodos();
        vuelo.setId(obtenerSiguienteId());
        vuelos.add(vuelo);
        guardarDatos(vuelos);
    }

    @Override
    public void actualizar(Vuelo vuelo) throws DatosInvalidosException {
        List<Vuelo> vuelos = obtenerTodos();
        for (int i = 0; i < vuelos.size(); i++) {
            if (vuelos.get(i).getId() == vuelo.getId()) {
                vuelos.set(i, vuelo);
                break;
            }
        }
        guardarDatos(vuelos);
    }

    @Override
    public void eliminar(int id) throws DatosInvalidosException {
        List<Vuelo> vuelos = obtenerTodos();
        vuelos.removeIf(v -> v.getId() == id);
        guardarDatos(vuelos);
    }

    @Override
    public int obtenerSiguienteId() throws DatosInvalidosException {
        List<Vuelo> vuelos = obtenerTodos();
        return vuelos.stream()
                .mapToInt(Vuelo::getId)
                .max()
                .orElse(0) + 1;
    }
}