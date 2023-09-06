import java.io.*;
import java.util.*;

public class Chatbot {
    private static final String ARCHIVO_BASE_DE_DATOS = "basededatos.txt";
    private static Map<String, String> baseDeDatos;
    private static Map<String, String> preguntasDesconocidas;

    public static void main(String[] args) {
        baseDeDatos = cargarBaseDeDatos();
        preguntasDesconocidas = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Chery-bot:¡Hola! Soy Chery-bot =)");
        System.out.println("          Puedes preguntarme cualquier cosa o escribir 'salir' para finalizar.");

        while (true) {
            System.out.print("Tú: ");
            String pregunta = scanner.nextLine().toLowerCase();

            if (pregunta.equals("salir")) {
                guardarBaseDeDatos();
                System.out.println("Chatbot: ¡Hasta luego!");
                break;
            }

            String respuesta = buscarRespuesta(pregunta);
            if (respuesta != null) {
                System.out.println("Chery-bot: " + respuesta);
            } else {
                System.out.println("Chery-bot: Lo siento, no tengo información sobre eso.");
                System.out.println("          Dime cuál sería la respuesta a esta pregunta: ");
                System.out.print("Tú: ");
                String nuevaRespuesta = scanner.nextLine();
                preguntasDesconocidas.put(pregunta, nuevaRespuesta);
                System.out.println("Chery-bot: Gracias por la nueva información.");
            }
        }
    }

    private static Map<String, String> cargarBaseDeDatos() {
        Map<String, String> baseDeDatos = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_BASE_DE_DATOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes.length == 2) {
                    baseDeDatos.put(partes[0].trim().toLowerCase(), partes[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la base de datos: " + e.getMessage());
        }
        return baseDeDatos;
    }

    private static String buscarRespuesta(String pregunta) {
        String respuesta = baseDeDatos.get(pregunta);
        if (respuesta == null) {
            respuesta = preguntasDesconocidas.get(pregunta);
        }
        return respuesta;
    }

    private static void guardarBaseDeDatos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_BASE_DE_DATOS))) {
            for (Map.Entry<String, String> entry : baseDeDatos.entrySet()) {
                bw.write(entry.getKey() + ": " + entry.getValue());
                bw.newLine();
            }
            for (Map.Entry<String, String> entry : preguntasDesconocidas.entrySet()) {
                bw.write(entry.getKey() + ": " + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar la base de datos: " + e.getMessage());
        }
    }
}
