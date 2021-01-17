package Others;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Control extends javafx.scene.control.Control {
    private Board board;
    private StatusBar statusBar;
    private final int statusBarSize = 100;

    public Control() {
        setSkin(new CustomControlSkin(this));
        statusBar = new StatusBar();
        board = new Board(statusBar);
        getChildren().addAll(statusBar, board);

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                board.selectPiece(event.getX(), event.getY() - (statusBarSize / 2));
            }
        });

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.SPACE)
                    board.resetGame();
            }
        });

        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.SPACE) {

                }
            }
        });

        statusBar.getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                board.resetGame();
            }
        });
    }


    public void resize(double width, double height) {
        super.resize(width, height - statusBarSize);
        board.setTranslateY(statusBarSize / 2);
        board.resize(width, height - statusBarSize);
        statusBar.resize(width, statusBarSize);
        statusBar.setTranslateY(-(statusBarSize / 2));
    }

    static class CustomControlSkin extends SkinBase<Control> implements Skin<Control> {
        public CustomControlSkin(Control cc) {
            super(cc);
        }
    }
}
