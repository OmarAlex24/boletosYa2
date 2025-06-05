package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Boleto {
    private String idBoleto;
    private LocalDate fechaCompra;
    private LocalTime horaCompra;
    private String metodoPago;
    private String idVuelo;
    private String numCliente;
    private EstadoBoleto estado;

    public Boleto() {}

    public Boleto(String idBoleto, LocalDate fechaCompra, LocalTime horaCompra,
                  String metodoPago, String idVuelo, String numCliente, EstadoBoleto estado) {
        this.idBoleto = idBoleto;
        this.fechaCompra = fechaCompra;
        this.horaCompra = horaCompra;
        this.metodoPago = metodoPago;
        this.idVuelo = idVuelo;
        this.numCliente = numCliente;
        this.estado = estado;
    }

    // Getters y Setters
    public String getIdBoleto() { return idBoleto; }
    public void setIdBoleto(String idBoleto) { this.idBoleto = idBoleto; }

    public LocalDate getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDate fechaCompra) { this.fechaCompra = fechaCompra; }

    public LocalTime getHoraCompra() { return horaCompra; }
    public void setHoraCompra(LocalTime horaCompra) { this.horaCompra = horaCompra; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getIdVuelo() { return idVuelo; }
    public void setIdVuelo(String idVuelo) { this.idVuelo = idVuelo; }

    public String getNumCliente() { return numCliente; }
    public void setNumCliente(String numCliente) { this.numCliente = numCliente; }

    public EstadoBoleto getEstado() { return estado; }
    public void setEstado(EstadoBoleto estado) { this.estado = estado; }

    @Override
    public String toString() {
        return String.format("Boleto{id='%s', cliente='%s', vuelo='%s', estado=%s}",
                idBoleto, numCliente, idVuelo, estado);
    }
}