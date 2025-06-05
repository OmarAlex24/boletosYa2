package boletosyappae.models.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import boletosyappae.utils.adapters.LocalDateAdapter;
import boletosyappae.utils.adapters.LocalDateTimeAdapter;
import boletosyappae.utils.adapters.LocalTimeAdapter;
import boletosyappae.utils.adapters.EmpleadoTypeAdapter;
import boletosyappae.models.pojo.Empleado;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class GsonDAO<T> {
    protected static final String RUTA_ARCHIVOS = "data/";
    protected Gson gson;

    public GsonDAO() {
        // Crear directorio si no existe
        File directorio = new File(RUTA_ARCHIVOS);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Configurar Gson con adaptadores personalizados
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .registerTypeAdapter(Empleado.class, new EmpleadoTypeAdapter())
                .setPrettyPrinting()
                .create();
    }

    /**
     * Lee un archivo JSON y lo convierte a objeto/lista
     */
    protected List<T> leerArchivo(String nombreArchivo, Type tipo) {
        String rutaCompleta = RUTA_ARCHIVOS + nombreArchivo;
        File archivo = new File(rutaCompleta);

        if (!archivo.exists()) {
            return null;
        }

        try (FileReader reader = new FileReader(archivo)) {
            return gson.fromJson(reader, tipo);
        } catch (IOException e) {
            System.err.println("Error al leer archivo " + nombreArchivo + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Escribe un objeto/lista a archivo JSON
     */
    protected <T> boolean escribirArchivo(String nombreArchivo, T datos) {
        String rutaCompleta = RUTA_ARCHIVOS + nombreArchivo;

        try (FileWriter writer = new FileWriter(rutaCompleta)) {
            gson.toJson(datos, writer);
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir archivo " + nombreArchivo + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifica si un archivo existe
     */
    protected boolean archivoExiste(String nombreArchivo) {
        File archivo = new File(RUTA_ARCHIVOS + nombreArchivo);
        return archivo.exists();
    }

    /**
     * Crea un archivo vacío con una lista vacía
     */
    protected boolean crearArchivoVacio(String nombreArchivo) {
        return escribirArchivo(nombreArchivo, new ArrayList<>());
    }

    /**
     * Obtiene la ruta completa del archivo
     */
    protected String obtenerRutaCompleta(String nombreArchivo) {
        return RUTA_ARCHIVOS + nombreArchivo;
    }
}