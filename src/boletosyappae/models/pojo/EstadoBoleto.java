package boletosyappae.models.pojo;

public enum EstadoBoleto {
    ACTIVO("Activo"),
    CANCELADO("Cancelado"),
    USADO("Usado");

    private final String descripcion;

    EstadoBoleto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}