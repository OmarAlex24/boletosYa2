package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.util.List; // Aunque el PDF sugiere un tipo, el JSON y adapter usan uno. Se usa uno.

/**
 * Clase para empleados Pilotos.
 * Ajustada para coincidir con los requerimientos del PDF y la estructura del JSON.
 *
 * Requerimientos del PDF para Piloto (además de los de Empleado):
 * - Tipo de licencia que tienen para volar los aviones (mapeado a licenciaPiloto)
 * - Años de experiencia de vuelo (mapeado a aniosExperiencia)
 * - Número total de horas de vuelo (mapeado a numTotalHoras)
 */
public class Piloto extends Empleado {
    private LicenciaPiloto licenciaPiloto; // Tipo de licencia (según PDF y JSON)
    private int aniosExperiencia; // Años de experiencia (según PDF y JSON)
    private int numTotalHoras; // Número total de horas de vuelo (según PDF y JSON)

    // Constructores
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

    // Getters y Setters
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
