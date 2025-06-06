package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Avion;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.stream.Collectors;

public class AvionDAO extends GsonDAO<Avion> {
    private static final String ARCHIVO = "datos/aviones.json";

    public AvionDAO() {
        super(ARCHIVO, new TypeToken<List<Avion>>(){}.getType());
    }

    @Override
    public List<Avion> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    @Override
    public Avion obtenerPorId(int id) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void guardar(Avion avion) throws DatosInvalidosException {
        List<Avion> aviones = obtenerTodos();
        if (avion.getId() == 0) {
            avion.setId(obtenerSiguienteId());
        }
        aviones.add(avion);
        guardarDatos(aviones);
    }

    @Override
    public void actualizar(Avion avion) throws DatosInvalidosException {
        List<Avion> aviones = obtenerTodos();
        boolean encontrado = false;
        for (int i = 0; i < aviones.size(); i++) {
            if (aviones.get(i).getId() == avion.getId()) {
                aviones.set(i, avion);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new DatosInvalidosException("Avión con ID " + avion.getId() + " no encontrado");
        }
        guardarDatos(aviones);
    }

    @Override
    public void eliminar(int id) throws DatosInvalidosException {
        List<Avion> aviones = obtenerTodos();
        boolean eliminado = aviones.removeIf(a -> a.getId() == id);
        if (!eliminado) {
            throw new DatosInvalidosException("Avión con ID " + id + " no encontrado");
        }
        guardarDatos(aviones);
    }

    @Override
    public int obtenerSiguienteId() throws DatosInvalidosException {
        List<Avion> aviones = obtenerTodos();
        return aviones.stream()
                .mapToInt(Avion::getId)
                .max()
                .orElse(0) + 1;
    }

    //FIX buscar primero la aerolina y despues devolver la flotilla
    public List<Avion> obtenerPorAerolinea(int aerolineaId) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(a -> a.getId() == aerolineaId)
                .collect(Collectors.toList());
    }

    public List<Avion> obtenerPorModelo(String modelo) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(a -> a.getModelo().toLowerCase().contains(modelo.toLowerCase()))
                .collect(Collectors.toList());
    }
}