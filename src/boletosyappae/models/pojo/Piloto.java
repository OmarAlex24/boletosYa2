package boletosyappae.models.pojo;

import java.time.LocalDate;

public class Piloto extends Empleado {
    private String tipoLicencia;
    private int anosExperiencia;
    private int horasVueloTotal;

    public Piloto(int id, String nombre, String direccion,
                  LocalDate fechaNacimiento, String genero, double salario,
                  String usuario, String contrasena, String tipoLicencia,
                  int anosExperiencia, int horasVueloTotal) {
        super(id, nombre, direccion, fechaNacimiento, genero, salario, usuario, contrasena);
        this.tipoLicencia = tipoLicencia;
        this.anosExperiencia = anosExperiencia;
        this.horasVueloTotal = horasVueloTotal;
        this.tipoEmpleado = "Piloto";
    }

    public String getTipoLicencia() { return tipoLicencia; }
    public void setTipoLicencia(String tipoLicencia) { this.tipoLicencia = tipoLicencia; }

    public int getAnosExperiencia() { return anosExperiencia; }
    public void setAnosExperiencia(int anosExperiencia) { this.anosExperiencia = anosExperiencia; }

    public int getHorasVueloTotal() { return horasVueloTotal; }
    public void setHorasVueloTotal(int horasVueloTotal) { this.horasVueloTotal = horasVueloTotal; }
}