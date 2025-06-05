package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Boleto;
import boletosyappae.models.pojo.EstadoBoleto;
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
                .filter(b -> b.getIdBoleto() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void guardar(Boleto boleto) throws DatosInvalidosException {
        List<Boleto> boletos = obtenerTodos();
        if (boleto.getIdBoleto() == 0) {
            boleto.setIdBoleto(obtenerSiguienteId());
        }
        boletos.add(boleto);
        guardarDatos(boletos);
    }

    @Override
    public void actualizar(Boleto boleto) throws DatosInvalidosException {
        List<Boleto> boletos = obtenerTodos();
        boolean encontrado = false;
        for (int i = 0; i < boletos.size(); i++) {
            if (boletos.get(i).getIdBoleto() == boleto.getIdBoleto()) {
                boletos.set(i, boleto);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new DatosInvalidosException("Boleto con ID " + boleto.getIdBoleto() + " no encontrado");
        }
        guardarDatos(boletos);
    }

    @Override
    public void eliminar(int id) throws DatosInvalidosException {
        List<Boleto> boletos = obtenerTodos();
        boolean eliminado = boletos.removeIf(b -> b.getIdBoleto() == id);
        if (!eliminado) {
            throw new DatosInvalidosException("Boleto con ID " + id + " no encontrado");
        }
        guardarDatos(boletos);
    }

    @Override
    public int obtenerSiguienteId() throws DatosInvalidosException {
        List<Boleto> boletos = obtenerTodos();
        return boletos.stream()
                .mapToInt(Boleto::getIdBoleto)
                .max()
                .orElse(0) + 1;
    }

    public List<Boleto> obtenerPorVuelo(int vueloId) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(b -> b.getIdVuelo() == vueloId)
                .collect(Collectors.toList());
    }

    public List<Boleto> obtenerPorCliente(int clienteId) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(b -> b.getIdCliente() == clienteId)
                .collect(Collectors.toList());
    }

    public List<Boleto> obtenerPorEstado(EstadoBoleto estado) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(b -> b.getEstado() == estado)
                .collect(Collectors.toList());
    }

    public int contarBoletosPorVuelo(int vueloId) throws DatosInvalidosException {
        return (int) obtenerTodos().stream()
                .filter(b -> b.getIdVuelo() == vueloId)
                .count();
    }

    public List<Boleto> obtenerBoletosActivos() throws DatosInvalidosException {
        return obtenerTodos().stream()
                .filter(b -> b.getEstado() == EstadoBoleto.CONFIRMADO)
                .collect(Collectors.toList());
    }

    public boolean existeBoletoParaAsiento(int vueloId, String numeroAsiento) throws DatosInvalidosException {
        return obtenerTodos().stream()
                .anyMatch(b -> b.getIdVuelo() == vueloId &&
                        b.getNumeroAsiento().equals(numeroAsiento) &&
                        (b.getEstado() == EstadoBoleto.CONFIRMADO;
    }
}