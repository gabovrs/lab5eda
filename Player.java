public class Player {
    String playerName;
    int wins;
    int draws;
    int losses;

    public Player(String playerName) {
        this.playerName = playerName;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
    }

    public void addWin() {
        this.wins++;
    }

    public void addDraw() {
        this.draws++;
    }

    public void addLoss() {
        this.losses++;
    }

    public int winRate() {
        int totalGames = wins + draws + losses;
        return totalGames == 0 ? 0 : (wins * 100) / totalGames;
    }
}