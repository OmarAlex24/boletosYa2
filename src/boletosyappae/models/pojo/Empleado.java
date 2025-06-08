package boletosyappae.models.pojo;

import java.time.LocalDate;

/**
 * Clase abstracta base para todos los empleados.
 */
public abstract class Empleado {
    protected String numEmpleado;
    protected String nombre;
    protected String direccion;
    protected LocalDate fechaNac;
    protected String genero;
    protected double sueldo;
    protected String nombreUsuario;
    protected String contraseña;
    protected String correo;
    protected TipoEmpleado tipo;

    public Empleado() {
    }

    public Empleado(String numEmpleado, String nombre, String direccion, LocalDate fechaNac, String genero,
                    double sueldo, String nombreUsuario, String contraseña, String correo, TipoEmpleado tipo) {
        this.numEmpleado = numEmpleado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaNac = fechaNac;
        this.genero = genero;
        this.sueldo = sueldo;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.tipo = tipo;
    }

    public String getNumEmpleado() {
        return numEmpleado;
    }

    public void setNumEmpleado(String numEmpleado) {
        this.numEmpleado = numEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    /**
     * Asigna la contraseña.
     * @param contrasena La contraseña a asignar.
     */
    public void setContraseña(String contrasena) {
        this.contraseña = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public TipoEmpleado getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpleado tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "numEmpleado='" + numEmpleado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}