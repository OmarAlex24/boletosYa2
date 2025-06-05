package boletosyappae.utils.adapters;

import com.google.gson.*;
import boletosyappae.models.pojo.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EmpleadoTypeAdapter implements JsonDeserializer<Empleado>, JsonSerializer<Empleado> {

    @Override
    public Empleado deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        String tipo = jsonObject.get("tipo").getAsString();

        // Datos comunes
        String numEmpleado = jsonObject.get("numEmpleado").getAsString();
        String nombre = jsonObject.get("nombre").getAsString();
        String direccion = jsonObject.get("direccion").getAsString();
        String genero = jsonObject.get("genero").getAsString();
        float sueldo = jsonObject.get("sueldo").getAsFloat();
        String correo = jsonObject.get("correo").getAsString();
        LocalDate fechaNac = LocalDate.parse(jsonObject.get("fechaNac").getAsString());
        String nombreUsuario = jsonObject.get("nombreUsuario").getAsString();
        String contraseña = jsonObject.get("contraseña").getAsString();

        switch (tipo) {
            case "ADMINISTRATIVO":
                TipoDepartamento departamento = TipoDepartamento.valueOf(
                        jsonObject.get("departamento").getAsString()
                );
                LocalTime horaEntrada = LocalTime.parse(jsonObject.get("horaEntrada").getAsString());
                LocalTime horaSalida = LocalTime.parse(jsonObject.get("horaSalida").getAsString());

                return new Administrativo(numEmpleado, nombre, direccion, fechaNac, genero,
                        sueldo, nombreUsuario, contraseña, correo,
                        departamento, horaEntrada, horaSalida);

            case "PILOTO":
                LicenciaPiloto licencia = LicenciaPiloto.valueOf(
                        jsonObject.get("licenciaPiloto").getAsString()
                );
                int aniosExperiencia = jsonObject.get("aniosExperiencia").getAsInt();
                int numTotalHoras = jsonObject.get("numTotalHoras").getAsInt();

                return new Piloto(numEmpleado, nombre, direccion, fechaNac, genero,
                        sueldo, nombreUsuario, contraseña, correo,
                        licencia, aniosExperiencia, numTotalHoras);

            case "ASISTENTE_VUELO":
                int numHorasAsistidas = jsonObject.get("numHorasAsistidas").getAsInt();
                JsonArray idiomasArray = jsonObject.getAsJsonArray("idiomas");
                List<Idioma> idiomas = new java.util.ArrayList<>();
                for (JsonElement elemento : idiomasArray) {
                    idiomas.add(Idioma.valueOf(elemento.getAsString()));
                }

                return new AsistenteVuelo(numEmpleado, nombre, direccion, fechaNac, genero,
                        sueldo, nombreUsuario, contraseña, correo,
                        numHorasAsistidas, idiomas);

            default:
                throw new JsonParseException("Tipo de empleado desconocido: " + tipo);
        }
    }

    @Override
    public JsonElement serialize(Empleado src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        // Propiedades comunes
        jsonObject.addProperty("numEmpleado", src.getNumEmpleado());
        jsonObject.addProperty("nombre", src.getNombre());
        jsonObject.addProperty("direccion", src.getDireccion());
        jsonObject.addProperty("genero", src.getGenero());
        jsonObject.addProperty("sueldo", src.getSueldo());
        jsonObject.addProperty("correo", src.getCorreo());
        jsonObject.addProperty("fechaNac", src.getFechaNac().toString());
        jsonObject.addProperty("nombreUsuario", src.getNombreUsuario());
        jsonObject.addProperty("contraseña", src.getContraseña());

        // Propiedades específicas según el tipo
        if (src instanceof Administrativo) {
            Administrativo admin = (Administrativo) src;
            jsonObject.addProperty("tipo", "ADMINISTRATIVO");
            jsonObject.addProperty("departamento", admin.getDepartamento().toString());
            jsonObject.addProperty("horaEntrada", admin.getHoraEntrada().toString());
            jsonObject.addProperty("horaSalida", admin.getHoraSalida().toString());

        } else if (src instanceof Piloto) {
            Piloto piloto = (Piloto) src;
            jsonObject.addProperty("tipo", "PILOTO");
            jsonObject.addProperty("licenciaPiloto", piloto.getLicenciaPiloto().toString());
            jsonObject.addProperty("aniosExperiencia", piloto.getAniosExperiencia());
            jsonObject.addProperty("numTotalHoras", piloto.getNumTotalHoras());

        } else if (src instanceof AsistenteVuelo) {
            AsistenteVuelo asistente = (AsistenteVuelo) src;
            jsonObject.addProperty("tipo", "ASISTENTE_VUELO");
            jsonObject.addProperty("numHorasAsistidas", asistente.getNumHorasAsistidas());

            JsonArray idiomasArray = new JsonArray();
            for (Idioma idioma : asistente.getIdiomas()) {
                idiomasArray.add(idioma.toString());
            }
            jsonObject.add("idiomas", idiomasArray);
        }

        return jsonObject;
    }
}