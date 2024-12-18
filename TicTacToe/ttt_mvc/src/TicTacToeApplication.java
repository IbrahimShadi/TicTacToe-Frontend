import controller.TicTacToeControllerImpl;
import model.TicTacToeModelImpl;

import javax.swing.*;

/**
 * Startet zwei unabhängige Clients, die synchronisiert werden.
 */
public class TicTacToeApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToeControllerImpl(new TicTacToeModelImpl(), 100, 100); // Fenster 1
        });

        SwingUtilities.invokeLater(() -> {
            new TicTacToeControllerImpl(new TicTacToeModelImpl(), 500, 100); // Fenster 2
        });
    }
}


