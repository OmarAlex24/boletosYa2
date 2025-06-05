package boletosyappae.models.pojo;

import java.time.LocalDate;

public abstract class Empleado {
    protected int id;
    protected String nombre;
    protected String direccion;
    protected LocalDate fechaNacimiento;
    protected String genero;
    protected double salario;
    protected String usuario;
    protected String contrasena;
    protected String tipoEmpleado;

    public Empleado(int id, String nombre, String direccion,
                    LocalDate fechaNacimiento, String genero, double salario,
                    String usuario, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.salario = salario;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getTipoEmpleado() { return tipoEmpleado; }
    public void setTipoEmpleado(String tipoEmpleado) { this.tipoEmpleado = tipoEmpleado; }
}