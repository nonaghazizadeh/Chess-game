package Pieces;

import Others.Board;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rook extends Piece {


    public Rook(int type, int xPos, int yPos) {
        super(type, xPos, yPos);
        name = "Rook";
        Image image;
        if (type == 1) {
            image = new Image("file:src/Images/White_Rook.png");
            imageView.setImage(image);
            imageView.fitHeightProperty();
            imageView.fitWidthProperty();
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } else {
            image = new Image("file:src/Images/Black_Rook.png");
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
        board.colorSquare(this.xPos, this.yPos, true);
        if (board.checkState && !this.isASavior)
            return;
        if (gameLogic.slashDiagonalProtection(board, this.xPos, this.yPos, this.type) || gameLogic.backslashDiagonalProtection(board, this.xPos, this.yPos, this.type))
            return;
        if (!gameLogic.horizontalProtection(board, this.xPos, this.yPos, this.type)) {
            for (int y = this.yPos - 1; y >= 0; y--) {
                if (board.getBoardPosition(this.xPos, y) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, this.xPos, y, this.type))
                            board.colorSquare(this.xPos, y, false);
                    } else
                        board.colorSquare(this.xPos, y, false);
                } else if (board.getBoardPosition(this.xPos, y) == this.type)
                    break;
                else {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, this.xPos, y, this.type))
                            board.colorSquare(this.xPos, y, false);
                    } else
                        board.colorSquare(this.xPos, y, false);
                    break;
                }
            }
            for (int y = this.yPos + 1; y < board.getBoardHeight(); y++) {
                if (board.getBoardPosition(this.xPos, y) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, this.xPos, y, this.type))
                            board.colorSquare(this.xPos, y, false);
                    } else
                        board.colorSquare(this.xPos, y, false);
                } else if (board.getBoardPosition(this.xPos, y) == this.type)
                    break;
                else {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, this.xPos, y, this.type))
                            board.colorSquare(this.xPos, y, false);
                    } else
                        board.colorSquare(this.xPos, y, false);
                    break;
                }
            }
        }
        if (!gameLogic.verticalProtection(board, this.xPos, this.yPos, this.type)) {
            for (int x = this.xPos - 1; x >= 0; x--) {
                if (board.getBoardPosition(x, this.yPos) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, this.yPos, this.type))
                            board.colorSquare(x, this.yPos, false);
                    } else
                        board.colorSquare(x, this.yPos, false);
                } else if (board.getBoardPosition(x, this.yPos) == this.type)
                    break;
                else {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, this.yPos, this.type))
                            board.colorSquare(x, this.yPos, false);
                    } else
                        board.colorSquare(x, this.yPos, false);
                    break;
                }
            }
            for (int x = this.xPos + 1; x < board.getBoardWidth(); x++) {
                if (board.getBoardPosition(x, this.yPos) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, this.yPos, this.type))
                            board.colorSquare(x, this.yPos, false);
                    } else
                        board.colorSquare(x, this.yPos, false);
                } else if (board.getBoardPosition(x, this.yPos) == this.type)
                    break;
                else {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, x, this.yPos, this.type))
                            board.colorSquare(x, this.yPos, false);
                    } else
                        board.colorSquare(x, this.yPos, false);
                    break;
                }
            }
        }
    }
}