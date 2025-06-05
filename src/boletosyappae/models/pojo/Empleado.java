package boletosyappae.models.pojo;

import java.time.LocalDate;

public abstract class Empleado {
    protected String numEmpleado;
    protected String nombre;
    protected String direccion;
    protected LocalDate fechaNac;
    protected String genero;
    protected float sueldo;
    protected String nombreUsuario;
    protected String contraseña;
    protected String correo;

    public Empleado() {}

    public Empleado(String numEmpleado, String nombre, String direccion, LocalDate fechaNac,
                    String genero, float sueldo, String nombreUsuario, String contraseña, String correo) {
        this.numEmpleado = numEmpleado;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaNac = fechaNac;
        this.genero = genero;
        this.sueldo = sueldo;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.correo = correo;
    }

    // Getters y Setters
    public String getNumEmpleado() { return numEmpleado; }
    public void setNumEmpleado(String numEmpleado) { this.numEmpleado = numEmpleado; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public LocalDate getFechaNac() { return fechaNac; }
    public void setFechaNac(LocalDate fechaNac) { this.fechaNac = fechaNac; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public float getSueldo() { return sueldo; }
    public void setSueldo(float sueldo) { this.sueldo = sueldo; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public abstract String getTipoEmpleado();
}