package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para representar un Cliente.
 * Ajustada para coincidir con los requerimientos del PDF y la estructura del JSON.
 *
 * Requerimientos del PDF para Cliente:
 * - Nombre o nombres (mapeado a nombres)
 * - Apellidos (mapeado a apellidos)
 * - Nacionalidad
 * - Fecha de nacimiento (mapeado a fechaNacimiento)
 * - Pueden comprar boletos en uno o varios vuelos (relación con Boleto)
 * - El JSON `clientes.json` usa `numCliente` como String identificador.
 */
public class Cliente {
    // ID interno para el DAO. El JSON usa `numCliente` (String).
    // Se puede usar `id` (int) para el DAO y `numCliente` (String) como el identificador de negocio.
    private int id; // ID numérico interno para el DAO
    private String numCliente; // Identificador del cliente (String, como en JSON)
    private String nombres; // Nombres del cliente (según PDF y JSON (nombre, paterno, materno))
    private String apellidos; // Apellidos del cliente (concatenación de paterno y materno del JSON)
    private String nacionalidad; // Nacionalidad (según PDF y JSON)
    private LocalDate fechaNacimiento; // Fecha de nacimiento (según PDF y JSON (fechaNac))
    private String telefono;
    private String email;
    private List<String> idsBoletosComprados;


    // Constructores
    public Cliente() {
        this.idsBoletosComprados = new ArrayList<>();
    }
    
    // Constructor completo incluyendo id interno del DAO.
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

    // Constructor sin id interno, para cuando el DAO lo asigna.
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


    // Getters y setters
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
