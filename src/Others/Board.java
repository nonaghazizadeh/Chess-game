package Others;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import Pieces.*;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Pane {
    private final int boardWidth = 8;
    private final int boardHeight = 8;
    private int[][] board;
    private Piece[][] pieces;
    private Cell[][] cells;

    private Rook rookB1;
    private Knight knightB1;
    private Bishop bishopB1;
    private Queen queenB;
    private King kingB;
    private Bishop bishopB2;
    private Knight knightB2;
    private Rook rookB2;
    private Pawn pawnB1;
    private Pawn pawnB2;
    private Pawn pawnB3;
    private Pawn pawnB4;
    private Pawn pawnB5;
    private Pawn pawnB6;
    private Pawn pawnB7;
    private Pawn pawnB8;

    private Rook rookW1;
    private Knight knightW1;
    private Bishop bishopW1;
    private Queen queenW;
    private King kingW;
    private Bishop bishopW2;
    private Knight knightW2;
    private Rook rookW2;
    private Pawn pawnW1;
    private Pawn pawnW2;
    private Pawn pawnW3;
    private Pawn pawnW4;
    private Pawn pawnW5;
    private Pawn pawnW6;
    private Pawn pawnW7;
    private Pawn pawnW8;

    private Piece selectedPiece = null;

    private StatusBar statusBar = null;

    private GameLogic gameLogic = new GameLogic();
    public ArrayList<Piece> checkPieces = new ArrayList<Piece>();
    public ArrayList<Piece> saviorPieces = new ArrayList<Piece>();
    public int playerOneRook = 2;
    public int playerOneBishopLightSquare = 1;
    public int playerOneBishopDarkSquare = 1;
    public int playerOneKnight = 2;
    public int playerOneQueen = 1;
    public int playerOnePawn = 8;
    public int playerTwoRook = 2;
    public int playerTwoBishopLightSquare = 1;
    public int playerTwoBishopDarkSquare = 1;
    public int playerTwoKnight = 2;
    public int playerTwoQueen = 1;
    public int playerTwoPawn = 8;
    private Alert alert;

    private Rectangle background;
    private double cellWidth;
    private double cellHeight;
    private int currentPlayer;
    private boolean isBlack = false;
    public boolean checkmate = false;
    public boolean checkState = false;
    public boolean stalemate = false;

    private StatusBar.Timer timer;

    public Board(StatusBar newStatusBar) {
        statusBar = newStatusBar;
        statusBar.whitePlayerAlert.setText("");
        statusBar.blackPlayerAlert.setText("");
        statusBar.whitePlayerTimer.setText("White player timer: 15:00");
        statusBar.blackPlayerTimer.setText("Black player timer: 15:00");

        background = new Rectangle();
        background.setFill(Color.WHITE);
        getChildren().add(background);

        board = new int[boardWidth][boardHeight];
        pieces = new Piece[boardWidth][boardHeight];
        cells = new Cell[boardWidth][boardHeight];

        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0 || i == 0) {
                isBlack = false;
            } else
                isBlack = true;
            for (int j = 0; j < 8; j++) {
                board[i][j] = 0;
                if (isBlack) {
                    cells[i][j] = new Cell(0);
                    isBlack = false;
                } else {
                    cells[i][j] = new Cell(1);
                    isBlack = true;
                }
                getChildren().add(cells[i][j]);
                pieces[i][j] = null;
            }
        }
        currentPlayer = 1;
        initPiece();
        timer = new StatusBar.Timer(this);
        timer.timeline.setCycleCount(Timeline.INDEFINITE);
        timer.timeline.play();
        timer.playerTurn = currentPlayer;
    }

    public void initPiece() {
        rookB1 = new Rook(2, 0, 0);
        knightB1 = new Knight(2, 1, 0);
        bishopB1 = new Bishop(2, 2, 0);
        queenB = new Queen(2, 3, 0);
        kingB = new King(2, 4, 0);
        bishopB2 = new Bishop(2, 5, 0);
        knightB2 = new Knight(2, 6, 0);
        rookB2 = new Rook(2, 7, 0);
        pawnB1 = new Pawn(2, 0, 1);
        pawnB2 = new Pawn(2, 1, 1);
        pawnB3 = new Pawn(2, 2, 1);
        pawnB4 = new Pawn(2, 3, 1);
        pawnB5 = new Pawn(2, 4, 1);
        pawnB6 = new Pawn(2, 5, 1);
        pawnB7 = new Pawn(2, 6, 1);
        pawnB8 = new Pawn(2, 7, 1);

        rookW1 = new Rook(1, 0, 7);
        knightW1 = new Knight(1, 1, 7);
        bishopW1 = new Bishop(1, 2, 7);
        queenW = new Queen(1, 3, 7);
        kingW = new King(1, 4, 7);
        bishopW2 = new Bishop(1, 5, 7);
        knightW2 = new Knight(1, 6, 7);
        rookW2 = new Rook(1, 7, 7);
        pawnW1 = new Pawn(1, 0, 6);
        pawnW2 = new Pawn(1, 1, 6);
        pawnW3 = new Pawn(1, 2, 6);
        pawnW4 = new Pawn(1, 3, 6);
        pawnW5 = new Pawn(1, 4, 6);
        pawnW6 = new Pawn(1, 5, 6);
        pawnW7 = new Pawn(1, 6, 6);
        pawnW8 = new Pawn(1, 7, 6);

        pieces[0][0] = rookB1;
        pieces[1][0] = knightB1;
        pieces[2][0] = bishopB1;
        pieces[3][0] = queenB;
        pieces[4][0] = kingB;
        pieces[5][0] = bishopB2;
        pieces[6][0] = knightB2;
        pieces[7][0] = rookB2;

        pieces[0][1] = pawnB1;
        pieces[1][1] = pawnB2;
        pieces[2][1] = pawnB3;
        pieces[3][1] = pawnB4;
        pieces[4][1] = pawnB5;
        pieces[5][1] = pawnB6;
        pieces[6][1] = pawnB7;
        pieces[7][1] = pawnB8;

        for (int y = 2; y < 6; y++) {
            for (int x = 0; x < boardWidth; x++) {
                pieces[x][y] = null;
            }
        }

        pieces[0][6] = pawnW1;
        pieces[1][6] = pawnW2;
        pieces[2][6] = pawnW3;
        pieces[3][6] = pawnW4;
        pieces[4][6] = pawnW5;
        pieces[5][6] = pawnW6;
        pieces[6][6] = pawnW7;
        pieces[7][6] = pawnW8;

        pieces[0][7] = rookW1;
        pieces[1][7] = knightW1;
        pieces[2][7] = bishopW1;
        pieces[3][7] = queenW;
        pieces[4][7] = kingW;
        pieces[5][7] = bishopW2;
        pieces[6][7] = knightW2;
        pieces[7][7] = rookW2;

        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                if (y == 0 || y == 1)
                    board[x][y] = 2;
                else if (y == 6 || y == 7)
                    board[x][y] = 1;
                else
                    board[x][y] = 0;
            }
        }

        for (int i = 0; i < 8; i++) {
            getChildren().addAll(pieces[i][0].getImage(), pieces[i][1].getImage(), pieces[i][6].getImage(), pieces[i][7].getImage());
        }
    }

    @Override
    public void resize(double width, double height) {
        super.resize(width, height);
        background.setWidth(width);
        background.setHeight(height);
        cellWidth = width / 8.0;
        cellHeight = height / 8.0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != 0) {
                    pieces[i][j].relocate(i * cellWidth, j * cellHeight);
                    pieces[i][j].resize(cellWidth, cellHeight);
                }
                cells[i][j].relocate(i * cellWidth, j * cellHeight);
                cells[i][j].resize(cellWidth, cellHeight);
            }
        }
    }

    public void resetGame() {
        timer.playerTurn = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = 0;
                if (pieces[i][j] != null)
                    getChildren().remove(pieces[i][j].getImage());
                getChildren().remove(pieces[i][j]);
                pieces[i][j] = null;
            }
        }
        currentPlayer = 1;
        initPiece();
        for (int i = 0; i < 8; i++) {
            pieces[i][0].resetPiece();
            pieces[i][1].resetPiece();
            pieces[i][6].resetPiece();
            pieces[i][7].resetPiece();
        }
        removeBorder();
        statusBar.whitePlayerAlert.setText(Player.getFirstPlayerName() + "'s turn");
        statusBar.blackPlayerAlert.setText("");
        statusBar.whitePlayerTimer.setText("White player timer: 15:00");
        statusBar.blackPlayerTimer.setText("Black player timer: 15:00");
        statusBar.winner.setText("");
        checkmate = false;
        checkState = false;
        stalemate = false;
        selectedPiece = null;
        playerOneRook = 2;
        playerOneBishopLightSquare = 1;
        playerOneBishopDarkSquare = 1;
        playerOneKnight = 2;
        playerOneQueen = 1;
        playerOnePawn = 8;
        playerTwoRook = 2;
        playerTwoBishopLightSquare = 1;
        playerTwoBishopDarkSquare = 1;
        playerTwoKnight = 2;
        playerTwoQueen = 1;
        playerTwoPawn = 8;
        checkPieces.clear();
        saviorPieces.clear();
        timer.timeIsOver = false;
        timer.whiteTimer = 900;
        timer.blackTimer = 900;
        timer.playerTurn = currentPlayer;
        timer.timeline.play();
    }

    public void selectPiece(final double x, final double y) {
        int indexX = (int) (x / cellWidth);
        int indexY = (int) (y / cellHeight);
        if (!checkmate && !stalemate && !timer.timeIsOver) {
            if (cells[indexX][indexY].isHaveBorder()) {
                movePiece(x, y);
                removeBorder();
                selectedPiece = null;
            } else {
                if (board[indexX][indexY] == currentPlayer) {
                    removeBorder();
                    pieces[indexX][indexY].SelectPiece(this);
                    selectedPiece = pieces[indexX][indexY];
                }
            }
        }
    }

    public void movePiece(final double x, final double y) {
        int indexX = (int) (x / cellWidth);
        int indexY = (int) (y / cellHeight);
        selectedPiece.MovePiece(this, indexX, indexY);
        selectedPiece.setFirstTime(false);
        if (currentPlayer == 1) {
            currentPlayer = 2;
            statusBar.whitePlayerAlert.setText("");
            checkState = false;
            for (Iterator<Piece> piece = saviorPieces.iterator(); piece.hasNext(); ) {
                Piece item = piece.next();
                item.setASavior(false);
            }
            if (gameLogic.isCheck(this, kingB.getX(), kingB.getY(), currentPlayer, true)) {
                checkPieces.clear();
                saviorPieces.clear();
                checkState = true;
                gameLogic.findAllCheckPieces(this, kingB.getX(), kingB.getY(), currentPlayer);
                if (gameLogic.isCheckmate(this, kingB.getX(), kingB.getY(), currentPlayer)) {
                    checkmate = true;
                    statusBar.blackPlayerAlert.setText(Player.getSecondPlayerName() + " (black player) is in checkmate");
                    statusBar.winner.setText(Player.getFirstPlayerName() + " (white player) won !");
                    Player playerOne = Player.getPlayerByUsername(Player.getFirstPlayerName());
                    Player playerTwo = Player.getPlayerByUsername(Player.getSecondPlayerName());
                    playerOne.setWins(playerOne.getWins() + 1);
                    playerOne.setScore(playerOne.getScore() + 1);
                    playerTwo.setLoses(playerTwo.getLoses() + 1);
                    playerTwo.setScore(playerTwo.getScore() - 1);
                } else
                    statusBar.blackPlayerAlert.setText(Player.getSecondPlayerName() + " (black player) is in check");
            } else if (gameLogic.isStalemate(this, kingB, currentPlayer))
                statusBar.winner.setText("Stalemate !");
            else
                statusBar.blackPlayerAlert.setText(Player.getSecondPlayerName() + "'s turn");
        } else {
            currentPlayer = 1;
            statusBar.blackPlayerAlert.setText("");
            checkState = false;
            for (Iterator<Piece> piece = saviorPieces.iterator(); piece.hasNext(); ) {
                Piece item = piece.next();
                item.setASavior(false);
            }
            if (gameLogic.isCheck(this, kingW.getX(), kingW.getY(), currentPlayer, true)) {
                checkPieces.clear();
                saviorPieces.clear();
                checkState = true;
                gameLogic.findAllCheckPieces(this, kingW.getX(), kingW.getY(), currentPlayer);
                if (gameLogic.isCheckmate(this, kingW.getX(), kingW.getY(), currentPlayer)) {
                    checkmate = true;
                    statusBar.whitePlayerAlert.setText(Player.getFirstPlayerName() + " (white player) is in checkmate");
                    statusBar.winner.setText(Player.getSecondPlayerName() + " (black player) won !");
                    Player playerOne = Player.getPlayerByUsername(Player.getFirstPlayerName());
                    Player playerTwo = Player.getPlayerByUsername(Player.getSecondPlayerName());
                    playerOne.setLoses(playerOne.getLoses() + 1);
                    playerOne.setLoses(playerOne.getScore() - 1);
                    playerTwo.setWins(playerOne.getWins() + 1);
                    playerOne.setScore(playerOne.getScore() + 1);
                } else
                    statusBar.whitePlayerAlert.setText(Player.getFirstPlayerName() + " (white player) is in check");
            } else if (gameLogic.isStalemate(this, kingW, currentPlayer))
                statusBar.winner.setText("Stalemate !");
            else
                statusBar.whitePlayerAlert.setText(Player.getFirstPlayerName() + "'s turn");
        }
        timer.playerTurn = currentPlayer;
    }

    public void createPromotePiece(Piece piece) {
        Piece promotedPiece;
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Promote a piece");
        alert.setHeaderText("You can promote your pawn into another piece");
        alert.setContentText("Choose one of these piece");

        ButtonType buttonRook = new ButtonType("Rook");
        ButtonType buttonKnight = new ButtonType("Knight");
        ButtonType buttonBishop = new ButtonType("Bishop");
        ButtonType buttonQueen = new ButtonType("Queen");

        alert.getButtonTypes().setAll(buttonRook, buttonKnight, buttonBishop, buttonQueen);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonRook) {
            promotedPiece = new Rook(piece.getType(), piece.getX(), piece.getY());
            getChildren().remove(piece.getImage());
            getChildren().add(promotedPiece.getImage());
            pieces[piece.getX()][piece.getY()] = promotedPiece;
            if (piece.getType() == 1)
                playerOneRook++;
            else
                playerTwoRook++;
        } else if (result.get() == buttonKnight) {
            promotedPiece = new Knight(piece.getType(), piece.getX(), piece.getY());
            getChildren().remove(piece.getImage());
            getChildren().add(promotedPiece.getImage());
            pieces[piece.getX()][piece.getY()] = promotedPiece;
            if (piece.getType() == 1)
                playerOneKnight++;
            else
                playerTwoKnight++;
        } else if (result.get() == buttonBishop) {
            promotedPiece = new Bishop(piece.getType(), piece.getX(), piece.getY());
            getChildren().remove(piece.getImage());
            getChildren().add(promotedPiece.getImage());
            pieces[piece.getX()][piece.getY()] = promotedPiece;
            if (piece.getType() == 1) {
                if ((piece.getX() + piece.getY()) % 2 != 0)
                    playerOneBishopDarkSquare++;
                else
                    playerOneBishopLightSquare++;
            } else {
                if ((piece.getX() + piece.getY()) % 2 == 0)
                    playerTwoBishopLightSquare++;
                else
                    playerTwoBishopDarkSquare++;
            }
        } else if (result.get() == buttonQueen) {
            promotedPiece = new Queen(piece.getType(), piece.getX(), piece.getY());
            getChildren().remove(piece.getImage());
            getChildren().add(promotedPiece.getImage());
            pieces[piece.getX()][piece.getY()] = promotedPiece;
            if (piece.getType() == 1)
                playerOneQueen++;
            else
                playerTwoQueen++;
        }
    }

    public void colorSquare(int x, int y, boolean selectedPiece) {
        if (selectedPiece)
            cells[x][y].cellBorder(Color.RED);
        else
            cells[x][y].cellBorder(Color.GREEN);
    }

    public void removeBorder() {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                if (cells[x][y].getRectangle().getStroke() != null)
                    cells[x][y].removeCellBorder();
            }
        }
    }

    public void timerOver(int playerOutOfTime) {
        timer.timeline.stop();
        if (playerOutOfTime == 1) {
            statusBar.whitePlayerAlert.setText(Player.getFirstPlayerName() + " (white player) time ends!");
            statusBar.winner.setText(Player.getSecondPlayerName() + " (black player) won!");
            Player playerOne = Player.getPlayerByUsername(Player.getFirstPlayerName());
            Player playerTwo = Player.getPlayerByUsername(Player.getSecondPlayerName());
            playerOne.setLoses(playerOne.getLoses() + 1);
            playerOne.setLoses(playerOne.getScore() - 1);
            playerTwo.setWins(playerOne.getWins() + 1);
            playerOne.setScore(playerOne.getScore() + 1);
        } else if (playerOutOfTime == 2) {
            statusBar.blackPlayerAlert.setText(Player.getSecondPlayerName() + " (black player) time ends!");
            statusBar.winner.setText(Player.getFirstPlayerName() + " (white player) won!");
            Player playerOne = Player.getPlayerByUsername(Player.getFirstPlayerName());
            Player playerTwo = Player.getPlayerByUsername(Player.getSecondPlayerName());
            playerOne.setWins(playerOne.getWins() + 1);
            playerOne.setScore(playerOne.getScore() + 1);
            playerTwo.setLoses(playerTwo.getLoses() + 1);
            playerTwo.setScore(playerTwo.getScore() - 1);
        }
    }

    public Piece getKing(int type) {
        if (type == 1)
            return (kingW);
        return (kingB);
    }

    public int getBoardHeight() {
        return (this.boardHeight);
    }

    public int getBoardWidth() {
        return (this.boardWidth);
    }

    public int getBoardPosition(int x, int y) {
        return (this.board[x][y]);
    }

    public void setBoard(int x, int y, int type) {
        this.board[x][y] = type;
    }

    public Piece getPiece(int x, int y) {
        return (pieces[x][y]);
    }

    public void setPiece(int x, int y, Piece piece) {
        this.pieces[x][y] = piece;
    }

    public StatusBar getStatusBar() {
        return (statusBar);
    }

}
