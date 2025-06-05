package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class AsistenteVuelo extends Empleado {
    private int numHorasAsistidas;
    private List<Idioma> idiomas;

    public AsistenteVuelo() {
        super();
        this.idiomas = new ArrayList<>();
    }

    public AsistenteVuelo(String numEmpleado, String nombre, String direccion, LocalDate fechaNac,
                          String genero, float sueldo, String nombreUsuario, String contraseña,
                          String correo, int numHorasAsistidas, List<Idioma> idiomas) {
        super(numEmpleado, nombre, direccion, fechaNac, genero, sueldo, nombreUsuario, contraseña, correo);
        this.numHorasAsistidas = numHorasAsistidas;
        this.idiomas = idiomas != null ? idiomas : new ArrayList<>();
    }

    @Override
    public String getTipoEmpleado() {
        return "ASISTENTE_VUELO";
    }

    // Getters y Setters
    public int getNumHorasAsistidas() { return numHorasAsistidas; }
    public void setNumHorasAsistidas(int numHorasAsistidas) { this.numHorasAsistidas = numHorasAsistidas; }

    public List<Idioma> getIdiomas() { return idiomas; }
    public void setIdiomas(List<Idioma> idiomas) { this.idiomas = idiomas; }

    public void agregarIdioma(Idioma idioma) {
        if (!this.idiomas.contains(idioma)) {
            this.idiomas.add(idioma);
        }
    }

    public void removerIdioma(Idioma idioma) {
        this.idiomas.remove(idioma);
    }

    public int getNumeroIdiomas() {
        return idiomas.size();
    }

    @Override
    public String toString() {
        return String.format("AsistenteVuelo{numEmpleado='%s', nombre='%s', horas=%d, idiomas=%d}",
                numEmpleado, nombre, numHorasAsistidas, idiomas.size());
    }
}