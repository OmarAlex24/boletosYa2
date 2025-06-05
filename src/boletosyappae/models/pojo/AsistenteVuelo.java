package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.util.List;

public class AsistenteVuelo extends Empleado {
    private List<Idioma> idiomas;
    private String certificacionSeguridad;

    // Constructors
    public AsistenteVuelo() {
        super();
    }

    public AsistenteVuelo(int idEmpleado, String nombre, String apellidoPaterno,
                          String apellidoMaterno, String telefono, String email,
                          LocalDate fechaContratacion, double salario,
                          int idAerolinea, List<Idioma> idiomas, String certificacionSeguridad) {
        super(idEmpleado, nombre, apellidoPaterno, apellidoMaterno, telefono,
                email, fechaContratacion, salario, TipoEmpleado.ASISTENTE_VUELO, idAerolinea);
        this.idiomas = idiomas;
        this.certificacionSeguridad = certificacionSeguridad;
    }

    // Getters and Setters
    public List<Idioma> getIdiomas() { return idiomas; }
    public void setIdiomas(List<Idioma> idiomas) { this.idiomas = idiomas; }

    public String getCertificacionSeguridad() { return certificacionSeguridad; }
    public void setCertificacionSeguridad(String certificacionSeguridad) { this.certificacionSeguridad = certificacionSeguridad; }

    public boolean hablaIdioma(String idioma) {
        return idiomas != null && idiomas.stream()
                .anyMatch(i -> i.getNombre().equalsIgnoreCase(idioma));
    }

    @Override
    public String toString() {
        return "AsistenteVuelo{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", idiomas=" + (idiomas != null ? idiomas.size() : 0) +
                ", certificacionSeguridad='" + certificacionSeguridad + '\'' +
                '}';
    }
}