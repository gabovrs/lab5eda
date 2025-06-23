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

    public String play() {
        Scanner scanner = new Scanner(System.in);
        String currentPlayer = playerNameA;
        while (!status.equals("VICTORY") && !status.equals("DRAW")) {
            connectFour.printGrid();
            System.out.println("Es turno de " + currentPlayer + ". Ingresa la columna (0-6):");

            int col = scanner.nextInt();

            if (connectFour.makeMove(col)) {
                if (connectFour.isGameOver()) {
                    if (connectFour.checkWinner()) {
                        status = "VICTORY";
                        winnerPlayerName = currentPlayer;
                    } else {
                        status = "DRAW";
                    }
                }
                currentPlayer = (currentPlayer.equals(playerNameA)) ? playerNameB : playerNameA;
            } else {
                System.out.println("Movimiento inválido. Intenta de nuevo.");
            }
        }

        connectFour.printGrid();
        if (status.equals("VICTORY")) {
            System.out.println("Felicidades " + winnerPlayerName + ", ganaste");
        } else {
            System.out.println("Empate. No hay más movimientos posibles.");
        }
        return winnerPlayerName;
    }
}
