//Aman Velpula, 251190328

public class Evaluate {

    private char[][] gameBoard;
    private int size;
    private int tilesToWin;
    private int maxLevels;

    public Evaluate(int size, int tilesToWin, int maxLevels) {
        this.size = size;
        this.tilesToWin = tilesToWin;
        this.maxLevels = maxLevels;
        gameBoard = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameBoard[i][j] = 'e';
            }
        }
    }

    public Dictionary createDictionary() {
        return new Dictionary(100);
    }

    public Record repeatedState(Dictionary dict) {
        String board = getBoardAsString();
        return dict.get(board);
    }

    public void insertState(Dictionary dict, int score, int level) {
        String key = getBoardAsString();
        Record record = new Record(key, score, level);
        dict.put(record);
    }

    public void storePlay(int row, int col, char symbol) {
        this.gameBoard[row][col] = symbol;
    }

    public boolean squareIsEmpty(int row, int col) {
        return this.gameBoard[row][col] == 'e';
    }

    public boolean tileOfComputer(int row, int col) {
        return this.gameBoard[row][col] == 'c';
    }


    public boolean tileOfHuman(int row, int col) {
        return this.gameBoard[row][col] == 'h';
    }

    private boolean checkRight(int row, int col, char symbol) {
        int start = col;
        int count = 0;

        while (start < this.gameBoard[row].length) {
            if (this.gameBoard[row][start] != symbol) {
                return false;
            }
            count++;
            if (count == this.tilesToWin) {
                return true;
            }
            start++;
        }
        return false;
    }

    private boolean checkLeft(int row, int col, char symbol) {
        int start = col;
        int count = 0;

        while (start >= 0) {
            if (this.gameBoard[row][start] != symbol) {
                return false;
            }
            count++;
            if (count == this.tilesToWin) {
                return true;
            }
            start--;
        }
        return false;
    }

    private boolean checkDown(int row, int col, char symbol) {
        int start = row;
        int count = 0;

        while (start < this.gameBoard.length) {
            if (this.gameBoard[start][col] != symbol) {
                return false;
            }
            count++;
            if (count == this.tilesToWin) {
                return true;
            }
            start++;
        }
        return false;
    }

    private boolean checkUp(int row, int col, char symbol) {
        int start = row;
        int count = 0;

        while (start >= 0) {
            if (this.gameBoard[start][col] != symbol) {
                return false;
            }
            count++;
            if (count == this.tilesToWin) {
                return true;
            }
            start--;
        }
        return false;
    }

    private boolean checkDiagonal(int row, int col, char symbol) {
        int startR = row;
        int startC = col;
        int count = 0;

        while (startR < this.gameBoard.length && startC >= 0) {
            if (this.gameBoard[startR][startC] != symbol) {
               break;
            }
            count++;
            if (count == this.tilesToWin) {
                return true;
            }
            startR++;
            startC--;
        }

        startR = row;
        startC = col;
        count = 0;

        while (startR < this.gameBoard.length && startC < this.gameBoard[row].length) {
            if (this.gameBoard[startR][startC] != symbol) {
                break;
            }
            count++;
            if (count == this.tilesToWin) {
                return true;
            }
            startR++;
            startC++;
        }

        startR = row;
        startC = col;
        count = 0;

        while (startR >= 0 && startC < this.gameBoard[row].length) {
            if (this.gameBoard[startR][startC] != symbol) {
                break;
            }
            count++;
            if (count == this.tilesToWin) {
                return true;
            }
            startR--;
            startC++;
        }

        startR = row;
        startC = col;
        count = 0;

        while (startR >= 0 && startC >= 0) {
            if (this.gameBoard[startR][startC] != symbol) {
                break;
            }
            count++;
            if (count == this.tilesToWin) {
                return true;
            }
            startR--;
            startC--;
        }
        return false;
    }



    public boolean wins(char symbol) {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                char current = this.gameBoard[i][j];
                if (current != symbol) {
                    continue;
                }
                if (checkRight(i, j, symbol)
                        || checkLeft(i, j, symbol)
                        || checkDown(i, j, symbol)
                        || checkUp(i, j, symbol)
                        || checkDiagonal(i, j, symbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isDraw() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.gameBoard[i][j] == 'e') {
                    return false;
                }
            }
        }
        return true;
    }

    public int evalBoard() {
        if (this.wins('c')) {
            return 3;
        } else if (this.wins('h')) {
            return 0;
        } else if (this.isDraw()) {
            return 2;
        } else {
            return 1;
        }
    }



    private String getBoardAsString() {
        String ret = "";
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                ret += this.gameBoard[j][i];
            }
        }
        return  ret;
    }

}
