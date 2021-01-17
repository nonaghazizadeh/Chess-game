package Others;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class StatusBar extends HBox {
    private Button resetButton;
    public Label whitePlayerAlert;
    public Label blackPlayerAlert;
    public Label whitePlayerTimer;
    public Label blackPlayerTimer;
    public Label winner;

    public StatusBar() {
        GridPane statusBarGp = new GridPane();
        resetButton = new Button("Reset");
        whitePlayerAlert = new Label("");
        blackPlayerAlert = new Label("");
        whitePlayerTimer = new Label("");
        blackPlayerTimer = new Label("");
        winner = new Label("");

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(30);
        statusBarGp.getColumnConstraints().add(column);
        column = new ColumnConstraints();
        column.setPercentWidth(30);
        statusBarGp.getColumnConstraints().add(column);
        column = new ColumnConstraints();
        column.setPercentWidth(30);
        statusBarGp.getColumnConstraints().add(column);
        statusBarGp.setPrefSize(2000, 100);
        statusBarGp.getRowConstraints().add(new RowConstraints(70 / 2));
        statusBarGp.getRowConstraints().add(new RowConstraints(70 / 2));
        statusBarGp.addRow(0, whitePlayerAlert, resetButton, blackPlayerAlert);
        statusBarGp.addRow(1, whitePlayerTimer, winner, blackPlayerTimer);
        for (Node n : statusBarGp.getChildren()) {
            GridPane.setHalignment(n, HPos.CENTER);
            GridPane.setValignment(n, VPos.CENTER);
            if (n instanceof Label) {
                n.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-opacity: 1.0;");
            }
        }
        statusBarGp.setVgap(10);
        statusBarGp.setHgap(10);
        statusBarGp.setPadding(new Insets(10, 10, 10, 10));

        statusBarGp.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#d66d75 , #e29587)");
        statusBarGp.setSnapToPixel(false);
        getChildren().add(statusBarGp);
    }

    public void resize(double width, double height) {
        super.resize(width, height);
        setWidth(width);
        setHeight(height);
    }

    public Button getResetButton() {
        return resetButton;
    }

    public static class Timer {
        public int whiteTimer = 60;
        public int blackTimer = 60;
        public int playerTurn = 0;
        public boolean timeIsOver = false;
        private Board chessboard;

        public Timer(Board board) {
            chessboard = board;
        }

        public Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (playerTurn == 1 && !timeIsOver && !chessboard.checkmate && !chessboard.stalemate) {
                    whiteTimer -= 1;
                    chessboard.getStatusBar().whitePlayerTimer.setText("White player timer: " + TimeUnit.SECONDS.toMinutes(whiteTimer) + ":" + (whiteTimer % 60));
                } else if (playerTurn == 2 && !timeIsOver) {
                    blackTimer -= 1;
                    chessboard.getStatusBar().blackPlayerTimer.setText("Black player timer: " + TimeUnit.SECONDS.toMinutes(blackTimer) + ":" + (blackTimer % 60));
                }
                if (!timeIsOver && (whiteTimer == 0 || blackTimer == 0)) {
                    chessboard.timerOver(playerTurn);
                    timeIsOver = true;
                }
            }
        }));
    }
}