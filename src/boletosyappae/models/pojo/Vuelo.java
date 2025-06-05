package boletosyappae.models.pojo;

import java.time.LocalDateTime;
import java.util.List;

public class Vuelo {
    private int id;
    private int numeroPasajeros;
    private double costoBoleto;
    private int tiempoRecorrido; // en minutos
    private String ciudadSalida;
    private String ciudadLlegada;
    private LocalDateTime fechaHoraSalida;
    private LocalDateTime fechaHoraLlegada;
    private int avionId;
    private List<Integer> pilotosIds; // 2 pilotos
    private List<Integer> asistentesIds; // 4 asistentes

    public Vuelo() {}

    public Vuelo(int id, int numeroPasajeros, double costoBoleto, int tiempoRecorrido,
                 String ciudadSalida, String ciudadLlegada, LocalDateTime fechaHoraSalida,
                 LocalDateTime fechaHoraLlegada, int avionId, List<Integer> pilotosIds,
                 List<Integer> asistentesIds) {
        this.id = id;
        this.numeroPasajeros = numeroPasajeros;
        this.costoBoleto = costoBoleto;
        this.tiempoRecorrido = tiempoRecorrido;
        this.ciudadSalida = ciudadSalida;
        this.ciudadLlegada = ciudadLlegada;
        this.fechaHoraSalida = fechaHoraSalida;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.avionId = avionId;
        this.pilotosIds = pilotosIds;
        this.asistentesIds = asistentesIds;
    }

    // Getters y Setters completos
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNumeroPasajeros() { return numeroPasajeros; }
    public void setNumeroPasajeros(int numeroPasajeros) { this.numeroPasajeros = numeroPasajeros; }

    public double getCostoBoleto() { return costoBoleto; }
    public void setCostoBoleto(double costoBoleto) { this.costoBoleto = costoBoleto; }

    public int getTiempoRecorrido() { return tiempoRecorrido; }
    public void setTiempoRecorrido(int tiempoRecorrido) { this.tiempoRecorrido = tiempoRecorrido; }

    public String getCiudadSalida() { return ciudadSalida; }
    public void setCiudadSalida(String ciudadSalida) { this.ciudadSalida = ciudadSalida; }

    public String getCiudadLlegada() { return ciudadLlegada; }
    public void setCiudadLlegada(String ciudadLlegada) { this.ciudadLlegada = ciudadLlegada; }

    public LocalDateTime getFechaHoraSalida() { return fechaHoraSalida; }
    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) { this.fechaHoraSalida = fechaHoraSalida; }

    public LocalDateTime getFechaHoraLlegada() { return fechaHoraLlegada; }
    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) { this.fechaHoraLlegada = fechaHoraLlegada; }

    public int getAvionId() { return avionId; }
    public void setAvionId(int avionId) { this.avionId = avionId; }

    public List<Integer> getPilotosIds() { return pilotosIds; }
    public void setPilotosIds(List<Integer> pilotosIds) { this.pilotosIds = pilotosIds; }

    public List<Integer> getAsistentesIds() { return asistentesIds; }
    public void setAsistentesIds(List<Integer> asistentesIds) { this.asistentesIds = asistentesIds; }

    @Override
    public String toString() {
        return "Vuelo " + id + ": " + ciudadSalida + " → " + ciudadLlegada;
    }
}