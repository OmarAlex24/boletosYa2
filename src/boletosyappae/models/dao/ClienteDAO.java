package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Cliente;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * DAO para la gestión de clientes.
 */
public class ClienteDAO extends GsonDAO<Cliente> {
    private static final String NOMBRE_ARCHIVO = "datos/clientes.json";

    public ClienteDAO() {
        super(NOMBRE_ARCHIVO, new TypeToken<List<Cliente>>(){}.getType());
    }

    @Override
    public List<Cliente> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    /**
     * Obtiene un cliente por su ID numérico interno del DAO.
     * @param id El ID numérico del cliente.
     * @return El cliente encontrado.
     * @throws DatosInvalidosException si el ID no corresponde a ningún cliente.
     */
    @Override
    public Cliente obtenerPorId(int id) throws DatosInvalidosException {
        Optional<Cliente> clienteOpt = obtenerTodos().stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        if (!clienteOpt.isPresent()) {
            throw new DatosInvalidosException("Cliente con ID interno " + id + " no encontrado.");
        }
        return clienteOpt.get();
    }

    /**
     * Obtiene un cliente por su número de cliente (String, ej: "CLI001").
     * @param numCliente El número de cliente String.
     * @return El cliente encontrado.
     * @throws DatosInvalidosException si el número de cliente no corresponde a ningún cliente.
     */
    @Override
    public Cliente obtenerPorId(String numCliente) throws DatosInvalidosException {
        if (numCliente == null || numCliente.trim().isEmpty()) {
            throw new DatosInvalidosException("El número de cliente no puede ser nulo o vacío.");
        }
        Optional<Cliente> clienteOpt = obtenerTodos().stream()
                .filter(c -> c.getNumCliente().equalsIgnoreCase(numCliente.trim()))
                .findFirst();
        if (!clienteOpt.isPresent()) {
            throw new DatosInvalidosException("Cliente con número '" + numCliente + "' no encontrado.");
        }
        return clienteOpt.get();
    }


    @Override
    public void guardar(Cliente cliente) throws DatosInvalidosException {
        if (cliente == null) {
            throw new DatosInvalidosException("El cliente no puede ser nulo.");
        }
        List<Cliente> clientes = obtenerTodos();

        boolean numClienteExiste = clientes.stream()
                .anyMatch(c -> c.getNumCliente().equalsIgnoreCase(cliente.getNumCliente()));
        if (numClienteExiste) {
            throw new DatosInvalidosException("Ya existe un cliente con el número " + cliente.getNumCliente() + ".");
        }

        if (cliente.getId() == 0) {
            cliente.setId(obtenerSiguienteIdNumerico());
        } else {
            boolean idNumericoExiste = clientes.stream().anyMatch(c -> c.getId() == cliente.getId());
            if (idNumericoExiste) {
                throw new DatosInvalidosException("Ya existe un cliente con el ID numérico " + cliente.getId() + ". Use actualizar en su lugar o revise la asignación de ID.");
            }
        }
        clientes.add(cliente);
        guardarDatos(clientes);
    }

    @Override
    public void actualizar(Cliente cliente) throws DatosInvalidosException {
        if (cliente == null) {
            throw new DatosInvalidosException("El cliente a actualizar no puede ser nulo.");
        }
        List<Cliente> clientes = obtenerTodos();
        boolean encontrado = false;
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                final String numClienteActualizar = cliente.getNumCliente();
                final int idActualizar = cliente.getId();
                boolean otroConMismoNumCliente = clientes.stream()
                        .anyMatch(c -> c.getNumCliente().equalsIgnoreCase(numClienteActualizar) && c.getId() != idActualizar);
                if (otroConMismoNumCliente) {
                    throw new DatosInvalidosException("Otro cliente ya existe con el número " + numClienteActualizar);
                }
                clientes.set(i, cliente);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new DatosInvalidosException("Cliente con ID interno " + cliente.getId() + " no encontrado para actualizar.");
        }
        guardarDatos(clientes);
    }

    /**
     * Elimina un cliente por su ID numérico interno.
     */
    @Override
    public void eliminar(int id) throws DatosInvalidosException {
        List<Cliente> clientes = obtenerTodos();
        boolean eliminado = clientes.removeIf(c -> c.getId() == id);
        if (!eliminado) {
            throw new DatosInvalidosException("Cliente con ID interno " + id + " no encontrado para eliminar.");
        }
        guardarDatos(clientes);
    }

    /**
     * Elimina un cliente por su número de cliente String.
     */
    @Override
    public void eliminar(String numCliente) throws DatosInvalidosException {
        if (numCliente == null || numCliente.trim().isEmpty()) {
            throw new DatosInvalidosException("El número de cliente para eliminar no puede ser nulo o vacío.");
        }
        List<Cliente> clientes = obtenerTodos();
        boolean eliminado = clientes.removeIf(c -> c.getNumCliente().equalsIgnoreCase(numCliente.trim()));
        if (!eliminado) {
            throw new DatosInvalidosException("Cliente con número '" + numCliente + "' no encontrado para eliminar.");
        }
        guardarDatos(clientes);
    }

    @Override
    public int obtenerSiguienteIdNumerico() throws DatosInvalidosException {
        try {
            return obtenerTodos().stream()
                    .mapToInt(Cliente::getId)
                    .max()
                    .orElse(0) + 1;
        } catch (NoSuchElementException e) {
            return 1;
        }
    }
}