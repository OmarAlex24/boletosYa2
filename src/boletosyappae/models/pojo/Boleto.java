package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase para representar un Boleto.
 * Ajustada para coincidir con los requerimientos del PDF y la estructura del JSON.
 *
 * Requerimientos del PDF para Boleto (implícitos de "comprar boletos"):
 * - Relación con Cliente (numCliente)
 * - Relación con Vuelo (idVuelo)
 * - Estado del boleto (estado)
 * - El PDF no detalla explícitamente los campos del boleto, pero el JSON `boletos.json` sí.
 * - idBoleto (String)
 * - fechaCompra
 * - horaCompra
 * - metodoPago
 * - idVuelo (String)
 * - numCliente (String)
 * - estado (Enum EstadoBoleto)
 * - Se añade numAsiento como se indica en el TODO del POJO original.
 */
public class Boleto {
    private String idBoleto; // Identificador del boleto (String, como en JSON)
    private LocalDate fechaCompra; // Fecha de compra (según JSON)
    private LocalTime horaCompra; // Hora de compra (según JSON)
    private String metodoPago; // Método de pago (según JSON)
    private String idVuelo; // ID del vuelo asociado (String, como en JSON)
    private String numCliente; // ID del cliente asociado (String, como en JSON)
    private EstadoBoleto estado; // Estado del boleto (Enum, como en JSON)
    private String numAsiento; // Número de asiento asignado (del TODO en POJO original)

    // Constructores
    public Boleto() {
    }

    public Boleto(String idBoleto, LocalDate fechaCompra, LocalTime horaCompra,
                  String metodoPago, String idVuelo, String numCliente, 
                  EstadoBoleto estado, String numAsiento) {
        this.idBoleto = idBoleto;
        this.fechaCompra = fechaCompra;
        this.horaCompra = horaCompra;
        this.metodoPago = metodoPago;
        this.idVuelo = idVuelo;
        this.numCliente = numCliente;
        this.estado = estado;
        this.numAsiento = numAsiento;
    }

    // Getters y Setters
    public String getIdBoleto() {
        return idBoleto;
    }

    public void setIdBoleto(String idBoleto) {
        this.idBoleto = idBoleto;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public LocalTime getHoraCompra() {
        return horaCompra;
    }

    public void setHoraCompra(LocalTime horaCompra) {
        this.horaCompra = horaCompra;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(String idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(String numCliente) {
        this.numCliente = numCliente;
    }

    public EstadoBoleto getEstado() {
        return estado;
    }

    public void setEstado(EstadoBoleto estado) {
        this.estado = estado;
    }

    public String getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(String numAsiento) {
        this.numAsiento = numAsiento;
    }

    @Override
    public String toString() {
        return String.format("Boleto{idBoleto='%s', numCliente='%s', idVuelo='%s', estado=%s, asiento='%s'}",
                idBoleto, numCliente, idVuelo, estado, numAsiento);
    }
}
