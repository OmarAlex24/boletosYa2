package test;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.dao.AerolineaDAO;
import boletosyappae.models.pojo.Aerolinea;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AerolineaDAOTest {

    private AerolineaDAO aerolineaDAO;
    private Aerolinea aerolineaDePrueba;

    @BeforeEach
    void setUp() {
        aerolineaDAO = new AerolineaDAO();
        aerolineaDePrueba = new Aerolinea(0, "ID-TEST-001", "Aerolínea de Prueba", "Dirección de Prueba", "Contacto de Prueba", "1234567890");
    }

    @AfterEach
    void tearDown() throws DatosInvalidosException {
        // Este método se ejecuta después de cada test para limpiar los datos de prueba.

        // Intenta eliminar la aerolínea por su nombre original.
        // Esto limpiará los datos creados en la mayoría de los tests.
        try {
            Aerolinea aBorrar = aerolineaDAO.obtenerPorNombre("Aerolínea de Prueba");
            aerolineaDAO.eliminar(aBorrar.getId());
        } catch (DatosInvalidosException e) {
            // Si no se encuentra, no hay problema. Significa que ya fue eliminada
            // o su nombre cambió (como en el test de actualización).
        }

        // Intenta eliminar la aerolínea por su nombre actualizado.
        // Esto es específicamente para limpiar después del test 'testActualizarAerolinea'.
        try {
            Aerolinea aBorrar = aerolineaDAO.obtenerPorNombre("Aerolínea Actualizada");
            aerolineaDAO.eliminar(aBorrar.getId());
        } catch (DatosInvalidosException e) {
            // Si no se encuentra, tampoco hay problema.
        }
    }

    @Test
    void testGuardarYObtenerAerolinea() throws DatosInvalidosException {
        // Guardar la aerolínea de prueba
        aerolineaDAO.guardar(aerolineaDePrueba);

        // Obtener la aerolínea por su nombre
        Aerolinea aerolineaObtenida = aerolineaDAO.obtenerPorNombre("Aerolínea de Prueba");

        // Verificar que la aerolínea obtenida no es nula y sus datos son correctos
        assertNotNull(aerolineaObtenida, "La aerolínea no debería ser nula");
        assertEquals("Aerolínea de Prueba", aerolineaObtenida.getNombre(), "El nombre de la aerolínea no coincide");
        assertEquals("Dirección de Prueba", aerolineaObtenida.getDireccion(), "La dirección no coincide");
    }

    @Test
    void testActualizarAerolinea() throws DatosInvalidosException {
        // Guardar la aerolínea de prueba
        aerolineaDAO.guardar(aerolineaDePrueba);
        Aerolinea aerolineaGuardada = aerolineaDAO.obtenerPorNombre("Aerolínea de Prueba");

        // Modificar los datos y actualizar
        aerolineaGuardada.setNombre("Aerolínea Actualizada");
        aerolineaDAO.actualizar(aerolineaGuardada);

        // Obtener la aerolínea actualizada y verificar los cambios
        Aerolinea aerolineaActualizada = aerolineaDAO.obtenerPorId(aerolineaGuardada.getId());
        assertEquals("Aerolínea Actualizada", aerolineaActualizada.getNombre(), "El nombre de la aerolínea debería haberse actualizado");
    }

    @Test
    void testEliminarAerolinea() throws DatosInvalidosException {
        // Guardar la aerolínea de prueba
        aerolineaDAO.guardar(aerolineaDePrueba);
        Aerolinea aerolineaGuardada = aerolineaDAO.obtenerPorNombre("Aerolínea de Prueba");

        // Eliminar la aerolínea
        aerolineaDAO.eliminar(aerolineaGuardada.getId());

        // Verificar que la aerolínea ya no existe
        assertThrows(DatosInvalidosException.class, () -> {
            aerolineaDAO.obtenerPorId(aerolineaGuardada.getId());
        }, "Se esperaba una excepción al intentar obtener una aerolínea eliminada");
    }

    @Test
    void testGuardarAerolineaNulaLanzaExcepcion() {
        // Verificar que intentar guardar una aerolínea nula lanza una excepción
        assertThrows(DatosInvalidosException.class, () -> {
            aerolineaDAO.guardar(null);
        }, "Se esperaba una excepción al guardar una aerolínea nula");
    }
}