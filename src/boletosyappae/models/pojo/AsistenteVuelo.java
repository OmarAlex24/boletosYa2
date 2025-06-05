package boletosyappae.models.pojo;

import java.time.LocalDate;

public class AsistenteVuelo extends Empleado {
    private int horasAsistencia;
    private int numeroIdiomas;

    public AsistenteVuelo(int id, String nombre, String direccion,
                          LocalDate fechaNacimiento, String genero, double salario,
                          String usuario, String contrasena, int horasAsistencia, int numeroIdiomas) {
        super(id, nombre, direccion, fechaNacimiento, genero, salario, usuario, contrasena);
        this.horasAsistencia = horasAsistencia;
        this.numeroIdiomas = numeroIdiomas;
        this.tipoEmpleado = "AsistenteVuelo";
    }

    public int getHorasAsistencia() { return horasAsistencia; }
    public void setHorasAsistencia(int horasAsistencia) { this.horasAsistencia = horasAsistencia; }

    public int getNumeroIdiomas() { return numeroIdiomas; }
    public void setNumeroIdiomas(int numeroIdiomas) { this.numeroIdiomas = numeroIdiomas; }
}