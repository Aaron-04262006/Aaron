import java.util.Scanner;

public class test1 {
    public static char[] board = new char[9];
    public static char currentPlayer = 'X';
    public static int[] scores = {0, 0};
    public static String[] players = new String[2];

    public static void main(String[] args) { 
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to TicTacToe Game");

        System.out.print("Enter Player 1 Name (X): ");
        players[0] = input.nextLine();
        System.out.print("Enter Player 2 Name (O): ");
        players[1] = input.nextLine();

        while (true) {
            resetBoard();
            currentPlayer = 'X';

            while (true) {
                printBoard();
                displayScores();
                
                String currentPlayerName = (currentPlayer == 'X') ? players[0] : players[1];
                System.out.println(currentPlayerName + "'s Turn (" + currentPlayer + ")");
                System.out.print("Enter a position (1-9): ");

                int position = getValidPosition(input);
                board[position] = currentPlayer;

                if (checkWin()) {
                    printBoard();
                    System.out.println("Congratulations! " + currentPlayerName + " (" + currentPlayer + ") wins!");
                    if (currentPlayer == 'X') {
                        scores[0]++;
                    } else {
                        scores[1]++;
                    }
                    break;
                }

                if (checkDraw()) {
                    printBoard();
                    System.out.println("It's a DRAW!");
                    break;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }

            if (!askPlayAgain(input)) {
                System.out.println("Thanks for playing! Here is the final score:");
                System.out.println(players[0] + " won(" + scores[0] + ")");
                System.out.println(players[1] + " won(" + scores[1] + ")");
                input.close();
                return;
            }
        }
    }

    public static void resetBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    public static boolean checkWin() {
        int[][] winPatterns = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, 
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
            {0, 4, 8}, {2, 4, 6}             
        };

        for (int[] pattern : winPatterns) {
            if (board[pattern[0]] == currentPlayer &&
                board[pattern[1]] == currentPlayer &&
                board[pattern[2]] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDraw() {
        for (char cell : board) {
            if (cell == ' ') {
                return false;
            }
        }
        return true;
    }

    public static void printBoard() {
        System.out.println("\n " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---|---|---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---|---|---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + "\n");
    }

    public static void displayScores() {
        System.out.println("Current Scores:");
        System.out.println(players[0] + " (X): " + scores[0] + " wins");
        System.out.println(players[1] + " (O): " + scores[1] + " wins\n");
    }

    public static int getValidPosition(Scanner input) {
        int position = -1;
        while (true) {
            try {
                position = input.nextInt() - 1;
                if (position < 0 || position >= 9) {
                    System.out.println("Invalid position. Please enter a number between 1 and 9.");
                } else if (board[position] != ' ') {
                    System.out.println("This position is already taken. Try again.");
                } else {
                    return position;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                input.next(); 
            }
        }
    }

    public static boolean askPlayAgain(Scanner input) {
        while (true) {
            System.out.print("Do you want to play again? (Y/N): ");
            String playAgain = input.next().toUpperCase();
            if (playAgain.equals("Y")) {
                return true;
            } else if (playAgain.equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Y' for Yes or 'N' for No.");
            }
        }
    }
}