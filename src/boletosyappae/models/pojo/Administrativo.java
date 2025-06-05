package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Administrativo extends Empleado {
    private TipoDepartamento departamento;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    public Administrativo() {
        super();
    }

    public Administrativo(String numEmpleado, String nombre, String direccion, LocalDate fechaNac,
                          String genero, float sueldo, String nombreUsuario, String contraseña,
                          String correo, TipoDepartamento departamento, LocalTime horaEntrada,
                          LocalTime horaSalida) {
        super(numEmpleado, nombre, direccion, fechaNac, genero, sueldo, nombreUsuario, contraseña, correo);
        this.departamento = departamento;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    @Override
    public String getTipoEmpleado() {
        return "ADMINISTRATIVO";
    }

    // Getters y Setters
    public TipoDepartamento getDepartamento() { return departamento; }
    public void setDepartamento(TipoDepartamento departamento) { this.departamento = departamento; }

    public LocalTime getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(LocalTime horaEntrada) { this.horaEntrada = horaEntrada; }

    public LocalTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalTime horaSalida) { this.horaSalida = horaSalida; }

    @Override
    public String toString() {
        return String.format("Administrativo{numEmpleado='%s', nombre='%s', departamento=%s}",
                numEmpleado, nombre, departamento);
    }
}