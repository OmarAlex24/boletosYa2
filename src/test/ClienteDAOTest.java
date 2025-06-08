package test;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.dao.ClienteDAO;
import boletosyappae.models.pojo.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.UUID;


public class ClienteDAOTest {

    private ClienteDAO clienteDAO;
    private Cliente clienteDePrueba;

    @BeforeEach
    void setUp() {
        clienteDAO = new ClienteDAO();
        clienteDePrueba = new Cliente(
                0,
                "CLI-TEST-" + UUID.randomUUID().toString().substring(0, 4),
                "NombrePrueba",
                "ApellidoPrueba",
                "NacionalidadPrueba",
                LocalDate.of(2000, 1, 1),
                "1234567890",
                "test@test.com"
        );
    }

    @AfterEach
    void tearDown() throws DatosInvalidosException {
        // Limpiar los datos de prueba después de cada test
        try {
            Cliente aBorrar = clienteDAO.obtenerPorId(clienteDePrueba.getNumCliente());
            clienteDAO.eliminar(aBorrar.getId());
        } catch (DatosInvalidosException e) {
            // El cliente ya fue eliminado o no se guardó, no hay problema.
        }
    }

    @Test
    void testGuardarYObtenerCliente() throws DatosInvalidosException {
        clienteDAO.guardar(clienteDePrueba);
        Cliente clienteObtenido = clienteDAO.obtenerPorId(clienteDePrueba.getNumCliente());

        assertNotNull(clienteObtenido, "El cliente no debería ser nulo.");
        assertEquals("NombrePrueba", clienteObtenido.getNombres(), "El nombre del cliente no coincide.");
    }

    @Test
    void testActualizarCliente() throws DatosInvalidosException {
        clienteDAO.guardar(clienteDePrueba);
        Cliente clienteGuardado = clienteDAO.obtenerPorId(clienteDePrueba.getNumCliente());

        clienteGuardado.setNombres("NombreActualizado");
        clienteDAO.actualizar(clienteGuardado);

        Cliente clienteActualizado = clienteDAO.obtenerPorId(clienteGuardado.getId());
        assertEquals("NombreActualizado", clienteActualizado.getNombres(), "El nombre del cliente debería haberse actualizado.");
    }

    @Test
    void testEliminarCliente() throws DatosInvalidosException {
        clienteDAO.guardar(clienteDePrueba);
        Cliente clienteGuardado = clienteDAO.obtenerPorId(clienteDePrueba.getNumCliente());
        assertNotNull(clienteGuardado, "El cliente debe existir para poder ser eliminado.");

        clienteDAO.eliminar(clienteGuardado.getId());

        assertThrows(DatosInvalidosException.class, () -> {
            clienteDAO.obtenerPorId(clienteGuardado.getId());
        }, "Se esperaba una excepción al intentar obtener un cliente eliminado.");
    }

    @Test
    void testGuardarClienteNuloLanzaExcepcion() {
        assertThrows(DatosInvalidosException.class, () -> {
            clienteDAO.guardar(null);
        }, "Se esperaba DatosInvalidosException al guardar un cliente nulo.");
    }
}