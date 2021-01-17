package Pieces;

import Others.Board;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bishop extends Piece {

    public Bishop(int type, int xPos, int yPos) {
        super(type, xPos, yPos);
        name = "Bishop";
        Image image;
        if (type == 1) {
            image = new Image("file:src/Images/White_Bishop.png");
            imageView.setImage(image);
            imageView.fitHeightProperty();
            imageView.fitWidthProperty();
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } else {
            image = new Image("file:src/Images/Black_Bishop.png");
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
        int y = this.yPos + 1;
        board.colorSquare(this.xPos, this.yPos, true);
        if (board.checkState && !this.isASavior)
            return;
        if (gameLogic.horizontalProtection(board, this.xPos, this.yPos, this.type) || gameLogic.verticalProtection(board, this.xPos, this.yPos, this.type))
            return;
        if (!gameLogic.slashDiagonalProtection(board, this.xPos, this.yPos, this.type)) {
            for (int x = this.xPos + 1; x < board.getBoardWidth() && y < board.getBoardHeight(); x++, y++) {
                if (board.getBoardPosition(x, y) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, y, this.type))
                            board.colorSquare(x, y, false);
                    } else
                        board.colorSquare(x, y, false);
                } else if (board.getBoardPosition(x, y) == this.type)
                    break;
                else {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, y, this.type))
                            board.colorSquare(x, y, false);
                    } else
                        board.colorSquare(x, y, false);
                    break;
                }
            }
            y = this.yPos - 1;
            for (int x = this.xPos - 1; x >= 0 && y >= 0; x--, y--) {
                if (board.getBoardPosition(x, y) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, y, this.type))
                            board.colorSquare(x, y, false);
                    } else
                        board.colorSquare(x, y, false);
                } else if (board.getBoardPosition(x, y) == this.type)
                    break;
                else {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, y, this.type))
                            board.colorSquare(x, y, false);
                    } else
                        board.colorSquare(x, y, false);
                    break;
                }
            }
        }
        if (!gameLogic.backslashDiagonalProtection(board, this.xPos, this.yPos, this.type)) {
            y = this.yPos + 1;
            for (int x = this.xPos - 1; x >= 0 && y < board.getBoardHeight(); x--, y++) {
                if (board.getBoardPosition(x, y) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, y, this.type))
                            board.colorSquare(x, y, false);
                    } else
                        board.colorSquare(x, y, false);
                } else if (board.getBoardPosition(x, y) == this.type)
                    break;
                else {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, y, this.type))
                            board.colorSquare(x, y, false);
                    } else
                        board.colorSquare(x, y, false);
                    break;
                }
            }
            y = this.yPos - 1;
            for (int x = this.xPos + 1; x < board.getBoardWidth() && y >= 0; x++, y--) {
                if (board.getBoardPosition(x, y) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, y, this.type))
                            board.colorSquare(x, y, false);
                    } else
                        board.colorSquare(x, y, false);
                } else if (board.getBoardPosition(x, y) == this.type)
                    break;
                else {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, y, this.type))
                            board.colorSquare(x, y, false);
                    } else
                        board.colorSquare(x, y, false);
                    break;
                }
            }
        }
    }
}
