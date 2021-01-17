package Pieces;

import Others.Board;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Knight extends Piece {

    public Knight(int type, int xPos, int yPos) {
        super(type, xPos, yPos);
        name = "Knight";
        Image image;
        if (type == 1) {
            image = new Image("file:src/Images/White_Knight.png");
            imageView.setImage(image);
            imageView.fitHeightProperty();
            imageView.fitWidthProperty();
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } else {
            image = new Image("file:src/Images/Black_Knight.png");
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
        int x = 0;
        board.colorSquare(this.xPos, this.yPos, true);
        if (board.checkState && !this.isASavior)
            return;
        if (gameLogic.verticalProtection(board, this.xPos, this.yPos, this.type) || gameLogic.horizontalProtection(board, this.xPos, this.yPos, this.type) ||
                gameLogic.slashDiagonalProtection(board, this.xPos, this.yPos, this.type) || gameLogic.backslashDiagonalProtection(board, this.xPos, this.yPos, this.type))
            return;
        for (int y = -2; y <= 2; y++) {
            if (y != 0) {
                x = y % 2 == 0 ? 1 : 2;
                if (this.yPos + y >= 0 && this.yPos + y < board.getBoardHeight() && this.xPos - x >= 0 && this.xPos - x < board.getBoardWidth() && board.getBoardPosition(this.xPos - x, this.yPos + y) != this.type) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, this.xPos - x, this.yPos + y, this.type))
                            board.colorSquare(this.xPos - x, this.yPos + y, false);
                    } else
                        board.colorSquare(this.xPos - x, this.yPos + y, false);
                }
                if (this.yPos + y >= 0 && this.yPos + y < board.getBoardHeight() && this.xPos + x >= 0 && this.xPos + x < board.getBoardWidth() && board.getBoardPosition(this.xPos + x, this.yPos + y) != this.type) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, this.xPos + x, this.yPos + y, this.type))
                            board.colorSquare(this.xPos + x, this.yPos + y, false);
                    } else
                        board.colorSquare(this.xPos + x, this.yPos + y, false);
                }
            }
        }
    }
}
