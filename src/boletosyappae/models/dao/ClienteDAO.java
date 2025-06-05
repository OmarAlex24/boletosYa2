package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Cliente;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class ClienteDAO extends GsonDAO<Cliente> {
    private static final String ARCHIVO = "datos/clientes.json";

    public ClienteDAO() {
        super(ARCHIVO, new TypeToken<List<Cliente>>(){}.getType());
    }

    @Override
    public List<Cliente> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    @Override
    public Cliente obtenerPorId(int id) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void guardar(Cliente cliente) throws DatosInvalidosException {
        List<Cliente> clientes = obtenerTodos();
        cliente.setId(obtenerSiguienteId());
        clientes.add(cliente);
        guardarDatos(clientes);
    }

    @Override
    public void actualizar(Cliente cliente) throws DatosInvalidosException {
        List<Cliente> clientes = obtenerTodos();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente);
                break;
            }
        }
        guardarDatos(clientes);
    }

    @Override
    public void eliminar(int id) throws DatosInvalidosException {
        List<Cliente> clientes = obtenerTodos();
        clientes.removeIf(c -> c.getId() == id);
        guardarDatos(clientes);
    }

    @Override
    public int obtenerSiguienteId() throws DatosInvalidosException {
        List<Cliente> clientes = obtenerTodos();
        return clientes.stream()
                .mapToInt(Cliente::getId)
                .max()
                .orElse(0) + 1;
    }
}