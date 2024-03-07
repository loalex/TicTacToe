import java.util.Scanner;

public class TicTacToe {

    public static void startGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===========");
        System.out.println("Tic tac toe");
        System.out.println("=========== \n");
        int size;
        boolean isValid = false;
        do {
            System.out.print("Enter the number of rows/column (it must be an odd number!): ");
            size = sc.nextInt();
            if (size >= 3 && size % 2 == 1) {
                isValid = true;
            }
        } while (!isValid);
        displayHelp();
        String[][] board = generateBoard(size);
        displayBoard(board);
        int counter = 0;
        char pawn;
        do {
            if (counter % 2 == 0) {
                pawn = 'X';
            } else {
                pawn = 'O';
            }
            counter = nextMove(board, counter, pawn);
        } while (counter != size * size);
    }

    private static boolean gameValidation(String[][] board, int column, int row, String sym) {
        int len = board.length;
        boolean isWinner = true;
        for (int i = 0; i < len; i++) {
            if (!board[column][i].equals(sym)) {
                isWinner = false;
            }
        }
        if (isWinner) {
            return true;
        }
        isWinner = true;
        for (int i = 0; i < len; i++) {
            if (!board[i][row].equals(sym)) {
                isWinner = false;
            }
        }
        if (isWinner) {
            return true;
        }
        //check if there is a diagonal winner
        if (column == row) {
            isWinner = true;
            int c = 0, r = 0;
            while (c != len && r != len) {
                if (!board[c][r].equals(sym)) {
                    isWinner = false;
                    break;
                }
                c++;
                r++;
            }
            if (isWinner) {
                return true;
            }

        }
        //opposite diagonal winner
        int c = 0;
        for (int r = board.length - 1; r >= 0; r--) {
            if (!board[c][r].equals("X")) {
                return false;
            }
            c++;
        }
        return true;
    }

    private static int nextMove(String[][] board, int moves, char pawn) {
        System.out.print(pawn + " next move: ");
        Scanner sc = new Scanner(System.in);
        int field = sc.nextInt();
        int x = board.length;
        int cPos = 0;
        int rPos = 0;
        boolean isFound = false;
        for (int c = 0; c < x; c++) {
            for (int r = 0; r < x; r++) {
                if (board[c][r].equals(Integer.toString(field))) {
                    isFound = true;
                    board[c][r] = Character.toString(pawn);
                    moves++;
                    //Variables to winner validation
                    cPos = c;
                    rPos = r;
                }
            }
        }
        if (isFound == false) {
            System.out.println("Index is incorrect or has been already taken");
        }
        if (gameValidation(board, cPos, rPos, Character.toString(pawn))) {
            displayBoard(board);
            System.out.println("---------");
            System.out.println("| " + pawn + " won |");
            System.out.println("---------");
            return board.length * board.length;
        }
        displayBoard(board);
        return moves;
    }

    private static String[][] generateBoard(int x) {
        String[][] board = new String[x][x];
        int pos = 1;
        for (int c = 0; c < x; c++) {
            for (int r = 0; r < x; r++) {
                board[c][r] = Integer.toString(pos);
                pos++;
            }
        }
        return board;
    }

    private static void displayBoard(String[][] board) {
        int size = board.length;
        int i = 0;
        for (String[] row : board) {
            for (String cell : row) {
                if (i == size - 1) {
                    if (cell.length() == 1) {
                        System.out.print("  " + cell + "\n");
                    } else {
                        System.out.print(" " + cell + "\n");
                    }
                    break;
                }
                if (cell.length() == 1) {
                    System.out.print("  " + cell + " |");
                } else {
                    System.out.print(" " + cell + " |");
                }
                i++;
            }
            i = 0;
        }
    }

    private static void displayHelp() {
        System.out.println("Let's start the game:");
        System.out.println("There are 2 players, first player starts by claiming 1 field by typing its index, then second player choose the other one.");
        System.out.println("The game is going as long as there are free fields, or:");
        System.out.println("1. Player 1 or 2 claim all fields in one row");
        System.out.println("2. Player 1 or 2 claim all fields in one column");
        System.out.println("3. Player 1 or 2 claim all fields in one diagonal");
    }
}