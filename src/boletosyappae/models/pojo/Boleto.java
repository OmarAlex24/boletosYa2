package boletosyappae.models.pojo;

import java.time.LocalDateTime;

public class Boleto {
    private int id;
    private int clienteId;
    private int vueloId;
    private double precio;
    private LocalDateTime fechaCompra;
    private String asiento;
    private EstadoBoleto estado;

    public Boleto() {
        this.fechaCompra = LocalDateTime.now();
        this.estado = EstadoBoleto.ACTIVO;
    }

    public Boleto(int id, int clienteId, int vueloId, double precio, String asiento) {
        this.id = id;
        this.clienteId = clienteId;
        this.vueloId = vueloId;
        this.precio = precio;
        this.asiento = asiento;
        this.fechaCompra = LocalDateTime.now();
        this.estado = EstadoBoleto.ACTIVO;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public int getVueloId() { return vueloId; }
    public void setVueloId(int vueloId) { this.vueloId = vueloId; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }

    public String getAsiento() { return asiento; }
    public void setAsiento(String asiento) { this.asiento = asiento; }

    public EstadoBoleto getEstado() { return estado; }
    public void setEstado(EstadoBoleto estado) { this.estado = estado; }
}