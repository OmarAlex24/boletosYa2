package boletosyappae.models.pojo;

import java.time.LocalDate;

/**
 * Clase para empleados Pilotos.
 */
public class Piloto extends Empleado {
    private LicenciaPiloto licenciaPiloto;
    private int aniosExperiencia;
    private int numTotalHoras;

    public Piloto() {
        super();
        this.tipo = TipoEmpleado.PILOTO;
    }

    public Piloto(String numEmpleado, String nombre, String direccion, LocalDate fechaNac, String genero,
                  double sueldo, String nombreUsuario, String contraseña, String correo,
                  LicenciaPiloto licenciaPiloto, int aniosExperiencia, int numTotalHoras) {
        super(numEmpleado, nombre, direccion, fechaNac, genero, sueldo, nombreUsuario, contraseña, correo, TipoEmpleado.PILOTO);
        this.licenciaPiloto = licenciaPiloto;
        this.aniosExperiencia = aniosExperiencia;
        this.numTotalHoras = numTotalHoras;
    }

    public LicenciaPiloto getLicenciaPiloto() {
        return licenciaPiloto;
    }

    public void setLicenciaPiloto(LicenciaPiloto licenciaPiloto) {
        this.licenciaPiloto = licenciaPiloto;
    }

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public int getNumTotalHoras() {
        return numTotalHoras;
    }

    public void setNumTotalHoras(int numTotalHoras) {
        this.numTotalHoras = numTotalHoras;
    }

    @Override
    public String toString() {
        return "Piloto{" +
                "numEmpleado='" + getNumEmpleado() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", licencia=" + licenciaPiloto +
                ", aniosExperiencia=" + aniosExperiencia +
                ", numTotalHoras=" + numTotalHoras +
                '}';
    }
}