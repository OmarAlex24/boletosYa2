package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Boleto;
import boletosyappae.models.pojo.EstadoBoleto;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * DAO para la gestión de boletos.
 */
public class BoletoDAO extends GsonDAO<Boleto> {
    private static final String NOMBRE_ARCHIVO = "datos/boletos.json";

    public BoletoDAO() {
        super(NOMBRE_ARCHIVO, new TypeToken<List<Boleto>>(){}.getType());
    }

    @Override
    public List<Boleto> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    /**
     * No aplicable para Boleto, ya que su ID es String ("idBoleto").
     * Lanza UnsupportedOperationException.
     * @param id El ID del boleto (no utilizado).
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public Boleto obtenerPorId(int id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("El ID de Boleto es String. Use obtenerPorId(String).");
    }

    /**
     * Obtiene un boleto por su ID (String, ej: "BOL001").
     * @param idBoleto El ID String del boleto.
     * @return El boleto encontrado.
     * @throws DatosInvalidosException si el ID no corresponde a ningún boleto.
     */
    @Override
    public Boleto obtenerPorId(String idBoleto) throws DatosInvalidosException {
        if (idBoleto == null || idBoleto.trim().isEmpty()) {
            throw new DatosInvalidosException("El ID del boleto no puede ser nulo o vacío.");
        }
        Optional<Boleto> boletoOpt = obtenerTodos().stream()
                .filter(b -> b.getIdBoleto().equalsIgnoreCase(idBoleto.trim()))
                .findFirst();
        if (!boletoOpt.isPresent()) {
            throw new DatosInvalidosException("Boleto con ID '" + idBoleto + "' no encontrado.");
        }
        return boletoOpt.get();
    }

    @Override
    public void guardar(Boleto boleto) throws DatosInvalidosException {
        if (boleto == null || boleto.getIdBoleto() == null || boleto.getIdBoleto().trim().isEmpty()) {
            throw new DatosInvalidosException("El boleto o su ID no pueden ser nulos o vacíos.");
        }
        List<Boleto> boletos = obtenerTodos();
        boolean existe = boletos.stream().anyMatch(b -> b.getIdBoleto().equalsIgnoreCase(boleto.getIdBoleto()));
        if (existe) {
            throw new DatosInvalidosException("Ya existe un boleto con el ID " + boleto.getIdBoleto() + ". Use actualizar en su lugar.");
        }
        boletos.add(boleto);
        guardarDatos(boletos);
    }

    @Override
    public void actualizar(Boleto boleto) throws DatosInvalidosException {
        if (boleto == null || boleto.getIdBoleto() == null || boleto.getIdBoleto().trim().isEmpty()) {
            throw new DatosInvalidosException("El boleto a actualizar o su ID no pueden ser nulos o vacíos.");
        }
        List<Boleto> boletos = obtenerTodos();
        boolean encontrado = false;
        for (int i = 0; i < boletos.size(); i++) {
            if (boletos.get(i).getIdBoleto().equalsIgnoreCase(boleto.getIdBoleto())) {
                boletos.set(i, boleto);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new DatosInvalidosException("Boleto con ID " + boleto.getIdBoleto() + " no encontrado para actualizar.");
        }
        guardarDatos(boletos);
    }

    /**
     * No aplicable para Boleto, ya que su ID es String ("idBoleto").
     * Lanza UnsupportedOperationException.
     * @param id El ID del boleto (no utilizado).
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public void eliminar(int id) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("El ID de Boleto es String. Use eliminar(String).");
    }

    @Override
    public void eliminar(String idBoleto) throws DatosInvalidosException {
        if (idBoleto == null || idBoleto.trim().isEmpty()) {
            throw new DatosInvalidosException("El ID del boleto para eliminar no puede ser nulo o vacío.");
        }
        List<Boleto> boletos = obtenerTodos();
        boolean eliminado = boletos.removeIf(b -> b.getIdBoleto().equalsIgnoreCase(idBoleto.trim()));
        if (!eliminado) {
            throw new DatosInvalidosException("Boleto con ID '" + idBoleto + "' no encontrado para eliminar.");
        }
        guardarDatos(boletos);
    }

    /**
     * Los IDs de Boleto son Strings y no son numéricos secuenciales simples.
     * @throws UnsupportedOperationException siempre.
     */
    @Override
    public int obtenerSiguienteIdNumerico() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Los IDs de Boleto son Strings y no se generan numéricamente de forma secuencial simple por este DAO.");
    }

