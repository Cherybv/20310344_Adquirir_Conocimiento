import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ChatBot {

    private HashMap<String, String> respuestasPredefinidas;

    public ChatBot() {
        respuestasPredefinidas = new HashMap<>();
        respuestasPredefinidas.put("Hola", "¡Hola! ¿En qué puedo ayudarte?");
        respuestasPredefinidas.put("Como estas?", "Estoy bien, gracias por preguntar, y tú como estas?");
        respuestasPredefinidas.put("Muy bien gracias", "Que bueno me alegro mucho");
        respuestasPredefinidas.put("Estoy triste", "Platicame ¿qué pasa?");
        respuestasPredefinidas.put("Podemos hablar de algo mas?", "Claro que si, ¿Qué te gustaría saber?");

    }

    public void iniciarChat() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Chery-bot: ");
        System.out.println("¡Hola! Soy Chery-bot creado por Erik Benavides. ¿En qué puedo ayudarte? (Escribe 'Adiós' para salir)");
        while (true) {
            System.out.print("Tu: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("Adios")) {
                System.out.println("Adiós. Espero verte pronto.");
                break;
            }

            String respuesta = respuestasPredefinidas.get(input);
            System.out.print("Chery-bot: ");
            if (respuesta != null) {
                System.out.println(respuesta);
            } else {
                String nuevaPregunta = input;
                System.out.println("Lo siento, no entiendo esa pregunta.¿Cuál sería la respuesta apropiada a esa pregunta?");
                 System.out.print("Tu: ");
                String nuevaRespuesta = scanner.nextLine();
                respuestasPredefinidas.put(nuevaPregunta, nuevaRespuesta);
                System.out.println("Gracias por enseñarme.");
            }
        }

        scanner.close();
    }
    public static void main(String[] args) {
        ChatBot chatBot = new ChatBot();
        chatBot.iniciarChat();
    }
}
