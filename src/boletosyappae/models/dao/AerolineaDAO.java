package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Aerolinea;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class AerolineaDAO extends GsonDAO<Aerolinea> {
    private static final String NOMBRE_ARCHIVO = "datos/aerolineas.json";

    public AerolineaDAO() {
        super(NOMBRE_ARCHIVO, new TypeToken<List<Aerolinea>>(){}.getType());
    }

    @Override
    public List<Aerolinea> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    /**
     * Obtiene una aerolínea por su ID numérico.
     * @param id El ID numérico de la aerolínea.
     * @return La aerolínea encontrada.
     * @throws DatosInvalidosException si el ID no corresponde a ninguna aerolínea.
     */
    @Override
    public Aerolinea obtenerPorId(int id) throws DatosInvalidosException {
        Optional<Aerolinea> aerolineaOpt = obtenerTodos().stream()
                .filter(a -> a.getId() == id)
                .findFirst();
        if (!aerolineaOpt.isPresent()) {
            throw new DatosInvalidosException("Aerolínea con ID " + id + " no encontrada.");
        }
        return aerolineaOpt.get();
    }
    
    /**
     * No aplicable para Aerolinea, ya que su ID es numérico.
     * Lanza UnsupportedOperationException.
     */
    @Override
    public Aerolinea obtenerPorId(String id) throws DatosInvalidosException {
        throw new UnsupportedOperationException("El ID de Aerolinea es numérico. Use obtenerPorId(int).");
    }


    @Override
    public void guardar(Aerolinea aerolinea) throws DatosInvalidosException {
        if (aerolinea == null) {
            throw new DatosInvalidosException("La aerolínea no puede ser nula.");
        }
        List<Aerolinea> aerolineas = obtenerTodos();
        // Asignar nuevo ID si es una nueva aerolínea (ID es 0 o no establecido)
        if (aerolinea.getId() == 0) { // Asumiendo que 0 indica nuevo objeto
            aerolinea.setId(obtenerSiguienteIdNumerico());
        } else {
            // Verificar si ya existe una aerolínea con ese ID para evitar duplicados al "guardar" una existente.
            boolean existe = aerolineas.stream().anyMatch(a -> a.getId() == aerolinea.getId());
            if (existe) {
                throw new DatosInvalidosException("Ya existe una aerolínea con el ID " + aerolinea.getId() + ". Use actualizar en su lugar.");
            }
        }
        aerolineas.add(aerolinea);
        guardarDatos(aerolineas);
    }

    @Override
    public void actualizar(Aerolinea aerolinea) throws DatosInvalidosException {
        if (aerolinea == null) {
            throw new DatosInvalidosException("La aerolínea a actualizar no puede ser nula.");
        }
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
            throw new DatosInvalidosException("Aerolínea con ID " + aerolinea.getId() + " no encontrada para actualizar.");
        }
        guardarDatos(aerolineas);
    }

    @Override
    public void eliminar(int id) throws DatosInvalidosException {
        List<Aerolinea> aerolineas = obtenerTodos();
        boolean eliminado = aerolineas.removeIf(a -> a.getId() == id);
        if (!eliminado) {
            throw new DatosInvalidosException("Aerolínea con ID " + id + " no encontrada para eliminar.");
        }
        guardarDatos(aerolineas);
    }
    
    /**
     * No aplicable para Aerolinea, ya que su ID es numérico.
     * Lanza UnsupportedOperationException.
     */
    @Override
    public void eliminar(String id) throws DatosInvalidosException {
         throw new UnsupportedOperationException("El ID de Aerolinea es numérico. Use eliminar(int).");
    }


    @Override
    public int obtenerSiguienteIdNumerico() throws DatosInvalidosException {
        try {
            return obtenerTodos().stream()
                    .mapToInt(Aerolinea::getId)
                    .max()
                    .orElse(0) + 1;
        } catch (NoSuchElementException e) { // Para el caso de .max() en lista vacía antes de orElse
            return 1;
        }
    }

    public Aerolinea obtenerPorNombre(String nombre) throws DatosInvalidosException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre para buscar no puede ser vacío.");
        }
        Optional<Aerolinea> aerolineaOpt = obtenerTodos().stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombre.trim()))
                .findFirst();
        if (!aerolineaOpt.isPresent()) {
            throw new DatosInvalidosException("Aerolínea con nombre '" + nombre + "' no encontrada.");
        }
        return aerolineaOpt.get();
    }
}
