package boletosyappae.models.dao;

import boletosyappae.exceptions.DatosInvalidosException;
import boletosyappae.models.pojo.Empleado; // Usado por el EmpleadoTypeAdapter
import boletosyappae.utils.adapters.EmpleadoTypeAdapter;
import boletosyappae.utils.adapters.LocalDateAdapter;
import boletosyappae.utils.adapters.LocalDateTimeAdapter;
import boletosyappae.utils.adapters.LocalTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta base para DAOs que usan Gson para persistencia en JSON.
 * @param <T> El tipo de objeto que este DAO manejará.
 */
public abstract class GsonDAO<T> {
    protected String archivoJsonPath; // Ruta al archivo JSON
    protected Gson gson;
    protected Type tipoListaT; // Tipo específico de List<T> para Gson

    /**
     * Constructor para GsonDAO.
     * @param archivoJsonPath Ruta al archivo JSON donde se almacenarán los datos.
     * @param tipoListaT El tipo de la lista de objetos (e.g., new TypeToken<List<MiClase>>(){}.getType()).
     */
    public GsonDAO(String archivoJsonPath, Type tipoListaT) {
        this.archivoJsonPath = archivoJsonPath;
        this.tipoListaT = tipoListaT;
        // Configuración de Gson con adaptadores para tipos de Java Time y Empleado (polimorfismo)
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .registerTypeAdapter(Empleado.class, new EmpleadoTypeAdapter()) // Para manejar la jerarquía de Empleado
                .setPrettyPrinting() // Para que el JSON sea legible
                .create();
        crearArchivoSiNoExiste();
    }

    /**
     * Crea el archivo JSON con una lista vacía si no existe.
     * También crea los directorios necesarios.
     */
    private void crearArchivoSiNoExiste() {
        File file = new File(archivoJsonPath);
        if (!file.exists()) {
            try {
                // Crear directorios padres si no existen
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    parentDir.mkdirs();
                }
                // Escribir una lista JSON vacía en el archivo
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("[]");
                }
            } catch (IOException e) {
                // Considerar un manejo de error más robusto o logging
                System.err.println("Error crítico al crear el archivo JSON: " + archivoJsonPath + " - " + e.getMessage());
            }
        }
    }

    /**
     * Carga todos los objetos desde el archivo JSON.
     * @return Una lista de objetos T. Si el archivo está vacío o hay un error, retorna una lista vacía.
     * @throws DatosInvalidosException si ocurre un error al leer el archivo.
     */
    protected List<T> cargarDatos() throws DatosInvalidosException {
        try (Reader reader = Files.newBufferedReader(Paths.get(archivoJsonPath))) {
            List<T> datos = gson.fromJson(reader, tipoListaT);
            return datos != null ? datos : new ArrayList<>();
        } catch (FileNotFoundException e) {
            // Esto no debería ocurrir si crearArchivoSiNoExiste funciona bien.
             System.err.println("Archivo no encontrado al cargar: " + archivoJsonPath + ". Se creará uno vacío.");
             crearArchivoSiNoExiste();
             return new ArrayList<>();
        } catch (IOException e) {
            throw new DatosInvalidosException("Error al leer datos del archivo: " + archivoJsonPath + " - " + e.getMessage(), e);
        }
    }

    /**
     * Guarda la lista de objetos en el archivo JSON.
     * @param datos La lista de objetos T a guardar.
     * @throws DatosInvalidosException si ocurre un error al escribir en el archivo.
     */
    protected void guardarDatos(List<T> datos) throws DatosInvalidosException {
        try (Writer writer = Files.newBufferedWriter(Paths.get(archivoJsonPath))) {
            gson.toJson(datos, tipoListaT, writer);
        } catch (IOException e) {
            throw new DatosInvalidosException("Error al guardar datos en el archivo: " + archivoJsonPath + " - " + e.getMessage(), e);
        }
    }

    // Métodos abstractos que cada DAO concreto deberá implementar

    /**
     * Obtiene todos los objetos del tipo T.
     * @return Lista de objetos T.
     * @throws DatosInvalidosException si hay problemas al acceder a los datos.
     */
    public abstract List<T> obtenerTodos() throws DatosInvalidosException;

    // Para IDs que son int
    public abstract T obtenerPorId(int id) throws DatosInvalidosException;
    
    // Para IDs que son String (algunas entidades lo usan)
    public abstract T obtenerPorId(String id) throws DatosInvalidosException;


    /**
     * Guarda un nuevo objeto.
     * La asignación de ID debe manejarse dentro de la implementación de este método si es autogenerado.
     * @param objeto El objeto a guardar.
     * @throws DatosInvalidosException si hay problemas al guardar los datos.
     */
    public abstract void guardar(T objeto) throws DatosInvalidosException;

    /**
     * Actualiza un objeto existente.
     * @param objeto El objeto con los datos actualizados.
     * @throws DatosInvalidosException si hay problemas al actualizar los datos o si el objeto no se encuentra.
     */
    public abstract void actualizar(T objeto) throws DatosInvalidosException;

    // Para IDs que son int
    public abstract void eliminar(int id) throws DatosInvalidosException;

    // Para IDs que son String
    public abstract void eliminar(String id) throws DatosInvalidosException;
    
    /**
     * Obtiene el siguiente ID disponible para un nuevo objeto (si los IDs son numéricos).
     * @return El siguiente ID numérico.
     * @throws DatosInvalidosException si hay problemas al acceder a los datos.
     */
    public abstract int obtenerSiguienteIdNumerico() throws DatosInvalidosException;

}
