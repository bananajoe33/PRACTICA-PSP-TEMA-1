import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/*
 * Consumidor de la caja en directo (proyecto A01.5).
 * Se adapta del Consumidor del ejemplo 8.4.
 *
 * Un registro que lee tickets de su entrada estándar ("número;céntimos"), suma sus importes y
 * espera una pausa después de cada uno. Cuando la entrada se acaba, escribe en la salida
 * estándar el número de tickets y el total. Anota en la salida de error la hora a la que lee
 * cada ticket.
 *
 * Uso: java Ledger <pausa en ms>
 */

public class Ledger {

    static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");  // hora con ms

    public static void main(String[] args) throws IOException, InterruptedException {
        int pause = Integer.parseInt(args[0]);

        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        int tickets = 0;
        long cents = 0;
        String linea;
        while ((linea = entrada.readLine()) != null) {   // se bloquea si la tubería está vacía; null = la caja ha terminado
            tickets++;
            String[] partes = linea.split(";");          // "3;1250" -> partes[0] = "3", partes[1] = "1250"
            cents += Integer.parseInt(partes[1]);         // de texto a número
            System.err.println(LocalTime.now().format(TIME) + " Registro lee ticket " + partes[0]);
            Thread.sleep(pause);                          // simula el tiempo de apuntar el ticket
        }
        System.err.println(LocalTime.now().format(TIME) + " Registro termina");
        System.out.println(tickets + " tickets, total "
                + String.format(Locale.ROOT, "%.2f", cents / 100.0) + " euros");
    }
}
