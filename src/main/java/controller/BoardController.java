package controller;

import de.jensd.fx.glyphs.emojione.EmojiOne;
import de.jensd.fx.glyphs.emojione.EmojiOneView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import de.jensd.fx.glyphs.weathericons.WeatherIcon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Board;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    @FXML
    GridPane paneBoard;

    @FXML
    Label lblPlayerOne, lblPlayerTwo, lblDieResult, lblDieOne, lblDieTwo, lblCurrentPlayer;

    @FXML
    Button btnShoot;

    @FXML
    MenuItem mnuNewGame;

    EmojiOneView players[];
    GridPaneCasilla casillas[];
    Board board;
    Die die1, die2;

    int currentPlayer;

    public void initialize(URL location, ResourceBundle resources) {
        board = new Board();
        players = new EmojiOneView[2];
        initData();
        initGUI();
    }

    private void initGUI() {
        paneBoard.add(players[0], 12, 7);
        paneBoard.add(players[1], 12, 7);

        btnShoot.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                shootAction();
            }
        });

        mnuNewGame.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                newGame();
            }
        });

        btnShoot.setStyle("-fx-background-color: rgba(252,245,20,0.5);");
    }

    private void initData() {

        casillas = new GridPaneCasilla[104];

        int row, col, pos = 0;
        GridPaneCasilla casilla;

        for (row = 7; row >= 0; row--)
            if (row % 2 != 0) {
                for (col = 12; col >= 0; col--) {
                    casilla = new GridPaneCasilla();
                    casilla.columna = col;
                    casilla.fila = row;
                    casillas[pos] = casilla;
                    pos++;
                }
            } else {
                for (col = 0; col < 13; col++) {
                    casilla = new GridPaneCasilla();
                    casilla.columna = col;
                    casilla.fila = row;
                    casillas[pos] = casilla;
                    pos++;
                }
            }

        players[0] = new EmojiOneView(EmojiOne.MAN_IN_TUXEDO);
        players[0].setSize("50");
        players[0].setFill(Color.YELLOW);

        players[1] = new EmojiOneView(EmojiOne.MAN_IN_TUXEDO);
        players[1].setSize("50");
        players[1].setFill(Color.RED);

        currentPlayer = 1;
        die1 = new Die();
        die2 = new Die();
    }

    private void changePlayer() {
        currentPlayer++;
        if (currentPlayer > 2)
            currentPlayer = 1;

        btnShoot.setStyle(null);
        if (currentPlayer == 1)
            btnShoot.setStyle("-fx-background-color: rgba(252,245,20,0.5);");
        else
            btnShoot.setStyle("-fx-background-color: rgba(255,65,65,0.5);");

    }

    private void shootAction() {
        int total, currentBox;
        die1.shoot();
        die2.shoot();

        total = die1.getResult() + die2.getResult();

        lblDieOne.setText(die1.getResult() + "");
        lblDieTwo.setText(die2.getResult() + "");
        lblDieResult.setText(total + "");

        board.move(total, currentPlayer);

        currentBox = board.getCurrentBox(currentPlayer);

        if (verifyWinner(currentPlayer, currentBox + total)) {
            GridPane.setRowIndex(players[currentPlayer - 1], 0);
            GridPane.setColumnIndex(players[currentPlayer - 1], 10);
            board.move(100-currentBox, currentPlayer);
            btnShoot.setDisable(true);
        } else {
            GridPaneCasilla casilla = parseBox(currentBox);

            GridPane.setRowIndex(players[currentPlayer - 1], casilla.fila);
            GridPane.setColumnIndex(players[currentPlayer - 1], casilla.columna);

        }

        if(currentPlayer == 1)
            lblPlayerOne.setText(board.getCurrentBox(currentPlayer) + "");
        else
            lblPlayerTwo.setText(board.getCurrentBox(currentPlayer) + "");

        changePlayer();

    }

    private boolean verifyWinner(int currentPlayer, int box) {
        boolean ganador = false;
        if (box >= 100) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("¡Felicidades!");
            alert.setContentText("Ha ganado el jugador: " + currentPlayer);
            alert.show();
            ganador = true;
        }

        return ganador;
    }

    private void newGame(){
        currentPlayer = 1;
        lblPlayerTwo.setText("");
        lblPlayerOne.setText("");
        lblDieResult.setText("");
        lblDieTwo.setText("");
        lblDieOne.setText("");
        lblCurrentPlayer.setText("1");
        btnShoot.setDisable(false);

        board.reset();

        paneBoard.getChildren().clear();
        paneBoard.add(players[0], 12, 7);
        paneBoard.add(players[1], 12, 7);

        btnShoot.setStyle("-fx-background-color: rgba(252,245,20,0.5);");

    }

    private GridPaneCasilla parseBox(int numCasilla) {
        if(numCasilla == 92)
            numCasilla+=2;

        return casillas[numCasilla - 1];
    }


    private class GridPaneCasilla {
        int fila;
        int columna;
    }
}
