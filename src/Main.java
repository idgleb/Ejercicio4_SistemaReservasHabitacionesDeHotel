import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Hotel hotel = new Hotel("Baba");

        Habitacion habitacion = new Habitacion(1, "Simple", 100, hotel);
        Habitacion habitacio3 = new Habitacion(4, "Doble", 200, hotel);

        int seleccion = 0;
        do {
            String[] opciones = {"Agregar Cliente", "Reservar habitacion", "Agregar NEW Habitacion", "Salir"};
            seleccion = JOptionPane.showOptionDialog(
                    null,
                    hotel.getInfoHotel(),
                    "Ursol",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );
            switch (seleccion) {
                case 0:
                    //Agregar cliente
                    Cliente.agregarCliente();
                    break;
                case 1:
                    //Reservar habitacion
                    hotel.reservarHabitacion();
                    break;
                case 2:
                    //Agregar NEW Habitacion
                    Habitacion.agregarHabitacion(hotel);
                    break;
                case 3:
                    //?????????

                    break;
                case 4:
                    //Salir
                    break;
                default:
                    break;
            }
        } while (seleccion != 3);



    }
}