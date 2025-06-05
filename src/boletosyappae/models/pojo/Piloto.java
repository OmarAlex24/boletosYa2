package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.util.List;

public class Piloto extends Empleado {
    private List<LicenciaPiloto> licencias;
    private int horasVuelo;

    // Constructors
    public Piloto() {
        super();
    }

    public Piloto(int idEmpleado, String nombre, String apellidoPaterno,
                  String apellidoMaterno, String telefono, String email,
                  LocalDate fechaContratacion, double salario,
                  int idAerolinea, List<LicenciaPiloto> licencias, int horasVuelo) {
        super(idEmpleado, nombre, apellidoPaterno, apellidoMaterno, telefono,
                email, fechaContratacion, salario, TipoEmpleado.PILOTO, idAerolinea);
        this.licencias = licencias;
        this.horasVuelo = horasVuelo;
    }

    // Getters and Setters
    public List<LicenciaPiloto> getLicencias() { return licencias; }
    public void setLicencias(List<LicenciaPiloto> licencias) { this.licencias = licencias; }

    public int getHorasVuelo() { return horasVuelo; }
    public void setHorasVuelo(int horasVuelo) { this.horasVuelo = horasVuelo; }

    public boolean tieneLicenciaParaAvion(String tipoAvion) {
        return licencias != null && licencias.stream()
                .anyMatch(licencia -> licencia.getTipoAeronave().equalsIgnoreCase(tipoAvion));
    }

    @Override
    public String toString() {
        return "Piloto{" +
                "idEmpleado=" + idEmpleado +
                ", nombre='" + nombre + '\'' +
                ", horasVuelo=" + horasVuelo +
                ", licencias=" + (licencias != null ? licencias.size() : 0) +
                '}';
    }
}