    /**
     * Obtiene una lista de boletos por el ID del vuelo.
     * @param idVuelo El ID del vuelo.
     * @return Una lista de boletos para el vuelo especificado.
     * @throws DatosInvalidosException si el ID del vuelo es nulo o vacío.
     */
    public List<Boleto> obtenerPorVuelo(String idVuelo) throws DatosInvalidosException {
        if (idVuelo == null || idVuelo.trim().isEmpty()) {
            throw new DatosInvalidosException("El ID del vuelo no puede ser nulo o vacío.");
        }
        return obtenerTodos().stream()
                .filter(b -> b.getIdVuelo().equalsIgnoreCase(idVuelo.trim()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una lista de boletos por el número de cliente.
     * @param numCliente El número de cliente.
     * @return Una lista de boletos para el cliente especificado.
     * @throws DatosInvalidosException si el número de cliente es nulo o vacío.
     */
    public List<Boleto> obtenerPorCliente(String numCliente) throws DatosInvalidosException {
        if (numCliente == null || numCliente.trim().isEmpty()) {
            throw new DatosInvalidosException("El número de cliente no puede ser nulo o vacío.");
        }
        return obtenerTodos().stream()
                .filter(b -> b.getNumCliente().equalsIgnoreCase(numCliente.trim()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene una lista de boletos por su estado.
     * @param estado El estado del boleto.
     * @return Una lista de boletos que coinciden con el estado especificado.
     * @throws DatosInvalidosException si el estado es nulo.
     */
    public List<Boleto> obtenerPorEstado(EstadoBoleto estado) throws DatosInvalidosException {
        if (estado == null) {
            throw new DatosInvalidosException("El estado del boleto no puede ser nulo.");
        }
        return obtenerTodos().stream()
                .filter(b -> b.getEstado() == estado)
                .collect(Collectors.toList());
    }

    /**
     * Cuenta el número de boletos para un vuelo específico.
     * @param idVuelo El ID del vuelo.
     * @return El número de boletos para el vuelo.
     * @throws DatosInvalidosException si el ID del vuelo es nulo o vacío.
     */
    public int contarBoletosPorVuelo(String idVuelo) throws DatosInvalidosException {
        if (idVuelo == null || idVuelo.trim().isEmpty()) {
            throw new DatosInvalidosException("El ID del vuelo para contar boletos no puede ser nulo o vacío.");
        }
        return (int) obtenerTodos().stream()
                .filter(b -> b.getIdVuelo().equalsIgnoreCase(idVuelo.trim()))
                .count();
    }

    /**
     * Verifica si existe un boleto para un asiento específico en un vuelo.
     * @param idVuelo El ID del vuelo.
     * @param numeroAsiento El número del asiento.
     * @return `true` si el asiento está ocupado, `false` en caso contrario.
     * @throws DatosInvalidosException si el ID del vuelo o el número de asiento son nulos o vacíos.
     */
    public boolean existeBoletoParaAsiento(String idVuelo, String numeroAsiento) throws DatosInvalidosException {
        if (idVuelo == null || idVuelo.trim().isEmpty() || numeroAsiento == null || numeroAsiento.trim().isEmpty()) {
            throw new DatosInvalidosException("El ID del vuelo y el número de asiento no pueden ser nulos o vacíos.");
        }
        return obtenerTodos().stream()
                .anyMatch(b -> b.getIdVuelo().equalsIgnoreCase(idVuelo.trim()) &&
                        b.getNumAsiento() != null &&
                        b.getNumAsiento().equalsIgnoreCase(numeroAsiento.trim()) &&
                        (b.getEstado() == EstadoBoleto.CONFIRMADO || b.getEstado() == EstadoBoleto.PENDIENTE));
    }
}