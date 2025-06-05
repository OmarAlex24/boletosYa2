package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.utils.adapters.LocalDateAdapter;
import boletosyappae.utils.adapters.LocalDateTimeAdapter;
import boletosyappae.utils.adapters.LocalTimeAdapter;
import boletosyappae.utils.adapters.EmpleadoTypeAdapter;
import boletosyappae.models.pojo.Empleado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class GsonDAO<T> {
    protected String archivo;
    protected Gson gson;
    protected Type tipoLista;

    public GsonDAO(String archivo, Type tipoLista) {
        this.archivo = archivo;
        this.tipoLista = tipoLista;
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .registerTypeAdapter(Empleado.class, new EmpleadoTypeAdapter())
                .setPrettyPrinting()
                .create();
        crearArchivoSiNoExiste();
    }

    private void crearArchivoSiNoExiste() {
        File file = new File(archivo);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[]");
            } catch (IOException e) {
                System.err.println("Error creando archivo: " + e.getMessage());
            }
        }
    }

    protected List<T> cargarDatos() throws DatosInvalidosException {
        try (FileReader reader = new FileReader(archivo)) {
            List<T> datos = gson.fromJson(reader, tipoLista);
            return datos != null ? datos : new ArrayList<>();
        } catch (IOException e) {
            throw new DatosInvalidosException("Error al cargar datos del archivo: " + archivo + " - " + e.getMessage());
        }
    }

    protected void guardarDatos(List<T> datos) throws DatosInvalidosException {
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(datos, writer);
        } catch (IOException e) {
            throw new DatosInvalidosException("Error al guardar datos en el archivo: " + archivo + " - " + e.getMessage());
        }
    }

    public abstract List<T> obtenerTodos() throws DatosInvalidosException;
    public abstract T obtenerPorId(int id) throws DatosInvalidosException;
    public abstract void guardar(T objeto) throws DatosInvalidosException;
    public abstract void actualizar(T objeto) throws DatosInvalidosException;
    public abstract void eliminar(int id) throws DatosInvalidosException;
    public abstract int obtenerSiguienteId() throws DatosInvalidosException;
}