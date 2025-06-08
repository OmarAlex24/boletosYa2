package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Avion;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * DAO para la gestión de aviones.
 */
public class AvionDAO extends GsonDAO<Avion> {
    private static final String NOMBRE_ARCHIVO = "datos/aviones.json";

    public AvionDAO() {
        super(NOMBRE_ARCHIVO, new TypeToken<List<Avion>>(){}.getType());
    }

    @Override
    public List<Avion> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    /**
     * Obtiene un avión por su ID numérico interno del DAO.
     * @param id El ID numérico del avión.
     * @return El avión encontrado.
     * @throws DatosInvalidosException si el ID no corresponde a ningún avión.
     */
    @Override
    public Avion obtenerPorId(int id) throws DatosInvalidosException {
        Optional<Avion> avionOpt = obtenerTodos().stream()
                .filter(a -> a.getId() == id)
                .findFirst();
        if (!avionOpt.isPresent()) {
            throw new DatosInvalidosException("Avión con ID interno " + id + " no encontrado.");
        }
        return avionOpt.get();
    }

    /**
     * Obtiene un avión por su identificador de negocio (String, ej: "A001").
     * @param identificador El identificador String del avión.
     * @return El avión encontrado.
     * @throws DatosInvalidosException si el identificador no corresponde a ningún avión.
     */
    @Override
    public Avion obtenerPorId(String identificador) throws DatosInvalidosException {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new DatosInvalidosException("El identificador del avión no puede ser nulo o vacío.");
        }
        Optional<Avion> avionOpt = obtenerTodos().stream()
                .filter(a -> a.getIdentificador().equalsIgnoreCase(identificador.trim()))
                .findFirst();
        if (!avionOpt.isPresent()) {
            throw new DatosInvalidosException("Avión con identificador '" + identificador + "' no encontrado.");
        }
        return avionOpt.get();
    }


    @Override
    public void guardar(Avion avion) throws DatosInvalidosException {
        if (avion == null) {
            throw new DatosInvalidosException("El avión no puede ser nulo.");
        }
        List<Avion> aviones = obtenerTodos();

        boolean identificadorExiste = aviones.stream()
                .anyMatch(a -> a.getIdentificador().equalsIgnoreCase(avion.getIdentificador()));
        if (identificadorExiste) {
            throw new DatosInvalidosException("Ya existe un avión con el identificador " + avion.getIdentificador() + ".");
        }

        if (avion.getId() == 0) {
            avion.setId(obtenerSiguienteIdNumerico());
        } else {
            boolean idNumericoExiste = aviones.stream().anyMatch(a -> a.getId() == avion.getId());
            if (idNumericoExiste) {
                throw new DatosInvalidosException("Ya existe un avión con el ID numérico " + avion.getId() + ". Use actualizar en su lugar o revise la asignación de ID.");
            }
        }
        aviones.add(avion);
        guardarDatos(aviones);
    }

    @Override
    public void actualizar(Avion avion) throws DatosInvalidosException {
        if (avion == null) {
            throw new DatosInvalidosException("El avión a actualizar no puede ser nulo.");
        }
        List<Avion> aviones = obtenerTodos();
        boolean encontrado = false;
        for (int i = 0; i < aviones.size(); i++) {
            if (aviones.get(i).getId() == avion.getId()) {
                final String identificadorActualizar = avion.getIdentificador();
                final int idActualizar = avion.getId();
                boolean otroConMismoIdentificador = aviones.stream()
                        .anyMatch(a -> a.getIdentificador().equalsIgnoreCase(identificadorActualizar) && a.getId() != idActualizar);
                if (otroConMismoIdentificador) {
                    throw new DatosInvalidosException("Otro avión ya existe con el identificador " + identificadorActualizar);
                }
                aviones.set(i, avion);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new DatosInvalidosException("Avión con ID interno " + avion.getId() + " no encontrado para actualizar.");
        }
        guardarDatos(aviones);
    }

    /**
     * Elimina un avión por su ID numérico interno.
     */
    @Override
    public void eliminar(int id) throws DatosInvalidosException {
        List<Avion> aviones = obtenerTodos();
        boolean eliminado = aviones.removeIf(a -> a.getId() == id);
        if (!eliminado) {
            throw new DatosInvalidosException("Avión con ID interno " + id + " no encontrado para eliminar.");
        }
        guardarDatos(aviones);
    }

    /**
     * Elimina un avión por su identificador String.
     */
    @Override
    public void eliminar(String identificador) throws DatosInvalidosException {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new DatosInvalidosException("El identificador del avión para eliminar no puede ser nulo o vacío.");
        }
        List<Avion> aviones = obtenerTodos();
        boolean eliminado = aviones.removeIf(a -> a.getIdentificador().equalsIgnoreCase(identificador.trim()));
        if (!eliminado) {
            throw new DatosInvalidosException("Avión con identificador '" + identificador + "' no encontrado para eliminar.");
        }
        guardarDatos(aviones);
    }

    @Override
    public int obtenerSiguienteIdNumerico() throws DatosInvalidosException {
        try {
            return obtenerTodos().stream()
                    .mapToInt(Avion::getId)
                    .max()
                    .orElse(0) + 1;
        } catch (NoSuchElementException e) {
            return 1;
        }
    }

    /**
     * Obtiene una lista de aviones por el ID de la aerolínea.
     * @param idAerolinea El ID de la aerolínea.
     * @return Una lista de aviones pertenecientes a la aerolínea.
     * @throws DatosInvalidosException si hay un problema al cargar los datos.
     */
    public List<Avion> obtenerPorAerolinea(int idAerolinea) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(a -> a.getIdAerolinea() == idAerolinea)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una lista de aviones por su modelo.
     * @param modelo El modelo del avión.
     * @return Una lista de aviones que coinciden con el modelo.
     * @throws DatosInvalidosException si hay un problema al cargar los datos.
     */
    public List<Avion> obtenerPorModelo(String modelo) throws DatosInvalidosException {
        if (modelo == null || modelo.trim().isEmpty()) {
            return obtenerTodos();
        }
        String modeloBusqueda = modelo.toLowerCase().trim();
        return obtenerTodos().stream()
                .filter(a -> a.getModelo().toLowerCase().contains(modeloBusqueda))
                .collect(Collectors.toList());
    }
}