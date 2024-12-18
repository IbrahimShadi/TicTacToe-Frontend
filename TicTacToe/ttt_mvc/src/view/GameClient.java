package view;

import java.io.*;
import java.net.*;

public class GameClient {
    private final String BASE_URL = "http://localhost:8080/api/game";

    // Spielfeld vom Backend abrufen
    public byte[][] getBoard() throws IOException {
        URL url = new URL(BASE_URL + "/board");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return parseBoard(response.toString());
    }

    // Spielzug senden
    public void makeMove(int x, int y) throws IOException {
        URL url = new URL(BASE_URL + "/move?x=" + x + "&y=" + y);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.getInputStream().close();
    }

    // Hilfsmethode zum Parsen des Spielfelds aus JSON
    private byte[][] parseBoard(String json) {
        json = json.replace("[", "").replace("]", "").replace("\"", ""); // Entferne Klammern und Anführungszeichen
        String[] rows = json.split(","); // Teile die Eingabe in Zeilen auf
        byte[][] board = new byte[3][3];

        for (int i = 0; i < rows.length; i++) {
            try {
                board[i / 3][i % 3] = Byte.parseByte(rows[i].trim()); // Konvertiere zu byte
            } catch (NumberFormatException e) {
                board[i / 3][i % 3] = 0; // Setze auf 0, wenn ungültige Werte auftreten
            }
        }
        return board;
    }

}
