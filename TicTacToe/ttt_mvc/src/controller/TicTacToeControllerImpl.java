package controller;

import model.TicTacToeModelInterface;
import view.TicTacToeView;

/**
 * This class is part of our controller.
 * It implements the right interface.
 * It serves as the controller in the MVC pattern.
 * Facilitating the interaction between view and model.
 */
public class TicTacToeControllerImpl implements TicTacToeControllerInterface {
    private TicTacToeView view;
    private TicTacToeModelInterface model;

    /**
     * Constructor, also initialize view and model.
     *
     * @param model game model with respect to MVC pattern.
     */
    public TicTacToeControllerImpl(TicTacToeModelInterface model, int x, int y) {
        this.model = model;
        this.view = new TicTacToeView(model, this, x, y);
    }

    /**
     * Called when a mouse press event occurs in the game.
     * This method handles the user interaction to update the game state.
     * It checks if the game already ended or if the position is already occupied.
     * If not the position will be set.
     *
     * @param c the column index of the position where the mouse is pressed.
     * @param r the row index of the position where the mouse is pressed
     */
    @Override
    public void whenMousePressed(byte c, byte r) {
        if (c >= model.getColumns() || r >= model.getRows() || model.ended() || model.getAtPosition(c, r) != model.getPlayerNone()) {
            return;
        }
        model = model.setAtPosition(c, r);
        view.setGame(model);
        view.repaint();
    }
}
