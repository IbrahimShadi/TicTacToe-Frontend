package model;

public interface TicTacToeModelInterface {

    /**
     * Gets the number of rows in the board.
     *
     * @return the number of rows.
     */
    int getRows();
    byte[][] getBoard();

    /**
     * Gets the number of columns in the board.
     *
     * @return the number of columns.
     */
    int getColumns();

    /**
     * Gets the byte representing player one.
     *
     * @return the byte representing player one.
     */
    byte getPlayerOne();

    /**
     * Gets the byte representing player two.
     *
     * @return the byte representing player two.
     */
    byte getPlayerTwo();

    /**
     * Gets the byte representing an empty cell.
     *
     * @return the byte representing an empty cell.
     */
    byte getPlayerNone();

    /**
     * Gets the player at a specific board position.
     *
     * @param column the column index.
     * @param row the row index.
     * @return the player byte at the given position.
     */
    byte getAtPosition(byte column, byte row);

    /**
     * Sets a move at a given position and returns a new game state.
     *
     * @param column the column index.
     * @param row the row index.
     * @return a new TicTacToeModelImpl object with the updated move.
     */
    TicTacToeModelInterface setAtPosition(byte column, byte row);

    /**
     * Executes a move and returns a new game state.
     *
     * @param p the move represented as a Pair.
     * @return a new TicTacToeModelImpl reflecting the move.
     */
    TicTacToeModelInterface doMove(Pair p);

    /**
     * Returns the opposite player.
     *
     * @param player the player byte.
     * @return the byte representing the other player.
     */
    byte otherPlayer(byte player);

    boolean noMoreMove();

    /**
     * Checks if the game has ended either by a win or draw.
     *
     * @return true if the game has ended, false otherwise.
     */
    boolean ended();

    /**
     * Checks if the specified player has won the game.
     *
     * @param player the player byte to check for a win.
     * @return true if the player has won, false otherwise.
     */
    boolean wins(byte player);
}
