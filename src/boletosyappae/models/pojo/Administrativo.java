package boletosyappae.models.pojo;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase para empleados Administrativos.
 * Ajustada para coincidir con los requerimientos del PDF y la estructura del JSON.
 *
 * Requerimientos del PDF para Administrativo (además de los de Empleado):
 * - Departamento de trabajo (mapeado a departamento)
 * - Horario de 8 horas (mapeado a horaEntrada y horaSalida)
 */
public class Administrativo extends Empleado {
    private TipoDepartamento departamento; // Departamento de trabajo (según PDF y JSON)
    private LocalTime horaEntrada; // Hora de entrada (para cumplir "horario de 8 horas", según JSON)
    private LocalTime horaSalida;  // Hora de salida (para cumplir "horario de 8 horas", según JSON)

    // Constructores
    public Administrativo() {
        super();
        this.tipo = TipoEmpleado.ADMINISTRATIVO;
    }

    public Administrativo(String numEmpleado, String nombre, String direccion, LocalDate fechaNac, String genero,
                          double sueldo, String nombreUsuario, String contraseña, String correo,
                          TipoDepartamento departamento, LocalTime horaEntrada, LocalTime horaSalida) {
        super(numEmpleado, nombre, direccion, fechaNac, genero, sueldo, nombreUsuario, contraseña, correo, TipoEmpleado.ADMINISTRATIVO);
        this.departamento = departamento;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    // Getters y Setters
    public TipoDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(TipoDepartamento departamento) {
        this.departamento = departamento;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * Valida las credenciales. Este método podría estar en EmpleadoDAO o un servicio de autenticación.
     * Si se mantiene aquí, debe usar los campos de la clase base.
     * @param usuario El nombre de usuario a validar.
     * @param contrasena La contraseña a validar (debería ser comparada con la contraseña hasheada).
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean validarCredenciales(String usuario, String contrasena) {
        // La comparación de contraseña debe ser contra la versión hasheada.
        // Aquí se asume que this.contraseña ya está (o debería estar) hasheada.
        // Y la 'contrasena' entrante también debería ser hasheada antes de comparar.
        // O, si this.contraseña está en texto plano (NO RECOMENDADO), entonces se compara directo.
        return this.nombreUsuario != null && this.contraseña != null &&
               this.nombreUsuario.equals(usuario) && this.contraseña.equals(contrasena);
    }


    @Override
    public String toString() {
        return "Administrativo{" +
                "numEmpleado='" + getNumEmpleado() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", departamento=" + departamento +
                '}';
    }
}
