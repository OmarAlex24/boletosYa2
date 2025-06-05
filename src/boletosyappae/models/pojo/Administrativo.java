package boletosyappae.models.pojo;

import java.time.LocalDate;

public class Administrativo extends Empleado {
    private TipoDepartamento departamento;
    private String usuario;
    private String contrasena;

    // Constructors
    public Administrativo() {
        super();
    }

    public Administrativo(int idEmpleado, String nombre, String apellidoPaterno,
                          String apellidoMaterno, String telefono, String email,
                          LocalDate fechaContratacion, double salario,
                          int idAerolinea, TipoDepartamento departamento,
                          String usuario, String contrasena) {
        super(idEmpleado, nombre, apellidoPaterno, apellidoMaterno, telefono,
                email, fechaContratacion, salario, TipoEmpleado.ADMINISTRATIVO, idAerolinea);
        this.departamento = departamento;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters and Setters
    public TipoDepartamento getDepartamento() { return departamento; }
    public void setDepartamento(TipoDepartamento departamento) { this.departamento = departamento; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    // Authentication method
    public boolean validarCredenciales(String usuario, String contrasena) {
        return this.usuario != null && this.contrasena != null &&
                this.usuario.equals(usuario) && this.contrasena.equals(contrasena);
    }

    @Override
    public String toString() {
        return "Administrativo{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", departamento=" + departamento +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}