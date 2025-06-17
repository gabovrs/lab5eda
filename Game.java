import java.util.Scanner;

public class Game {
    String status; // "IN_PROGRESS", "VICTORY", "DRAW"
    String winnerPlayerName;
    String playerNameA;
    String playerNameB;
    ConnectFour connectFour;

    public Game(String playerNameA, String playerNameB) {
        this.status = "IN_PROGRESS";
        this.winnerPlayerName = "";
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;
        this.connectFour = new ConnectFour();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        String currentPlayer = playerNameA; // El jugador A inicia
        while (!status.equals("VICTORY") && !status.equals("DRAW")) {
            // Imprimir el estado actual del tablero
            connectFour.printGrid();
            System.out.println("Es turno de " + currentPlayer + ". Ingresa la columna (0-6):");

            int col = scanner.nextInt();

            // Verificar si el movimiento es válido
            if (connectFour.makeMove(col)) {
                // Verificar si el juego ha terminado después del movimiento
                if (connectFour.isGameOver()) {
                    if (connectFour.checkWinner()) {
                        status = "VICTORY"; // Hay un ganador
                        winnerPlayerName = currentPlayer; // Guardamos el nombre del ganador
                    } else {
                        status = "DRAW"; // Empate
                    }
                }
                // Alternar el turno entre los jugadores
                currentPlayer = (currentPlayer.equals(playerNameA)) ? playerNameB : playerNameA;
            } else {
                System.out.println("Movimiento inválido. Intenta de nuevo.");
            }
        }

        // Imprimir el tablero final
        connectFour.printGrid();
        // Imprimir el resultado
        if (status.equals("VICTORY")) {
            System.out.println("¡Felicidades " + winnerPlayerName + ", ganaste!");
        } else {
            System.out.println("¡Empate! No hay más movimientos posibles.");
        }
        // Cerrar el escáner
        scanner.close();
    }
}
