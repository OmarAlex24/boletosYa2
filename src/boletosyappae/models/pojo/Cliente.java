package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int id;
    private String nombres;
    private String apellidos;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
    private List<String> boletosComprados;

    public Cliente(int id, String nombres, String apellidos,
                   String nacionalidad, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.boletosComprados = new ArrayList<>();
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public List<String> getBoletosComprados() { return boletosComprados; }
    public void setBoletosComprados(List<String> boletosComprados) { this.boletosComprados = boletosComprados; }

    public String getNombreCompleto() { return nombres + " " + apellidos; }
}