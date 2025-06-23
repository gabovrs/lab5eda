import java.util.HashMap;
import java.util.TreeMap;

public class Scoreboard {
    TreeMap<Integer, String> winTree;
    HashMap<String, Player> players;
    int playedGames;

    public Scoreboard() {
        this.winTree = new TreeMap<>();
        this.players = new HashMap<>();
        this.playedGames = 0;
    }

    public void addGameResult(String winnerPlayerName, String loserPlayerName, boolean draw) {
        Player winner = players.get(winnerPlayerName);
        Player loser = players.get(loserPlayerName);

        if (!draw) {
            winner.addWin();
            loser.addLoss();

            winTree.put(winner.wins, winnerPlayerName);
        } else {
            winner.addDraw();
            loser.addDraw();
        }
        playedGames++;
    }

    public void registerPlayer(String playerName) {
        if (!players.containsKey(playerName)) {
            players.put(playerName, new Player(playerName));
        }
    }

    public boolean checkPlayer(String playerName) {
        return players.containsKey(playerName);
    }

    public Player[] winRange(int lo, int hi) {
        Player[] result = new Player[hi - lo + 1];
        int index = 0;

        for (int i = hi; i >= lo; i--) {
            if (winTree.containsKey(i)) {
                String playerName = winTree.get(i);
                result[index++] = players.get(playerName);
            }
        }

        return result;
    }

    public Player[] winSuccessor(int wins) {
        Player[] result = new Player[players.size()];
        int index = 0;

        for (int i = wins + 1; i <= players.size(); i++) {
            if (winTree.containsKey(i)) {
                String playerName = winTree.get(i);
                result[index++] = players.get(playerName);
            }
        }

        // Resize the array to the actual number of players found
        Player[] trimmedResult = new Player[index];
        System.arraycopy(result, 0, trimmedResult, 0, index);
        return trimmedResult;
    }

    public void printScoreboard() {
        System.out.println("Scoreboard:");
        System.out.println("Player\tWins\tDraws\tLosses\tWin Rate");
        for (Player player : players.values()) {
            System.out.printf("%s\t%d\t%d\t%d\t%d%%\n", 
                player.playerName, 
                player.wins, 
                player.draws, 
                player.losses, 
                player.winRate());
        }
        System.out.println("Total games played: " + playedGames);
    }
}
