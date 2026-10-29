import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/*
 * Productor de la caja en directo (proyecto A01.5).
 * Se adapta del Productor del ejemplo 8.4.
 *
 * Una caja registradora que emite tickets por su salida estándar, uno por línea, con el número
 * del ticket y su importe en céntimos ("número;céntimos"). Espera una pausa antes de cada
 * ticket. Anota en la salida de error la hora a la que escribe cada uno.
 *
 * Uso: java CashRegister <tickets> <pausa en ms>
 */

public class CashRegister {

    static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");  // hora con ms

    public static void main(String[] args) throws InterruptedException {
        int tickets = Integer.parseInt(args[0]);
        int pause = Integer.parseInt(args[1]);
        Random random = new Random();

        //  La pausa -> Escribir ticket [importe aleatorio]-> repetir hasta el num de tickets -> mensaje final

        for (int i = 1; i <= tickets; i++) {
            Thread.sleep(pause);
            int cents = random.nextInt(10000) + 1;
            System.out.println(i + ";" + cents);       // se bloquea si la tubería está llena

            System.err.println(LocalTime.now().format(TIME) + " Caja escribe ticket " + i);
        }

        System.err.println(LocalTime.now().format(TIME) + " Caja termina");
    }
}
