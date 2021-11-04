package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.abs;

public class TicTacToe {
    private final int[][] board;
    private final int n;

    private final int[] rowSum, colSum;
    private int diagSum, revDiagSUm;
    private int winner;

    /**
     * @param n is the board size
     */
    public TicTacToe(final int n) {
        this.n = n;
        board = new int[n][n];
        rowSum = new int[n];
        colSum = new int[n];
    }

    public static void main(String[] args) {
        TicTacToe o = new TicTacToe(3);

        Scanner scanner = new Scanner(System.in);
        int player = 0;
        o.displayBoard();

        while (true) {
            player = o.changePlayer(player);
            System.out.println("Player - " + player);

            System.out.print("Row: ");
            int row = Integer.parseInt(scanner.nextLine());

            System.out.print("Col: ");
            int col = Integer.parseInt(scanner.nextLine());

            int moveState = o.move(player, row, col);
            o.displayBoard();

            if (moveState != 0 && moveState != -2) {
                System.out.println("Winner is Player: " + moveState);
                break;
            }
        }
    }

    /**
     * @param player is either 0 or 1
     * @param row    is the move's row index
     * @param col    is the move's column index
     * @return winner +1 if Player 1, -1 if Player 2 and 0 otherwise
     * @throws IllegalArgumentException if the move is an illegal move
     */
    public int move(int player, int row, int col) throws IllegalArgumentException {
        if (row < 0 || col < 0 || row >= n || col >= n)
            throw new IllegalArgumentException("Move out of bound!");
        if (board[row][col] != 0)
            throw new IllegalArgumentException("Cell is already occupied!");
        if (player != 0 && player != 1)
            throw new IllegalArgumentException("Invalid Player!");
        if (rowSum[row] == abs(n) || colSum[col] == abs(n) || diagSum == abs(n) || revDiagSUm == abs(n))
            return player;

        player = player == 0 ? -1 : 1;

        board[row][col] = player;
        rowSum[row] += player;
        colSum[col] += player;

        if (row == col)
            diagSum += player;
        if (row == n - 1 - col)
            revDiagSUm += player;

        if (rowSum[row] == abs(n) || colSum[col] == abs(n) || diagSum == abs(n) || revDiagSUm == abs(n))
            winner = player;

        return getWinner();
    }

    /**
     * @return winner of the ongoing game
     */
    public int getWinner() {
        return winner;
    }

    /**
     * @param n is the Current Player
     * @return player, alternates player value. If 1 then 0 and vice-e-versa
     */
    public int changePlayer(int n) {
        if (n == 1)
            return 0;
        return 1;
    }

    /**
     * Displays the Current State of the Board
     */
    public void displayBoard() {
        System.out.println("=========");
        for (int[] row : board)
            System.out.println(Arrays.toString(row));
        System.out.println("=========");
    }
}
