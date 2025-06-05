package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class Vuelo {
    private String idVuelo;
    private int numPasajeros;
    private float precioBoleto;
    private int tiempoRecorrido; // en minutos
    private String ciudadSalida;
    private String ciudadLlegada;
    private LocalDate fechaSalida;
    private LocalTime horaSalida;
    private LocalDate fechaLlegada;
    private LocalTime horaLlegada;
    private String idAvion;
    private List<String> pilotos; // IDs de pilotos
    private List<String> asistentesVuelo; // IDs de asistentes

    public Vuelo() {
        this.pilotos = new ArrayList<>();
        this.asistentesVuelo = new ArrayList<>();
    }

    public Vuelo(String idVuelo, int numPasajeros, float precioBoleto, int tiempoRecorrido,
                 String ciudadSalida, String ciudadLlegada, LocalDate fechaSalida,
                 LocalTime horaSalida, LocalDate fechaLlegada, LocalTime horaLlegada,
                 String idAvion) {
        this.idVuelo = idVuelo;
        this.numPasajeros = numPasajeros;
        this.precioBoleto = precioBoleto;
        this.tiempoRecorrido = tiempoRecorrido;
        this.ciudadSalida = ciudadSalida;
        this.ciudadLlegada = ciudadLlegada;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
        this.fechaLlegada = fechaLlegada;
        this.horaLlegada = horaLlegada;
        this.idAvion = idAvion;
        this.pilotos = new ArrayList<>();
        this.asistentesVuelo = new ArrayList<>();
    }

    // Getters y Setters
    public String getIdVuelo() { return idVuelo; }
    public void setIdVuelo(String idVuelo) { this.idVuelo = idVuelo; }

    public int getNumPasajeros() { return numPasajeros; }
    public void setNumPasajeros(int numPasajeros) { this.numPasajeros = numPasajeros; }

    public float getPrecioBoleto() { return precioBoleto; }
    public void setPrecioBoleto(float precioBoleto) { this.precioBoleto = precioBoleto; }

    public int getTiempoRecorrido() { return tiempoRecorrido; }
    public void setTiempoRecorrido(int tiempoRecorrido) { this.tiempoRecorrido = tiempoRecorrido; }

    public String getCiudadSalida() { return ciudadSalida; }
    public void setCiudadSalida(String ciudadSalida) { this.ciudadSalida = ciudadSalida; }

    public String getCiudadLlegada() { return ciudadLlegada; }
    public void setCiudadLlegada(String ciudadLlegada) { this.ciudadLlegada = ciudadLlegada; }

    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }

    public LocalTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalTime horaSalida) { this.horaSalida = horaSalida; }

    public LocalDate getFechaLlegada() { return fechaLlegada; }
    public void setFechaLlegada(LocalDate fechaLlegada) { this.fechaLlegada = fechaLlegada; }

    public LocalTime getHoraLlegada() { return horaLlegada; }
    public void setHoraLlegada(LocalTime horaLlegada) { this.horaLlegada = horaLlegada; }

    public String getIdAvion() { return idAvion; }
    public void setIdAvion(String idAvion) { this.idAvion = idAvion; }

    public List<String> getPilotos() { return pilotos; }
    public void setPilotos(List<String> pilotos) { this.pilotos = pilotos; }

    public List<String> getAsistentesVuelo() { return asistentesVuelo; }
    public void setAsistentesVuelo(List<String> asistentesVuelo) { this.asistentesVuelo = asistentesVuelo; }

    public void agregarPiloto(String idPiloto) {
        if (pilotos.size() < 2 && !pilotos.contains(idPiloto)) {
            pilotos.add(idPiloto);
        }
    }

    public void agregarAsistente(String idAsistente) {
        if (asistentesVuelo.size() < 4 && !asistentesVuelo.contains(idAsistente)) {
            asistentesVuelo.add(idAsistente);
        }
    }

    @Override
    public String toString() {
        return String.format("Vuelo{id='%s', %s->%s, fecha=%s, precio=%.2f}",
                idVuelo, ciudadSalida, ciudadLlegada, fechaSalida, precioBoleto);
    }
}