public class ConnectFour {
    char[][] grid;
    char currentSymbol;

    public ConnectFour() {
        this.grid = new char[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                grid[i][j] = ' ';
            }
        }
        this.currentSymbol = 'X';
    }

    public boolean makeMove(int col) {
        if (col < 0 || col >= 7) {
            return false;
        }
        for (int i = 5; i >= 0; i--) {
            if (grid[i][col] == ' ') {
                grid[i][col] = currentSymbol;
                currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
                return true;
            }
        }
        return false;
    }

    public boolean isGameOver() {
        return checkWinner() || isGridFull();
    }

    public boolean isGridFull() {
        for (int j = 0; j < 7; j++) {
            if (grid[0][j] == ' ') {
                return false;
            }
        }
        return true;
    }

    public boolean checkWinner() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (grid[i][j] != ' ' && (
                    checkDirection(i, j, 1, 0) || // Horizontal
                    checkDirection(i, j, 0, 1) || // Vertical
                    checkDirection(i, j, 1, 1) || // Diagonal
                    checkDirection(i, j, 1, -1))) { // Diagonal
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDirection(int row, int col, int deltaRow, int deltaCol) {
        char symbol = grid[row][col];
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int newRow = row + i * deltaRow;
            int newCol = col + i * deltaCol;
            if (newRow < 0 || newRow >= 6 || newCol < 0 || newCol >= 7 || grid[newRow][newCol] != symbol) {
                return false;
            }
            count++;
        }
        return count == 4;
    }

    public void printGrid() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print("|" + grid[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
        System.out.println(" 0 1 2 3 4 5 6 ");
    }
}
