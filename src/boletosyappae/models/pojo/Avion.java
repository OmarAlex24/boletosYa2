package boletosyappae.models.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Clase para representar un Avión.
 */
public class Avion {

    private int id;

    @SerializedName("idAvion")
    private String identificador;

    private String modelo;
    private int capacidad;
    private double peso;
    private int idAerolinea;

    public Avion() {
    }

    public Avion(String identificador, int capacidad, String modelo, double peso, int idAerolinea) {
        this.identificador = identificador;
        this.capacidad = capacidad;
        this.modelo = modelo;
        this.peso = peso;
        this.idAerolinea = idAerolinea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getIdAerolinea() {
        return idAerolinea;
    }

    public void setIdAerolinea(int idAerolinea) {
        this.idAerolinea = idAerolinea;
    }

    @Override
    public String toString() {
        return "Avion{"
                + "identificador='" + identificador + '\''
                + ", modelo='" + modelo + '\''
                + '}';
    }
}