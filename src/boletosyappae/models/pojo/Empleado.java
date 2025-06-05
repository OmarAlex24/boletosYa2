package boletosyappae.models.pojo;

import java.time.LocalDate;

public abstract class Empleado {
    protected int idEmpleado;
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String telefono;
    protected String email;
    protected LocalDate fechaContratacion;
    protected double salario;
    protected TipoEmpleado tipoEmpleado;
    protected int idAerolinea;

    // Constructor
    public Empleado() {}

    public Empleado(int idEmpleado, String nombre, String apellidoPaterno,
                    String apellidoMaterno, String telefono, String email,
                    LocalDate fechaContratacion, double salario,
                    TipoEmpleado tipoEmpleado, int idAerolinea) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.email = email;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
        this.tipoEmpleado = tipoEmpleado;
        this.idAerolinea = idAerolinea;
    }

    // Getters and Setters
    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public TipoEmpleado getTipoEmpleado() { return tipoEmpleado; }
    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) { this.tipoEmpleado = tipoEmpleado; }

    public int getIdAerolinea() { return idAerolinea; }
    public void setIdAerolinea(int idAerolinea) { this.idAerolinea = idAerolinea; }

    public String getNombreCompleto() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", tipoEmpleado=" + tipoEmpleado +
                '}';
    }
}