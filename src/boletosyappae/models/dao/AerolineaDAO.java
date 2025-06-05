package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Aerolinea;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class AerolineaDAO extends GsonDAO<Aerolinea> {
    private static final String ARCHIVO = "datos/aerolineas.json";

    public AerolineaDAO() {
        super(ARCHIVO, new TypeToken<List<Aerolinea>>(){}.getType());
    }

    @Override
    public List<Aerolinea> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    @Override
    public Aerolinea obtenerPorId(int id) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void guardar(Aerolinea aerolinea) throws DatosInvalidosException {
        List<Aerolinea> aerolineas = obtenerTodos();
        if (aerolinea.getId() == 0) {
            aerolinea.setId(obtenerSiguienteId());
        }
        aerolineas.add(aerolinea);
        guardarDatos(aerolineas);
    }

    @Override
    public void actualizar(Aerolinea aerolinea) throws DatosInvalidosException {
        List<Aerolinea> aerolineas = obtenerTodos();
        boolean encontrado = false;
        for (int i = 0; i < aerolineas.size(); i++) {
            if (aerolineas.get(i).getId() == aerolinea.getId()) {
                aerolineas.set(i, aerolinea);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new DatosInvalidosException("Aerolínea con ID " + aerolinea.getId() + " no encontrada");
        }
        guardarDatos(aerolineas);
    }

    @Override
    public void eliminar(int id) throws DatosInvalidosException {
        List<Aerolinea> aerolineas = obtenerTodos();
        boolean eliminado = aerolineas.removeIf(a -> a.getId() == id);
        if (!eliminado) {
            throw new DatosInvalidosException("Aerolínea con ID " + id + " no encontrada");
        }
        guardarDatos(aerolineas);
    }

    @Override
    public int obtenerSiguienteId() throws DatosInvalidosException {
        List<Aerolinea> aerolineas = obtenerTodos();
        return aerolineas.stream()
                .mapToInt(Aerolinea::getId)
                .max()
                .orElse(0) + 1;
    }

    public Aerolinea obtenerPorNombre(String nombre) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

}