package boletosyappae.models.pojo;

public class Avion {
    private int id;
    private String identificador;
    private int capacidad;
    private String modelo;
    private double peso;

    public Avion(String identificador, int capacidad, String modelo, double peso, int id) {
        this.id = id;
        this.identificador = identificador;
        this.capacidad = capacidad;
        this.modelo = modelo;
        this.peso = peso;
    }

    // Getters y setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificador() { return identificador; }
    public void setIdentificador(String identificador) { this.identificador = identificador; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
}