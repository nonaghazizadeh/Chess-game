package Pieces;

import Others.Board;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class King extends Piece {

    public King(int type, int xPos, int yPos) {
        super(type, xPos, yPos);
        name = "King";
        Image image;
        if (type == 1) {
            image = new Image("file:src/Images/White_King.png");
            imageView.setImage(image);
            imageView.fitHeightProperty();
            imageView.fitWidthProperty();
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } else {
            image = new Image("file:src/Images/Black_King.png");
            imageView.setImage(image);
            imageView.fitHeightProperty();
            imageView.fitWidthProperty();
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        }
    }

    @Override
    public ImageView getImage() {
        return (imageView);
    }

    @Override
    public void SelectPiece(Board board) {
        int x;
        int y;
        board.colorSquare(this.xPos, this.yPos, true);
        for (y = this.yPos - 1; y <= this.yPos + 1; y++) {
            for (x = this.xPos - 1; x <= this.xPos + 1; x++) {
                if (y >= 0 && y < board.getBoardHeight() && x >= 0 && x < board.getBoardWidth() && board.getBoardPosition(x, y) != this.type) {
                    if (!board.checkState)
                        this.canCastle(board);
                    if (!gameLogic.isCheck(board, x, y, this.type, true))
                        board.colorSquare(x, y, false);
                }
            }
        }
    }


    public int canCastle(Board board) {
        int canCastle = 0;
        if (type == 2 && this.isFirstTime && board.getBoardPosition(5, 0) == 0 && board.getBoardPosition(6, 0) == 0 && board.getPiece(7, 0) != null && board.getPiece(7, 0).isFirstTime) {
            canCastle = 1;
            board.colorSquare(7, 0, false);
        }
        if (type == 2 && this.isFirstTime && board.getBoardPosition(1, 0) == 0 && board.getBoardPosition(2, 0) == 0 && board.getBoardPosition(3, 0) == 0 && board.getPiece(0, 0) != null && board.getPiece(0, 0).isFirstTime) {
            canCastle = 2;
            board.colorSquare(0, 0, false);
        }
        if (type == 1 && this.isFirstTime && board.getBoardPosition(5, 7) == 0 && board.getBoardPosition(6, 7) == 0 && board.getPiece(7, 7) != null && board.getPiece(7, 7).isFirstTime) {
            canCastle = 3;
            board.colorSquare(7, 7, false);
        }
        if (type == 1 && this.isFirstTime && board.getBoardPosition(1, 7) == 0 && board.getBoardPosition(2, 7) == 0 && board.getBoardPosition(3, 7) == 0 && board.getPiece(0, 7) != null && board.getPiece(0, 7).isFirstTime) {
            canCastle = 4;
            board.colorSquare(0, 7, false);
        }
        return canCastle;
    }
}
