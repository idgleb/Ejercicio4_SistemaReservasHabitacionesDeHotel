import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Cliente {
    private String nombre;
    private LocalDate[] fechaEntrada;
    private LocalDate[] fechaSalida;
    private Habitacion[] habitaciones;
    private static Cliente[] clientes = new Cliente[0];

    public Cliente(String nombre) {
        this.nombre = nombre;
        fechaEntrada = new LocalDate[0];
        fechaSalida = new LocalDate[0];
        habitaciones = new Habitacion[0];

        Cliente[] newArrObject = new Cliente[clientes.length + 1];
        for (int i = 0; i < clientes.length; i++) {
            newArrObject[i] = clientes[i];
        }
        newArrObject[clientes.length] = this;
        clientes = newArrObject;

    }

    public static String[] getClientesInfo() {

        String[] clientesInfo = new String[clientes.length];
        for (int i = 0; i < clientes.length; i++) {
            clientesInfo[i] = clientes[i].nombre + "\n";
            for (int j = 0; j < clientes[i].habitaciones.length; j++) {

                Period period = Period.between(clientes[i].fechaEntrada[j], clientes[i].fechaSalida[j]);
                int dias = period.getDays();
                double montoTotal = dias * clientes[i].habitaciones[j].getPrecioPorNoche();

                clientesInfo[i] += " (Habit: " + clientes[i].habitaciones[j].getNumeroHabitacion() +
                        " " + clientes[i].habitaciones[j].getTipoHabitacion() +
                " fecha entr: " + clientes[i].fechaEntrada[j] +
                " fecha salida: " + clientes[i].fechaSalida[j] + ") "+
                "(dias: " + dias + " Monto total: " + montoTotal + ")\n";
            }
        }
        return clientesInfo;
    }

    public static Cliente eligirCliente() {
        if (clientes.length == 0) {
            agregarCliente();
        }
        String clientElig = (String) JOptionPane.showInputDialog(null,
                "Elege Cliente", "Lista de las Clientes",
                JOptionPane.QUESTION_MESSAGE, null, getClientesInfo(), null);
        int item = 0;
        for (String client : getClientesInfo()) {
            if (Objects.equals(client, clientElig)) return getClientes()[item];
            item++;
        }
        return null;
    }

    public static void agregarCliente(){
        String nombreCliente;
        do {
            nombreCliente = JOptionPane.showInputDialog(null, "Nombre de Cliente? ");
            if (nombreCliente == null) nombreCliente = "";
        } while (nombreCliente.isEmpty());
        new Cliente(nombreCliente);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaEntrada(LocalDate fechaEntr) {

        fechaEntrada = MisFunciones.agregarFechaArray(fechaEntr, fechaEntrada);

    }


    public void setFechaSalida(LocalDate fechaSali) {

        fechaSalida = MisFunciones.agregarFechaArray(fechaSali, fechaSalida);

    }

    public void setHabitaciones(Habitacion habitacione) {

        Habitacion[] newArrObject = new Habitacion[habitaciones.length + 1];
        for (int i = 0; i < habitaciones.length; i++) {
            newArrObject[i] = habitaciones[i];
        }
        newArrObject[habitaciones.length] = habitacione;
        habitaciones = newArrObject;

    }


    public String getNombre() {
        return nombre;
    }

    public LocalDate[] getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate[] getFechaSalida() {
        return fechaSalida;
    }

    public Habitacion[] getHabitaciones() {
        return habitaciones;
    }

    public static Cliente[] getClientes() {
        return clientes;
    }

}
