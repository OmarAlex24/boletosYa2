package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para empleados Asistentes de Vuelo.
 * Ajustada para coincidir con los requerimientos del PDF y la estructura del JSON.
 *
 * Requerimientos del PDF para Asistente de Vuelo (además de los de Empleado):
 * - Número de horas de asistencia de vuelo (mapeado a numHorasAsistidas)
 * - Número de idiomas que hablan (mapeado a idiomas)
 */
public class AsistenteVuelo extends Empleado {
    private int numHorasAsistidas; // Número de horas de asistencia (según PDF y JSON)
    private List<Idioma> idiomas; // Lista de idiomas que habla (según PDF y JSON)

    // Constructores
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

    // Getters y Setters
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

    /**
     * Verifica si el asistente habla un idioma específico.
     * @param idioma El idioma a verificar (como String, sensible a mayúsculas/minúsculas).
     * @return true si el asistente habla el idioma, false en caso contrario.
     */
    public boolean hablaIdioma(String idioma) {
        if (idiomas == null || idioma == null || idioma.trim().isEmpty()) {
            return false;
        }
        for (Idioma idiomaAsistente : idiomas) {
            // Comparar el nombre del enum con el string proporcionado.
            // Idioma.valueOf(idioma.toUpperCase()) podría usarse si se espera que 'idioma' sea un nombre de enum válido.
            if (idiomaAsistente.name().equalsIgnoreCase(idioma.trim())) {
                return true;
            }
        }
        return false;
    }
     /**
     * Verifica si el asistente habla un idioma específico (usando el enum).
     * @param idioma El enum Idioma a verificar.
     * @return true si el asistente habla el idioma, false en caso contrario.
     */
    public boolean hablaIdioma(Idioma idioma) {
        if (idiomas == null || idioma == null) {
            return false;
        }
        return idiomas.contains(idioma);
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
