package boletosyappae.models.dao;

import boletosyappae.models.pojo.Aerolinea;
import boletosyappae.exceptions.DatosInvalidosException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

public class AerolineaDAO extends GsonDAO {
    private static final String ARCHIVO_AEROLINEAS = "aerolineas.json";

    public AerolineaDAO() {
        super();
        if (!archivoExiste(ARCHIVO_AEROLINEAS)) {
            crearArchivoVacio(ARCHIVO_AEROLINEAS);
        }
    }

    public List<Aerolinea> obtenerTodasLasAerolineas() {
        Type tipoLista = new TypeToken<List<Aerolinea>>(){}.getType();
        List<Aerolinea> aerolineas = leerArchivo(ARCHIVO_AEROLINEAS, tipoLista);
        return aerolineas != null ? aerolineas : new ArrayList<>();
    }

    public Aerolinea obtenerAerolineaPorId(int idAerolinea) {
        List<Aerolinea> aerolineas = obtenerTodasLasAerolineas();
        return aerolineas.stream()
                .filter(a -> a.getId() == idAerolinea)
                .findFirst()
                .orElse(null);
    }

    public boolean guardarAerolinea(Aerolinea aerolinea) throws DatosInvalidosException {
        if (aerolinea == null) {
            throw new DatosInvalidosException("La aerolínea no puede ser nula");
        }

        validarDatosAerolinea(aerolinea);

        List<Aerolinea> aerolineas = obtenerTodasLasAerolineas();
        aerolineas.removeIf(a -> a.getId() == aerolinea.getId());
        aerolineas.add(aerolinea);

        return escribirArchivo(ARCHIVO_AEROLINEAS, aerolineas);
    }

    private void validarDatosAerolinea(Aerolinea aerolinea) throws DatosInvalidosException {
        if (aerolinea.getNombre() == null || aerolinea.getNombre().trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre de la aerolínea es requerido");
        }
    }
}