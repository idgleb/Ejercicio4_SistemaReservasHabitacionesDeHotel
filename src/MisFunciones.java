import javax.swing.*;
import java.time.LocalDate;

public abstract class MisFunciones {

    public static boolean isNumeroDe_1_10000000(String str) {
        if (str.isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) < '0' || str.charAt(i) > '9') return false;
            }
            int mes = Integer.parseInt(str);
            if (mes < 1 || mes > 10000000) return false;
        }
        return true;
    }


    public static LocalDate[] agregarFechaArray(LocalDate fecha, LocalDate[] fechaArr) {
        LocalDate[] newArrObject = new LocalDate[fechaArr.length + 1];
        for (int i = 0; i < fechaArr.length; i++) {
            newArrObject[i] = fechaArr[i];
        }
        newArrObject[fechaArr.length] = fecha;
        fechaArr = newArrObject;
        return fechaArr;
    }

    public static LocalDate pedirFechaMasDeHoy(String msg, String msgError) {
        LocalDate fechaEntr;
        do {
            fechaEntr = LocalDate.parse(JOptionPane.showInputDialog(null, msg));
            if (fechaEntr.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(null, msgError);
            }
        } while (fechaEntr.isBefore(LocalDate.now()));
        return fechaEntr;
    }

    public static LocalDate pedirFechaMasDeOtraFecha(LocalDate otraFecha, String msg, String msgError) {
        LocalDate fecha;
        do {
            fecha = LocalDate.parse(JOptionPane.showInputDialog(null, msg));
            if (fecha.isBefore(otraFecha)) {
                JOptionPane.showMessageDialog(null, msgError);
            }
        } while (fecha.isBefore(otraFecha));
        return fecha;
    }

}
