package boletosyappae.models.pojo;

import java.util.ArrayList;
import java.util.List;

public class Aerolinea {
    private int id;
    private String numeroIdentificacion;
    private String nombre;
    private String direccion;
    private String personaContacto;
    private String numeroTelefonico;
    private List<Avion> flotilla;

    public Aerolinea(String numeroIdentificacion, String nombre, String direccion,
                     String personaContacto, String numeroTelefonico) {
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.direccion = direccion;
        this.personaContacto = personaContacto;
        this.numeroTelefonico = numeroTelefonico;
        this.flotilla = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters y setters
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) { this.numeroIdentificacion = numeroIdentificacion; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getPersonaContacto() { return personaContacto; }
    public void setPersonaContacto(String personaContacto) { this.personaContacto = personaContacto; }

    public String getNumeroTelefonico() { return numeroTelefonico; }
    public void setNumeroTelefonico(String numeroTelefonico) { this.numeroTelefonico = numeroTelefonico; }

    public List<Avion> getFlotilla() { return flotilla; }
    public void setFlotilla(List<Avion> flotilla) { this.flotilla = flotilla; }

    public void agregarAvion(Avion avion) { this.flotilla.add(avion); }
}