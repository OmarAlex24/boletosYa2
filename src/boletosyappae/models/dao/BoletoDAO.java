package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Boleto;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.stream.Collectors;

public class BoletoDAO extends GsonDAO<Boleto> {
    private static final String ARCHIVO = "datos/boletos.json";

    public BoletoDAO() {
        super(ARCHIVO, new TypeToken<List<Boleto>>(){}.getType());
    }

    @Override
    public List<Boleto> obtenerTodos() throws DatosInvalidosException {
        return cargarDatos();
    }

    @Override
    public Boleto obtenerPorId(int id) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void guardar(Boleto boleto) throws DatosInvalidosException {
        List<Boleto> boletos = obtenerTodos();
        boleto.setId(obtenerSiguienteId());
        boletos.add(boleto);
        guardarDatos(boletos);
    }

    @Override
    public void actualizar(Boleto boleto) throws DatosInvalidosException {
        List<Boleto> boletos = obtenerTodos();
        for (int i = 0; i < boletos.size(); i++) {
            if (boletos.get(i).getId() == boleto.getId()) {
                boletos.set(i, boleto);
                break;
            }
        }
        guardarDatos(boletos);
    }

    @Override
    public void eliminar(int id) throws DatosInvalidosException {
        List<Boleto> boletos = obtenerTodos();
        boletos.removeIf(b -> b.getId() == id);
        guardarDatos(boletos);
    }

    @Override
    public int obtenerSiguienteId() throws DatosInvalidosException {
        List<Boleto> boletos = obtenerTodos();
        return boletos.stream()
                .mapToInt(Boleto::getId)
                .max()
                .orElse(0) + 1;
    }

    public List<Boleto> obtenerPorVuelo(int vueloId) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(b -> b.getVueloId() == vueloId).collect(Collectors.toList());
    }

    public List<Boleto> obtenerPorCliente(int clienteId) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(b -> b.getClienteId() == clienteId).collect(Collectors.toList());
    }
}