import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Hotel {
    private String nombre;

    public Hotel(String nombre) {
        this.nombre = nombre;
    }

    public String getInfoHotel() {
        String infoHotel = "Hotel: " + nombre + "\n";
        infoHotel += "__________ \n" ;
        infoHotel += "Habitaciones: \n" ;
        for (String hab: Habitacion.getHabitacionesInfo()) {
            infoHotel += hab + "\n";
        }
        infoHotel += "__________ \n" ;
        infoHotel += "Clientes: \n" ;
        for (String cli: Cliente.getClientesInfo()) {
            infoHotel += cli + "\n";
        }
        return infoHotel;

    }


    public boolean reservarHabitacion() {

        Habitacion habitacionEligido = Habitacion.eligirHabitacion();
        if (habitacionEligido == null) return false;
        Cliente clienteEligido = Cliente.eligirCliente();
        if (clienteEligido == null) return false;

        LocalDate fechaEntrada;
        LocalDate fechaSalida;

        boolean disponible;
        do {
            fechaEntrada = MisFunciones.pedirFechaMasDeHoy("Fecha Entrada?", "Fecha Entrada no puede ser de pasado");
            fechaSalida = MisFunciones.pedirFechaMasDeOtraFecha(fechaEntrada, "Fecha Salida?", "Fecha Salida debe ser despues de Entrada");
            disponible = isDiponibleHabitacion(habitacionEligido, fechaEntrada, fechaSalida);
        } while (!disponible);

        clienteEligido.setFechaEntrada(fechaEntrada);
        clienteEligido.setFechaSalida(fechaSalida);
        clienteEligido.setHabitaciones(habitacionEligido);

        JOptionPane.showMessageDialog(null, "Habitacion Reservada!");
        return true;
    }


    public boolean isDiponibleHabitacion(Habitacion habitacion, LocalDate fechaEntrada, LocalDate fechaSalida) {

        for (int i = 0; i < Cliente.getClientes().length; i++) {
            Cliente cliente = Cliente.getClientes()[i];
            LocalDate[] fechasEntradas = cliente.getFechaEntrada();
            LocalDate[] fechasSalidas = cliente.getFechaSalida();
            for (int j = 0; j < cliente.getHabitaciones().length; j++) {
                Habitacion hab = cliente.getHabitaciones()[j];
                if (habitacion == hab) {
                    long dias = ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
                    for (long d = 0; d < dias; d++) {
                        if (
                                (fechaEntrada.plusDays(d).isEqual(fechasEntradas[j]) || fechaEntrada.plusDays(d).isAfter(fechasEntradas[j])) &&
                                        (fechaEntrada.plusDays(d).isEqual(fechasSalidas[j]) || fechaEntrada.plusDays(d).isBefore(fechasSalidas[j]))
                        ) {
                            JOptionPane.showMessageDialog(null, "Habitacion NO disponible, elege otras dias!");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


}
