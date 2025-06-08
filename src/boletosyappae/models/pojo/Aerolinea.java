package boletosyappae.models.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Clase para representar una Aerolínea.
 */
public class Aerolinea {

    @SerializedName("idAerolinea")
    private int id;

    private String numeroIdentificacion;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("personaContacto")
    private String personaContacto;

    @SerializedName("telefono")
    private String numeroTelefonico;

    public Aerolinea() {
    }

    public Aerolinea(int id, String numeroIdentificacion, String nombre, String direccion,
                     String personaContacto, String numeroTelefonico) {
        this.id = id;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.direccion = direccion;
        this.personaContacto = personaContacto;
        this.numeroTelefonico = numeroTelefonico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    @Override
    public String toString() {
        return "Aerolinea{" + "id=" + id + ", nombre='" + nombre + '\'' + '}';
    }
}