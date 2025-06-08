package test;

// src/test/VueloDAOTest.java

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.dao.VueloDAO;
import boletosyappae.models.pojo.Vuelo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

public class VueloDAOTest {

    private VueloDAO vueloDAO;
    private Vuelo vueloDePrueba;

    @BeforeEach
    void setUp() {
        vueloDAO = new VueloDAO();
        vueloDePrueba = new Vuelo();
        vueloDePrueba.setIdVuelo("FL-TEST-999");
        vueloDePrueba.setCiudadSalida("OrigenTest");
        vueloDePrueba.setCiudadLlegada("DestinoTest");
        vueloDePrueba.setFechaSalida(LocalDate.now().plusDays(10));
        vueloDePrueba.setHoraSalida(LocalTime.of(10, 0));
        vueloDePrueba.setPilotos(Arrays.asList("PIL001", "PIL002"));
        vueloDePrueba.setAsistentesVuelo(Collections.emptyList());
        vueloDePrueba.setIdAvion("EC-MAD");
    }

    @AfterEach
    void tearDown() throws DatosInvalidosException {
        try {
            vueloDAO.eliminar("FL-TEST-999");
        } catch (DatosInvalidosException e) {
            // El vuelo ya fue eliminado
        }
    }

    @Test
    void testGuardarYObtenerVuelo() throws DatosInvalidosException {
        vueloDAO.guardar(vueloDePrueba);
        Vuelo vueloObtenido = vueloDAO.obtenerPorId("FL-TEST-999");

        assertNotNull(vueloObtenido);
        assertEquals("OrigenTest", vueloObtenido.getCiudadSalida());
    }

    @Test
    void testActualizarVuelo() throws DatosInvalidosException {
        vueloDAO.guardar(vueloDePrueba);
        Vuelo vueloGuardado = vueloDAO.obtenerPorId("FL-TEST-999");

        vueloGuardado.setCiudadLlegada("DestinoActualizado");
        vueloDAO.actualizar(vueloGuardado);

        Vuelo vueloActualizado = vueloDAO.obtenerPorId("FL-TEST-999");
        assertEquals("DestinoActualizado", vueloActualizado.getCiudadLlegada());
    }

    @Test
    void testEliminarVuelo() throws DatosInvalidosException {
        vueloDAO.guardar(vueloDePrueba);
        assertNotNull(vueloDAO.obtenerPorId("FL-TEST-999"));

        vueloDAO.eliminar("FL-TEST-999");

        assertThrows(DatosInvalidosException.class, () -> {
            vueloDAO.obtenerPorId("FL-TEST-999");
        });
    }
}