package model;

import java.util.Arrays;

/**
 * Implementation of the TicTacToeModelInterface representing the game logic.
 */
public class TicTacToeModelImpl implements TicTacToeModelInterface, Cloneable {

    // Constants for players and an empty cell
    //byte[][] b;
    private static final byte NONE = 0;
    private static final byte ONE = 1;
    private static final byte TWO = 2;

    private byte[][] board; // Game board
    private byte currentPlayer = ONE; // Current player
    private final byte rows = 3; // Number of rows
    private final byte columns = 3; // Number of columns
    private int movesDone = 0; // Number of moves made
    private Boolean winsLast = null; // Cached win state for the last move

    /** Default constructor
    public TicTacToeModelImpl() {
        initializeBoard();
    }
     */
    // In der TicTacToeModelImpl Klasse
    public TicTacToeModelImpl() {
        board = new byte[columns][rows]; // Initialisiere das Board als leer
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                board[i][j] = 0; // Jedes Feld auf "leer" setzen
            }
        }
        currentPlayer = 1; // Startspieler ist Spieler 1
    }


    // Constructor to initialize with an existing board
    public TicTacToeModelImpl(byte[][] board) {
        this.board = board;
    }

    // Initialize the board
    private void initializeBoard() {
        board = new byte[columns][rows];
        for (byte[] row : board) {
            Arrays.fill(row, NONE);
        }
    }

    @Override
    public byte getPlayerOne() {
        return ONE;
    }

    @Override
    public byte getPlayerTwo() {
        return TWO;
    }

    @Override
    public byte getPlayerNone() {
        return NONE;
    }

    @Override
    public byte getAtPosition(byte column, byte row) {
        return board[column][row];
    }



    @Override
    public TicTacToeModelInterface setAtPosition(byte column, byte row) {
        if (board[column][row] == 0) { // Nur wenn das Feld leer ist
            board[column][row] = currentPlayer; // Setze den aktuellen Spieler
            currentPlayer = otherPlayer(currentPlayer); // Spieler wechseln
        }
        return this;
    }





    @Override
    public TicTacToeModelInterface doMove(Pair move) {
        if (board[move.fst][move.snd] != NONE) {
            throw new IllegalStateException("Cell is already occupied!");
        }
        TicTacToeModelImpl newState = clone();
        newState.board[move.fst][move.snd] = currentPlayer;
        newState.currentPlayer = otherPlayer(currentPlayer);
        newState.movesDone++;
        newState.winsLast = null; // Reset win cache
        return newState;
    }

    @Override
    public byte otherPlayer(byte player) {
        return (player == ONE) ? TWO : ONE;
    }

    @Override
    public boolean noMoreMove() {
        return movesDone == rows * columns;
    }

    @Override
    public boolean ended() {
        return noMoreMove() || wins(getPlayerOne()) || wins(getPlayerTwo());
    }

    @Override
    public boolean wins(byte player) {
        return checkRows(player) || checkColumns(player) || checkDiagonal1(player) || checkDiagonal2(player);
    }

    private boolean checkRows(byte player) {
        for (byte[] row : board) {
            if (allEqual(row, player)) return true;
        }
        return false;
    }

    private boolean checkColumns(byte player) {
        for (int col = 0; col < columns; col++) {
            boolean columnWin = true;
            for (int row = 0; row < rows; row++) {
                if (board[col][row] != player) {
                    columnWin = false;
                    break;
                }
            }
            if (columnWin) return true;
        }
        return false;
    }

    private boolean checkDiagonal1(byte player) {
        for (int i = 0; i < rows; i++) {
            if (board[i][i] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal2(byte player) {
        for (int i = 0; i < rows; i++) {
            if (board[i][rows - i - 1] != player) {
                return false;
            }
        }
        return true;
    }

    private boolean allEqual(byte[] array, byte value) {
        for (byte b : array) {
            if (b != value) return false;
        }
        return true;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public TicTacToeModelImpl clone() {
        TicTacToeModelImpl copy = null;
        try {
            copy = (TicTacToeModelImpl) super.clone();
            copy.board = new byte[columns][rows];
            for (int i = 0; i < board.length; i++) {
                System.arraycopy(board[i], 0, copy.board[i], 0, board[i].length);
            }
            copy.winsLast = null; // Reset cached win state
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }

    @Override
    public byte[][] getBoard() {
        return board;
    }

}
