package view;

import controller.TicTacToeControllerInterface;
import model.TicTacToeModelInterface;
import model.TicTacToeModelImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays; // Importiere Arrays


public class TicTacToeView extends JPanel {
    private TicTacToeModelInterface model;
    private TicTacToeControllerInterface controller;
    private GameClient client;
    private final int UNIT = 100; // Größe jeder Zelle in Pixeln


    public TicTacToeView(TicTacToeModelInterface model, TicTacToeControllerInterface controller, int x, int y) {
        this.model = model;
        this.controller = controller;
        this.client = new GameClient();
        createView(x, y);
        fetchBoard();
    }


    // Spielfeld vom Backend holen
    private void fetchBoard() {
        try {
            byte[][] board = client.getBoard(); // Board vom Backend holen
            for (byte r = 0; r < model.getRows(); r++) {
                for (byte c = 0; c < model.getColumns(); c++) {
                    model.getBoard()[c][r] = board[c][r]; // Backend-Werte ins Modell übertragen
                }
            }
            repaint(); // Neuzeichnen der Oberfläche
        } catch (IOException e) {
            e.printStackTrace();
        }
    }








    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGridLines(g);

        for (byte c = 0; c < model.getColumns(); c++) {
            for (byte r = 0; r < model.getRows(); r++) {
                byte playerAtPosition = model.getAtPosition(c, r);

                if (playerAtPosition == model.getPlayerOne()) {
                    g.setColor(Color.YELLOW);
                    g.fillOval(c * UNIT, r * UNIT, UNIT, UNIT);
                } else if (playerAtPosition == model.getPlayerTwo()) {
                    g.setColor(Color.BLUE);
                    g.fillOval(c * UNIT, r * UNIT, UNIT, UNIT);
                }
            }
        }
    }









    public void createView(int x, int y) {
        JFrame f = new JFrame("Tic Tac Toe");

        // Setze die bevorzugte Größe des Spielfelds
        this.setPreferredSize(new Dimension(3 * UNIT, 3 * UNIT));

        f.add(this);
        f.pack(); // Größe des Fensters an das Spielfeld anpassen
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Fenster an die gewünschte Position setzen
        f.setLocation(x, y);

        f.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                byte c = (byte) (ev.getPoint().getX() / UNIT);
                byte r = (byte) (ev.getPoint().getY() / UNIT);
                try {
                    // Spielzug an das Backend senden
                    client.makeMove(c, r);

                    // Aktualisiere das Board NACH dem Spielzug
                    fetchBoard();
                    repaint(); // Neu zeichnen
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



        f.setVisible(true);
    }




    // Zeichnet die Linien für das Spielfeld
    private void drawGridLines(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 1; i < 3; i++) { // Für ein 3x3-Gitter
            g.drawLine(i * UNIT, 0, i * UNIT, 3 * UNIT); // Vertikale Linien
            g.drawLine(0, i * UNIT, 3 * UNIT, i * UNIT); // Horizontale Linien
        }
    }

    // Wählt die Farbe basierend auf dem Spieler
    private Color selectColor(byte player) {
        if (player == model.getPlayerOne()) return Color.YELLOW; // Spieler 1: Gelb
        if (player == model.getPlayerTwo()) return Color.BLUE;   // Spieler 2: Blau
        return Color.WHITE; // Leer: Weiß
    }



    public void setGame(TicTacToeModelInterface model) {
        this.model = model;
        repaint();
    }
}
