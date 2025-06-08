package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase para empleados Administrativos.
 */
public class Administrativo extends Empleado {
    private TipoDepartamento departamento;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;

    public Administrativo() {
        super();
        this.tipo = TipoEmpleado.ADMINISTRATIVO;
    }

    public Administrativo(String numEmpleado, String nombre, String direccion, LocalDate fechaNac, String genero,
                          double sueldo, String nombreUsuario, String contraseña, String correo,
                          TipoDepartamento departamento, LocalTime horaEntrada, LocalTime horaSalida) {
        super(numEmpleado, nombre, direccion, fechaNac, genero, sueldo, nombreUsuario, contraseña, correo, TipoEmpleado.ADMINISTRATIVO);
        this.departamento = departamento;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public TipoDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(TipoDepartamento departamento) {
        this.departamento = departamento;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    @Override
    public String toString() {
        return "Administrativo{" +
                "numEmpleado='" + getNumEmpleado() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", departamento=" + departamento +
                '}';
    }
}