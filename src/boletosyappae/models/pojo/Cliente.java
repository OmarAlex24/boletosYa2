package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para representar un Cliente.
 */
public class Cliente {
    private int id;
    private String numCliente;
    private String nombres;
    private String apellidos;
    private String nacionalidad;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String email;
    private List<String> idsBoletosComprados;

    public Cliente() {
        this.idsBoletosComprados = new ArrayList<>();
    }

    public Cliente(int id, String numCliente, String nombres, String apellidos,
                   String nacionalidad, LocalDate fechaNacimiento, String telefono, String email) {
        this.id = id;
        this.numCliente = numCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.idsBoletosComprados = new ArrayList<>();
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(String numCliente, String nombres, String apellidos,
                   String nacionalidad, LocalDate fechaNacimiento, String telefono, String email) {
        this.numCliente = numCliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.fechaNacimiento = fechaNacimiento;
        this.idsBoletosComprados = new ArrayList<>();
        this.telefono = telefono;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(String numCliente) {
        this.numCliente = numCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<String> getIdsBoletosComprados() {
        return idsBoletosComprados;
    }

    public void setIdsBoletosComprados(List<String> idsBoletosComprados) {
        this.idsBoletosComprados = idsBoletosComprados;
    }

    public void agregarIdBoletoComprado(String idBoleto) {
        if (this.idsBoletosComprados == null) {
            this.idsBoletosComprados = new ArrayList<>();
        }
        if (idBoleto != null && !this.idsBoletosComprados.contains(idBoleto)) {
            this.idsBoletosComprados.add(idBoleto);
        }
    }

    public String getNombreCompleto() {
        return (nombres != null ? nombres : "") + " " + (apellidos != null ? apellidos : "");
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", numCliente='" + numCliente + '\'' +
                ", nombreCompleto='" + getNombreCompleto().trim() + '\'' +
                '}';
    }
}