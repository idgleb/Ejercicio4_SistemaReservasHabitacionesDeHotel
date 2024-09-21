import javax.swing.*;
import java.util.Objects;

public class Habitacion {
    private int numeroHabitacion;
    private String tipoHabitacion;
    private double precioPorNoche;
    private Hotel hotel;

    private static Habitacion[] habitaciones = new Habitacion[0];


    public Habitacion(int numeroHabitacion, String tipoHabitacion, double precioPorNoche, Hotel hotel) {
        this.numeroHabitacion = numeroHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precioPorNoche = precioPorNoche;
        this.hotel = hotel;

        Habitacion[] newArrObject = new Habitacion[habitaciones.length + 1];
        for (int i = 0; i < habitaciones.length; i++) {
            newArrObject[i] = habitaciones[i];
        }
        newArrObject[habitaciones.length] = this;
        habitaciones = newArrObject;

    }

    public static void agregarHabitacion(Hotel hotel){

        String numeroHabitacionStr;
        do {
            numeroHabitacionStr = JOptionPane.showInputDialog(null, "numeroHabitacion? ");
            if (numeroHabitacionStr == null) numeroHabitacionStr = "";
        } while (!MisFunciones.isNumeroDe_1_10000000(numeroHabitacionStr));

        String tipoHabitacionStr;
        do {
            tipoHabitacionStr = JOptionPane.showInputDialog(null, "tipoHabitacion? ");
            if (tipoHabitacionStr == null) tipoHabitacionStr = "";
        } while (tipoHabitacionStr.isEmpty());

        String precioPorNocheStr;
        do {
            precioPorNocheStr = JOptionPane.showInputDialog(null, "precioPorNoche? ");
            if (precioPorNocheStr == null) precioPorNocheStr = "";
        } while (!MisFunciones.isNumeroDe_1_10000000(precioPorNocheStr));

        new Habitacion(Integer.parseInt(numeroHabitacionStr),tipoHabitacionStr,Integer.parseInt(precioPorNocheStr), hotel);
    }

    public static Habitacion[] getHabitaciones() {
        return habitaciones;
    }

    public static String[] getHabitacionesInfo() {
        String[] habitacionesInfo = new String[habitaciones.length];
        for (int i = 0; i < habitaciones.length; i++) {
            habitacionesInfo[i] = habitaciones[i].numeroHabitacion + " " + habitaciones[i].tipoHabitacion + " " + habitaciones[i].precioPorNoche;

        }
        return habitacionesInfo;
    }

    public static Habitacion eligirHabitacion() {
        String habitacionElig = (String) JOptionPane.showInputDialog(null,
                "Elege habitacion", "Lista de las habitasiones",
                JOptionPane.QUESTION_MESSAGE, null, getHabitacionesInfo(), null);
        int item = 0;
        for (String hab : getHabitacionesInfo()) {
            if (Objects.equals(hab, habitacionElig)) return getHabitaciones()[item];;
            item++;
        }
        return null;
    }

    public int getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }
}
