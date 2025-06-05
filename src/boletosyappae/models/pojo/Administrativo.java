package boletosyappae.models.pojo;

import java.time.LocalDate;

public class Administrativo extends Empleado {
    private String departamento;
    private String horario; // 8 horas

    public Administrativo(int id, String nombre, String direccion,
                          LocalDate fechaNacimiento, String genero, double salario,
                          String usuario, String contrasena, String departamento, String horario) {
        super(id, nombre, direccion, fechaNacimiento, genero, salario, usuario, contrasena);
        this.departamento = departamento;
        this.horario = horario;
        this.tipoEmpleado = "Administrativo";
    }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }
}