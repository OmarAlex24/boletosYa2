package test;
// src/test/BoletoDAOTest.java

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.exceptions.VueloSinCapacidadException;
import boletosyappae.models.dao.AvionDAO;
import boletosyappae.models.dao.BoletoDAO;
import boletosyappae.models.dao.VueloDAO;
import boletosyappae.models.pojo.Avion;
import boletosyappae.models.pojo.Boleto;
import boletosyappae.models.pojo.EstadoBoleto;
import boletosyappae.models.pojo.Vuelo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class BoletoDAOTest {

    private BoletoDAO boletoDAO;
    private VueloDAO vueloDAO;
    private AvionDAO avionDAO;
    private Boleto boletoDePrueba;
    private Vuelo vueloDePrueba;

    @BeforeEach
    void setUp() throws DatosInvalidosException {
        boletoDAO = new BoletoDAO();
        vueloDAO = new VueloDAO();
        avionDAO = new AvionDAO();

        // Asegurarse de que el vuelo y avión de prueba existen para las pruebas
        vueloDePrueba = vueloDAO.obtenerTodos().get(0); // Tomar el primer vuelo disponible

        boletoDePrueba = new Boleto(
                "BOL-TEST-" + UUID.randomUUID().toString().substring(0, 4),
                LocalDate.now(),
                LocalTime.now(),
                "TaquillaTest",
                vueloDePrueba.getIdVuelo(),
                "CLI001", // Asume que el cliente CLI001 existe
                EstadoBoleto.CONFIRMADO,
                "A1"
        );
    }

    @AfterEach
    void tearDown() throws DatosInvalidosException {
        try {
            boletoDAO.eliminar(boletoDePrueba.getIdBoleto());
        } catch (DatosInvalidosException e) {
            // El boleto ya fue eliminado.
        }
    }

    @Test
    void testGuardarYObtenerBoleto() throws DatosInvalidosException {
        boletoDAO.guardar(boletoDePrueba);
        Boleto boletoObtenido = boletoDAO.obtenerPorId(boletoDePrueba.getIdBoleto());

        assertNotNull(boletoObtenido);
        assertEquals("CLI001", boletoObtenido.getNumCliente());
    }

    @Test
    void testContarBoletosPorVuelo() throws DatosInvalidosException {
        int boletosAntes = boletoDAO.contarBoletosPorVuelo(vueloDePrueba.getIdVuelo());
        boletoDAO.guardar(boletoDePrueba);
        int boletosDespues = boletoDAO.contarBoletosPorVuelo(vueloDePrueba.getIdVuelo());

        assertEquals(boletosAntes + 1, boletosDespues, "El contador de boletos debería haber aumentado en uno.");
    }
}