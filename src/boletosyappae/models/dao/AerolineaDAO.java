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
        aerolinea.setId(obtenerSiguienteId());
        aerolineas.add(aerolinea);
        guardarDatos(aerolineas);
    }

    @Override
    public void actualizar(Aerolinea aerolinea) throws DatosInvalidosException {
        List<Aerolinea> aerolineas = obtenerTodos();
        for (int i = 0; i < aerolineas.size(); i++) {
            if (aerolineas.get(i).getId() == aerolinea.getId()) {
                aerolineas.set(i, aerolinea);
                break;
            }
        }
        guardarDatos(aerolineas);
    }

    @Override
    public void eliminar(int id) throws DatosInvalidosException {
        List<Aerolinea> aerolineas = obtenerTodos();
        aerolineas.removeIf(a -> a.getId() == id);
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
}