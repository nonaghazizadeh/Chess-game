package Pieces;

import Others.Board;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn extends Piece {

    public Pawn(int type, int xPos, int yPos) {
        super(type, xPos, yPos);
        name = "Pawn";
        Image image;
        if (type == 1) {
            image = new Image("file:src/Images/White_Pawn.png");
            imageView.setImage(image);
            imageView.fitHeightProperty();
            imageView.fitWidthProperty();
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } else {
            image = new Image("file:src/Images/Black_Pawn.png");
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
        if (gameLogic.horizontalProtection(board, this.xPos, this.yPos, this.type))
            return;
        if (this.type == 1) {
            if (!gameLogic.slashDiagonalProtection(board, this.xPos, this.yPos, this.type) && !gameLogic.backslashDiagonalProtection(board, this.xPos, this.yPos, this.type)) {
                if (this.yPos - 1 >= 0 && board.getBoardPosition(this.xPos, this.yPos - 1) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, this.xPos, this.yPos - 1, this.type))
                            board.colorSquare(this.xPos, this.yPos - 1, false);
                    } else
                        board.colorSquare(this.xPos, this.yPos - 1, false);
                }
                if (this.isFirstTime && board.getBoardPosition(this.xPos, this.yPos - 2) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, this.xPos, this.yPos - 2, this.type))
                            board.colorSquare(this.xPos, this.yPos - 2, false);
                    } else
                        board.colorSquare(this.xPos, this.yPos - 2, false);
                }
            }
            if (!gameLogic.verticalProtection(board, this.xPos, this.yPos, this.type)) {
                if (!gameLogic.slashDiagonalProtection(board, this.xPos, this.yPos, this.type)) {
                    if (this.yPos - 1 >= 0 && this.xPos - 1 >= 0 && board.getBoardPosition(this.xPos - 1, this.yPos - 1) != this.type && board.getBoardPosition(this.xPos - 1, this.yPos - 1) != 0) {
                        if (board.checkState) {
                            if (gameLogic.isThisProtecting(board, this.xPos - 1, this.yPos - 1, this.type))
                                board.colorSquare(this.xPos - 1, this.yPos - 1, false);
                        } else
                            board.colorSquare(this.xPos - 1, this.yPos - 1, false);
                    }
                }
                if (!gameLogic.backslashDiagonalProtection(board, this.xPos, this.yPos, this.type)) {
                    if (this.yPos - 1 >= 0 && this.xPos + 1 < board.getBoardWidth() && board.getBoardPosition(this.xPos + 1, this.yPos - 1) != this.type && board.getBoardPosition(this.xPos + 1, this.yPos - 1) != 0) {
                        if (board.checkState) {
                            if (gameLogic.isThisProtecting(board, this.xPos + 1, this.yPos - 1, this.type))
                                board.colorSquare(this.xPos + 1, this.yPos - 1, false);
                        } else
                            board.colorSquare(this.xPos + 1, this.yPos - 1, false);
                    }
                }
            }
        } else if (this.type == 2) {
            if (!gameLogic.slashDiagonalProtection(board, this.xPos, this.yPos, this.type) && !gameLogic.backslashDiagonalProtection(board, this.xPos, this.yPos, this.type)) {
                if (this.yPos + 1 < board.getBoardHeight() && board.getBoardPosition(this.xPos, this.yPos + 1) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, this.xPos, this.yPos + 1, this.type))
                            board.colorSquare(this.xPos, this.yPos + 1, false);
                    } else
                        board.colorSquare(this.xPos, this.yPos + 1, false);
                }
                if (this.isFirstTime == true && board.getBoardPosition(this.xPos, this.yPos + 2) == 0) {
                    if (board.checkState) {
                        if (gameLogic.isThisProtecting(board, this.xPos, this.yPos + 2, this.type))
                            board.colorSquare(this.xPos, this.yPos + 2, false);
                    } else
                        board.colorSquare(this.xPos, this.yPos + 2, false);
                }
            }
            if (!gameLogic.verticalProtection(board, this.xPos, this.yPos, this.type)) {
                if (!gameLogic.backslashDiagonalProtection(board, this.xPos, this.yPos, this.type)) {
                    if (this.yPos + 1 < board.getBoardHeight() && this.xPos - 1 >= 0 && board.getBoardPosition(this.xPos - 1, this.yPos + 1) != this.type && board.getBoardPosition(this.xPos - 1, this.yPos + 1) != 0) {
                        if (board.checkState) {
                            if (gameLogic.isThisProtecting(board, this.xPos - 1, this.yPos + 1, this.type))
                                board.colorSquare(this.xPos - 1, this.yPos + 1, false);
                        } else
                            board.colorSquare(this.xPos - 1, this.yPos + 1, false);
                    }
                }
                if (!gameLogic.slashDiagonalProtection(board, this.xPos, this.yPos, this.type)) {
                    if (this.yPos + 1 < board.getBoardHeight() && this.xPos + 1 < board.getBoardWidth() && board.getBoardPosition(this.xPos + 1, this.yPos + 1) != this.type && board.getBoardPosition(this.xPos + 1, this.yPos + 1) != 0) {
                        if (board.checkState) {
                            if (gameLogic.isThisProtecting(board, this.xPos + 1, this.yPos + 1, this.type))
                                board.colorSquare(this.xPos + 1, this.yPos + 1, false);
                        } else
                            board.colorSquare(this.xPos + 1, this.yPos + 1, false);
                    }
                }
            }
        }
    }
}
