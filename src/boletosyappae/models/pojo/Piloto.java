package boletosyappae.models.pojo;

import java.time.LocalDate;

public class Piloto extends Empleado {
    private LicenciaPiloto licenciaPiloto;
    private int aniosExperiencia;
    private int numTotalHoras;

    public Piloto() {
        super();
    }

    public Piloto(String numEmpleado, String nombre, String direccion, LocalDate fechaNac,
                  String genero, float sueldo, String nombreUsuario, String contraseña,
                  String correo, LicenciaPiloto licenciaPiloto, int aniosExperiencia,
                  int numTotalHoras) {
        super(numEmpleado, nombre, direccion, fechaNac, genero, sueldo, nombreUsuario, contraseña, correo);
        this.licenciaPiloto = licenciaPiloto;
        this.aniosExperiencia = aniosExperiencia;
        this.numTotalHoras = numTotalHoras;
    }

    @Override
    public String getTipoEmpleado() {
        return "PILOTO";
    }

    // Getters y Setters
    public LicenciaPiloto getLicenciaPiloto() { return licenciaPiloto; }
    public void setLicenciaPiloto(LicenciaPiloto licenciaPiloto) { this.licenciaPiloto = licenciaPiloto; }

    public int getAniosExperiencia() { return aniosExperiencia; }
    public void setAniosExperiencia(int aniosExperiencia) { this.aniosExperiencia = aniosExperiencia; }

    public int getNumTotalHoras() { return numTotalHoras; }
    public void setNumTotalHoras(int numTotalHoras) { this.numTotalHoras = numTotalHoras; }

    @Override
    public String toString() {
        return String.format("Piloto{numEmpleado='%s', nombre='%s', licencia=%s, experiencia=%d años}",
                numEmpleado, nombre, licenciaPiloto, aniosExperiencia);
    }
}