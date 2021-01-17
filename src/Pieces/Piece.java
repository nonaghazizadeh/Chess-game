package Pieces;

import Others.Board;
import Others.GameLogic;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;

public abstract class Piece {
    protected int type;
    protected int xPos;
    protected int yPos;
    protected String name;
    protected ImageView imageView = new ImageView();
    protected Translate pos;
    protected GameLogic gameLogic = new GameLogic();
    protected boolean isFirstTime;
    protected boolean isASavior = false;

    public Piece(int type, int xPos, int yPos) {
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        isFirstTime = true;
    }

    public void SelectPiece(Board board) {
    }

    public void MovePiece(Board board, int x, int y) {
        board.setBoard(this.xPos, this.yPos, 0);
        board.setPiece(this.xPos, this.yPos, null);
        if (!board.checkState && this.canCastle(board) != 0) {
            if (this.canCastle(board) == 1) {
                board.setBoard(x - 1, y, this.type);
                board.setPiece(x - 1, y, this);
                this.xPos = x - 1;
                board.setBoard(5, y, board.getPiece(7, y).type);
                board.setPiece(5, y, board.getPiece(7, y));
                board.getPiece(7, y).xPos = 7;
                board.setBoard(7, y, 0);
                board.setPiece(7, y, null);
            }
            if (this.canCastle(board) == 2) {
                board.setBoard(x + 2, y, this.type);
                board.setPiece(x + 2, y, this);
                this.xPos = x + 2;
                board.setBoard(3, y, board.getPiece(0, y).type);
                board.setPiece(3, y, board.getPiece(0, y));
                board.getPiece(3, y).xPos = 3;
                board.setBoard(0, y, 0);
                board.setPiece(0, y, null);
            }
            if (this.canCastle(board) == 3) {
                board.setBoard(x - 1, y, this.type);
                board.setPiece(x - 1, y, this);
                this.xPos = x - 1;
                board.setBoard(5, y, board.getPiece(7, y).type);
                board.setPiece(5, y, board.getPiece(7, y));
                board.getPiece(5, y).xPos = 5;
                board.setBoard(7, y, 0);
                board.setPiece(7, y, null);
            }
            if (this.canCastle(board) == 4) {
                board.setBoard(x + 2, y, this.type);
                board.setPiece(x + 2, y, this);
                this.xPos = x + 2;
                board.setBoard(3, y, board.getPiece(0, y).type);
                board.setPiece(3, y, board.getPiece(0, y));
                board.getPiece(3, y).xPos = 3;
                board.setBoard(0, y, 0);
                board.setPiece(0, y, null);
            }
        } else {
            this.xPos = x;
            this.yPos = y;
            if (board.getPiece(x, y) != null)
                board.getPiece(x, y).killed(board);
            board.setBoard(x, y, this.type);
            board.setPiece(x, y, this);
            if (this.name.equals("Pawn") && ((this.type == 1 && this.yPos == 0) || (this.type == 2 && this.yPos == 7))) {
                board.createPromotePiece(this);
                if (this.type == 1)
                    board.playerOnePawn--;
                else
                    board.playerTwoPawn--;
            }
        }
    }

    public ImageView getImage() {
        return (imageView);
    }

    public void centerImage() {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if (ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }

    public void killed(Board board) {
        if (this.type == 1) {
            if (this.name.equals("Rook"))
                board.playerOneRook--;
            else if (this.name.equals("Knight"))
                board.playerOneKnight--;
            else if (this.name.equals("Queen"))
                board.playerOneQueen--;
            else if (this.name.equals("Pawn"))
                board.playerOnePawn--;
            else if (this.name.equals("Bishop") && (this.xPos + this.yPos) % 2 != 0)
                board.playerOneBishopDarkSquare--;
            else if (this.name.equals("Bishop") && (this.xPos + this.yPos) % 2 == 0)
                board.playerOneBishopLightSquare--;
        } else {
            if (this.name.equals("Rook"))
                board.playerTwoRook--;
            else if (this.name.equals("Knight"))
                board.playerTwoKnight--;
            else if (this.name.equals("Queen"))
                board.playerTwoQueen--;
            else if (this.name.equals("Pawn"))
                board.playerTwoPawn--;
            else if (this.name.equals("Bishop") && (this.xPos + this.yPos) % 2 == 0)
                board.playerTwoBishopLightSquare--;
            else if (this.name.equals("Bishop") && (this.xPos + this.yPos) % 2 != 0)
                board.playerTwoBishopDarkSquare--;
        }
        board.getChildren().remove(this.getImage());
    }

    public int canCastle(Board board) {
        return 0;
    }

    public void resize(double width, double height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    public void relocate(double x, double y) {
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        centerImage();
    }

    public void resetPiece() {
        this.isFirstTime = true;
        this.isASavior = false;
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public void setFirstTime(boolean isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    public int getX() {
        return this.xPos;
    }

    public int getY() {
        return this.yPos;
    }

    public String getName() {
        return name;
    }

    public void setASavior(boolean ASavior) {
        isASavior = ASavior;
    }

    public int getType() {
        return type;
    }
}
