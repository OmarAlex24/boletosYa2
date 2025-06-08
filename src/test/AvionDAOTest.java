package test;

// src/test/AvionDAOTest.java

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.dao.AvionDAO;
import boletosyappae.models.pojo.Avion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AvionDAOTest {

    private AvionDAO avionDAO;
    private Avion avionDePrueba;

    @BeforeEach
    void setUp() {
        avionDAO = new AvionDAO();
        avionDePrueba = new Avion("TEST-AVION-001", 100, "ModeloTest", 50000.0, 1); // Asume que existe Aerolinea con ID 1
    }

    @AfterEach
    void tearDown() throws DatosInvalidosException {
        try {
            avionDAO.eliminar("TEST-AVION-001");
        } catch (DatosInvalidosException e) {
            // El avión ya fue eliminado
        }
        try {
            avionDAO.eliminar("TEST-AVION-UPD");
        } catch (DatosInvalidosException e) {
            // El avión actualizado ya fue eliminado
        }
    }

    @Test
    void testGuardarYObtenerAvion() throws DatosInvalidosException {
        avionDAO.guardar(avionDePrueba);
        Avion avionObtenido = avionDAO.obtenerPorId("TEST-AVION-001");

        assertNotNull(avionObtenido);
        assertEquals("ModeloTest", avionObtenido.getModelo());
    }

    @Test
    void testActualizarAvion() throws DatosInvalidosException {
        avionDAO.guardar(avionDePrueba);
        Avion avionGuardado = avionDAO.obtenerPorId("TEST-AVION-001");

        avionGuardado.setModelo("ModeloActualizado");
        avionDAO.actualizar(avionGuardado);

        Avion avionActualizado = avionDAO.obtenerPorId(avionGuardado.getId());
        assertEquals("ModeloActualizado", avionActualizado.getModelo());
    }

    @Test
    void testEliminarAvion() throws DatosInvalidosException {
        avionDAO.guardar(avionDePrueba);
        Avion avionGuardado = avionDAO.obtenerPorId("TEST-AVION-001");

        avionDAO.eliminar(avionGuardado.getId());

        assertThrows(DatosInvalidosException.class, () -> {
            avionDAO.obtenerPorId(avionGuardado.getId());
        });
    }

    @Test
    void testGuardarAvionConIdentificadorDuplicadoLanzaExcepcion() throws DatosInvalidosException {
        avionDAO.guardar(avionDePrueba);
        Avion avionDuplicado = new Avion("TEST-AVION-001", 150, "OtroModelo", 60000.0, 2);

        assertThrows(DatosInvalidosException.class, () -> {
            avionDAO.guardar(avionDuplicado);
        }, "Se esperaba excepción al guardar un avión con identificador duplicado.");
    }
}