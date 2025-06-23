import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scoreboard scoreboard = new Scoreboard();

        System.out.println("Bienvenido al juego Conecta 4");
        System.out.print("Ingresa el nombre del jugador A: ");
        String playerNameA = scanner.nextLine();
        System.out.print("Ingresa el nombre del jugador B: ");
        String playerNameB = scanner.nextLine();

        scoreboard.registerPlayer(playerNameA);
        scoreboard.registerPlayer(playerNameB);

        boolean playing = true;
        while (playing) {
            Game game = new Game(playerNameA, playerNameB);
            String winner = game.play();

            if (winner.isEmpty()) {
                System.out.println("No hubo ganador esta vez.");
                scoreboard.addGameResult(playerNameA, playerNameB, true);
            } else if (winner.equals(playerNameA)) {
                scoreboard.addGameResult(playerNameA, playerNameB, false);
            } else {
                scoreboard.addGameResult(playerNameB, playerNameA, false);
            }

            scoreboard.printScoreboard();

            System.out.print("¿Quieres jugar otra vez? (s/n): ");
            String response = scanner.nextLine();
            playing = response.equalsIgnoreCase("s");
        }

        System.out.println("Gracias por jugar Conecta 4.");
        scanner.close();
    }
}
