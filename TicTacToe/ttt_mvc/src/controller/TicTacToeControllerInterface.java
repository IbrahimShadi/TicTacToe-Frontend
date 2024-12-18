package controller;

/**
 * This interface defines the contract for our controller.
 * It outlines the necessary methods required to handle user interactions.
 */
public interface TicTacToeControllerInterface {
    /**
     * Called when a mouse press event occurs in the game.
     * This method handles the user interaction to update the game state.
     * It checks if the game already ended or if the position is already occupied.
     * If not the position will be set.
     *
     * @param c the column index of the position where the mouse is pressed.
     * @param r the row index of the position where the mouse is pressed
     */
    public void whenMousePressed(byte c, byte r);
}
