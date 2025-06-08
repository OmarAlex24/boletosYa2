package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para empleados Asistentes de Vuelo.
 */
public class AsistenteVuelo extends Empleado {
    private int numHorasAsistidas;
    private List<Idioma> idiomas;

    public AsistenteVuelo() {
        super();
        this.idiomas = new ArrayList<>();
        this.tipo = TipoEmpleado.ASISTENTE_VUELO;
    }

    public AsistenteVuelo(String numEmpleado, String nombre, String direccion, LocalDate fechaNac, String genero,
                          double sueldo, String nombreUsuario, String contraseña, String correo,
                          int numHorasAsistidas, List<Idioma> idiomas) {
        super(numEmpleado, nombre, direccion, fechaNac, genero, sueldo, nombreUsuario, contraseña, correo, TipoEmpleado.ASISTENTE_VUELO);
        this.numHorasAsistidas = numHorasAsistidas;
        this.idiomas = (idiomas != null) ? idiomas : new ArrayList<>();
    }

    public int getNumHorasAsistidas() {
        return numHorasAsistidas;
    }

    public void setNumHorasAsistidas(int numHorasAsistidas) {
        this.numHorasAsistidas = numHorasAsistidas;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "AsistenteVuelo{" +
                "numEmpleado='" + getNumEmpleado() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", numHorasAsistidas=" + numHorasAsistidas +
                ", idiomas=" + (idiomas != null ? idiomas.size() : 0) +
                '}';
    }
}