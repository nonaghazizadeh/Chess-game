package Others;

import Pieces.Piece;

import java.util.Iterator;

public class GameLogic {
    private boolean isOneKingStalemate(Board board, Piece king, int type) {
        int piece = 0;
        boolean stalemate = true;
        for (int y = 0; y < board.getBoardHeight(); y++) {
            for (int x = 0; x < board.getBoardWidth(); x++) {
                if (board.getBoardPosition(x, y) == type)
                    piece++;
            }
        }
        if (piece == 1) {
            for (int y = king.getY() - 1; y <= king.getY() + 1; y++) {
                for (int x = king.getX() - 1; x <= king.getX() + 1; x++) {
                    if (y >= 0 && y < board.getBoardHeight() && x >= 0 && x < board.getBoardWidth() && board.getBoardPosition(x, y) != type) {
                        if (!isCheck(board, x, y, type, true)) {
                            stalemate = false;
                            break;
                        }
                    }
                }
                if (!stalemate)
                    break;
            }
        } else
            stalemate = false;
        return (stalemate);
    }

    public boolean isLimitPieceStalemate(Board board) {
        if (board.playerOneQueen != 0 || board.playerTwoQueen != 0)
            return (false);
        else if (board.playerOneRook != 0 || board.playerTwoRook != 0)
            return (false);
        else if (board.playerOneKnight > 1 || board.playerTwoKnight > 1)
            return (false);
        else if (((board.playerOneBishopDarkSquare != 0 || board.playerOneBishopLightSquare != 0) && board.playerOneKnight != 0) ||
                ((board.playerTwoBishopDarkSquare != 0 || board.playerTwoBishopLightSquare != 0) && board.playerTwoKnight != 0))
            return (false);
        else if ((board.playerOneBishopDarkSquare != 0 && board.playerOneBishopLightSquare != 0) || (board.playerTwoBishopDarkSquare != 0 && board.playerTwoBishopLightSquare != 0))
            return (false);
        else if (board.playerOnePawn > 1 || board.playerTwoPawn > 1)
            return (false);
        return (true);
    }

    public boolean isStalemate(Board board, Piece king, int type) {
        if (isOneKingStalemate(board, king, type) || isLimitPieceStalemate(board)) {
            board.stalemate = true;
            return (true);
        }
        return (false);
    }

    public boolean verticalProtection(Board board, int xPos, int yPos, int type) {
        int y;
        int opponentType;
        if (type == 1)
            opponentType = 2;
        else
            opponentType = 1;
        for (y = yPos - 1; y >= 0; y--) {
            if (board.getBoardPosition(xPos, y) == type && board.getPiece(xPos, y).getName() == "King") {
                for (y = yPos + 1; y < board.getBoardHeight(); y++) {
                    if (board.getBoardPosition(xPos, y) == type)
                        break;
                    else if (board.getBoardPosition(xPos, y) == opponentType) {
                        if (board.getPiece(xPos, y).getName() == "Queen" || board.getPiece(xPos, y).getName() == "Rook")
                            return (true);
                        else
                            break;
                    }
                }
                break;
            } else if (board.getBoardPosition(xPos, y) != 0)
                break;
        }
        for (y = yPos + 1; y < board.getBoardHeight(); y++) {
            if (board.getBoardPosition(xPos, y) == type && board.getPiece(xPos, y).getName().equals("King")) {
                for (y = yPos - 1; y >= 0; y--) {
                    if (board.getBoardPosition(xPos, y) == type)
                        break;
                    else if (board.getBoardPosition(xPos, y) == opponentType) {
                        if (board.getPiece(xPos, y).getName().equals("Queen") || board.getPiece(xPos, y).getName().equals("Rook"))
                            return (true);
                        else
                            break;
                    }
                }
                break;
            } else if (board.getBoardPosition(xPos, y) != 0)
                break;
        }
        return (false);
    }

    public boolean horizontalProtection(Board board, int xPos, int yPos, int type) {
        int x;
        int opponentType;
        if (type == 1)
            opponentType = 2;
        else
            opponentType = 1;
        for (x = xPos - 1; x >= 0; x--) {
            if (board.getBoardPosition(x, yPos) == type && board.getPiece(x, yPos).getName() == "King") {
                for (x = xPos + 1; x < board.getBoardWidth(); x++) {
                    if (board.getBoardPosition(x, yPos) == type)
                        break;
                    else if (board.getBoardPosition(x, yPos) == opponentType) {
                        if (board.getPiece(x, yPos).getName() == "Queen" || board.getPiece(x, yPos).getName() == "Rook")
                            return (true);
                        else
                            break;
                    }
                }
                break;
            } else if (board.getBoardPosition(x, yPos) != 0)
                break;
        }
        for (x = xPos + 1; x < board.getBoardWidth(); x++) {
            if (board.getBoardPosition(x, yPos) == type && board.getPiece(x, yPos).getName().equals("King")) {
                for (x = xPos - 1; x >= 0; x--) {
                    if (board.getBoardPosition(x, yPos) == type)
                        break;
                    else if (board.getBoardPosition(x, yPos) == opponentType) {
                        if (board.getPiece(x, yPos).getName().equals("Queen") || board.getPiece(x, yPos).getName().equals("Rook"))
                            return (true);
                        else
                            break;
                    }
                }
                break;
            } else if (board.getBoardPosition(x, yPos) != 0)
                break;
        }
        return (false);
    }

    public boolean slashDiagonalProtection(Board board, int xPos, int yPos, int type) {
        int opponentType;
        if (type == 1)
            opponentType = 2;
        else
            opponentType = 1;
        int y = yPos - 1;
        for (int x = xPos + 1; x < board.getBoardWidth() && y >= 0; x++, y--) {
            if (board.getBoardPosition(x, y) == type && board.getPiece(x, y).getName().equals("King")) {
                y = yPos + 1;
                for (x = xPos - 1; x >= 0 && y < board.getBoardHeight(); x--, y++) {
                    if (board.getBoardPosition(x, y) == type)
                        break;
                    else if (board.getBoardPosition(x, y) == opponentType) {
                        if (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop"))
                            return (true);
                        else
                            break;
                    }
                }
                break;
            } else if (board.getBoardPosition(x, y) != 0)
                break;
        }
        y = yPos + 1;
        for (int x = xPos - 1; x >= 0 && y < board.getBoardHeight(); x--, y++) {
            if (board.getBoardPosition(x, y) == type && board.getPiece(x, y).getName().equals("King")) {
                y = yPos - 1;
                for (x = xPos + 1; x < board.getBoardWidth() && y >= 0; x++, y--) {
                    if (board.getBoardPosition(x, y) == type)
                        break;
                    else if (board.getBoardPosition(x, y) == opponentType) {
                        if (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop"))
                            return (true);
                        else
                            break;
                    }
                }
                break;
            } else if (board.getBoardPosition(x, y) != 0)
                break;
        }
        return (false);
    }

    public boolean backslashDiagonalProtection(Board board, int xPos, int yPos, int type) {
        int opponentType;
        if (type == 1)
            opponentType = 2;
        else
            opponentType = 1;
        int y = yPos - 1;
        for (int x = xPos - 1; x >= 0 && y >= 0; x--, y--) {
            if (board.getBoardPosition(x, y) == type && board.getPiece(x, y).getName().equals("King")) {
                y = yPos + 1;
                for (x = xPos + 1; x < board.getBoardWidth() && y < board.getBoardHeight(); x++, y++) {
                    if (board.getBoardPosition(x, y) == type)
                        break;
                    else if (board.getBoardPosition(x, y) == opponentType) {
                        if (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop"))
                            return (true);
                        else
                            break;
                    }
                }
                break;
            } else if (board.getBoardPosition(x, y) != 0)
                break;
        }
        y = yPos + 1;
        for (int x = xPos + 1; x < board.getBoardWidth() && y < board.getBoardHeight(); x++, y++) {
            if (board.getBoardPosition(x, y) == type && board.getPiece(x, y).getName().equals("King")) {
                y = yPos - 1;
                for (x = xPos - 1; x >= 0 && y >= 0; x--, y--) {
                    if (board.getBoardPosition(x, y) == type)
                        break;
                    else if (board.getBoardPosition(x, y) == opponentType) {
                        if (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop"))
                            return (true);
                        else
                            break;
                    }
                }
                break;
            } else if (board.getBoardPosition(x, y) != 0)
                break;
        }
        return (false);
    }

    public boolean isCheck(Board board, int xPos, int yPos, int type, boolean kingCanCapture) {
        int y;
        int x;
        int opponentType;
        if (type == 1)
            opponentType = 2;
        else
            opponentType = 1;
        for (x = xPos - 1; x >= 0; x--) {
            if (board.getBoardPosition(x, yPos) == type && !board.getPiece(x, yPos).getName().equals("King"))
                break;
            else if (board.getBoardPosition(x, yPos) == opponentType) {
                if (x == xPos - 1 && board.getPiece(x, yPos) != null && kingCanCapture && board.getPiece(x, yPos).getName().equals("King"))
                    return (true);
                else if (board.getPiece(x, yPos) != null && (board.getPiece(x, yPos).getName().equals("Queen") || board.getPiece(x, yPos).getName().equals("Rook")))
                    return (true);
                else
                    break;
            }
        }
        for (x = xPos + 1; x < board.getBoardWidth(); x++) {
            if (board.getBoardPosition(x, yPos) == type && !board.getPiece(x, yPos).getName().equals("King"))
                break;
            else if (board.getBoardPosition(x, yPos) == opponentType) {
                if (x == xPos + 1 && board.getPiece(x, yPos) != null && kingCanCapture && board.getPiece(x, yPos).getName().equals("King"))
                    return (true);
                else if (board.getPiece(x, yPos) != null && (board.getPiece(x, yPos).getName().equals("Queen") || board.getPiece(x, yPos).getName().equals("Rook")))
                    return (true);
                else
                    break;
            }
        }
        for (y = yPos - 1; y >= 0; y--) {
            if (board.getBoardPosition(xPos, y) == type && !board.getPiece(xPos, y).getName().equals("King"))
                break;
            else if (board.getBoardPosition(xPos, y) == opponentType) {
                if (y == yPos - 1 && board.getPiece(xPos, y) != null && kingCanCapture && board.getPiece(xPos, y).getName().equals("King"))
                    return (true);
                else if (board.getPiece(xPos, y) != null && (board.getPiece(xPos, y).getName().equals("Queen") || board.getPiece(xPos, y).getName().equals("Rook")))
                    return (true);
                else
                    break;
            }
        }
        for (y = yPos + 1; y < board.getBoardHeight(); y++) {
            if (board.getBoardPosition(xPos, y) == type && !board.getPiece(xPos, y).getName().equals("King"))
                break;
            else if (board.getBoardPosition(xPos, y) == opponentType) {
                if (y == yPos + 1 && board.getPiece(xPos, y) != null && kingCanCapture && board.getPiece(xPos, y).getName().equals("King"))
                    return (true);
                else if (board.getPiece(xPos, y) != null && (board.getPiece(xPos, y).getName().equals("Queen") || board.getPiece(xPos, y).getName().equals("Rook")))
                    return (true);
                else
                    break;
            }
        }
        for (y = yPos - 1, x = xPos - 1; y >= 0 && x >= 0; y--, x--) {
            if (board.getBoardPosition(x, y) == type && !board.getPiece(x, y).getName().equals("King"))
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (y == yPos - 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && ((kingCanCapture && board.getPiece(x, y).getName().equals("King")) || (type == 1 && board.getPiece(x, y).getName().equals("Pawn"))))
                    return (true);
                else if (board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    return (true);
                else
                    break;
            }
        }

        for (y = yPos + 1, x = xPos + 1; y < board.getBoardHeight() && x < board.getBoardWidth(); y++, x++) {
            if (board.getBoardPosition(x, y) == type && !board.getPiece(x, y).getName().equals("King"))
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (y == yPos + 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && ((kingCanCapture && board.getPiece(x, y).getName().equals("King")) || (type == 2 && board.getPiece(x, y).getName().equals("Pawn"))))
                    return (true);
                else if (board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    return (true);
                else
                    break;
            }
        }
        for (y = yPos - 1, x = xPos + 1; y >= 0 && x < board.getBoardWidth(); y--, x++) {
            if (board.getBoardPosition(x, y) == type && !board.getPiece(x, y).getName().equals("King"))
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (y == yPos - 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && ((kingCanCapture && board.getPiece(x, y).getName().equals("King")) || (type == 1 && board.getPiece(x, y).getName().equals("Pawn"))))
                    return (true);
                else if (board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    return (true);
                else
                    break;
            }
        }
        for (y = yPos + 1, x = xPos - 1; y < board.getBoardHeight() && x >= 0; y++, x--) {
            if (board.getBoardPosition(x, y) == type && !board.getPiece(x, y).getName().equals("King"))
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (y == yPos + 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && ((kingCanCapture && board.getPiece(x, y).getName().equals("King")) || (type == 2 && board.getPiece(x, y).getName().equals("Pawn"))))
                    return (true);
                else if (board.getBoardPosition(x, y) != 0 && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    return (true);
                else
                    break;
            }
        }
        for (y = -2; y <= 2; y++) {
            if (y != 0) {
                x = y % 2 == 0 ? 1 : 2;
                if (yPos + y >= 0 && yPos + y < board.getBoardHeight() && xPos - x >= 0 && xPos - x < board.getBoardWidth() && board.getBoardPosition(xPos - x, yPos + y) != type && board.getBoardPosition(xPos - x, yPos + y) != 0) {
                    if (board.getPiece(xPos - x, yPos + y) != null && board.getPiece(xPos - x, yPos + y).getName().equals("Knight"))
                        return (true);
                }
                if (yPos + y >= 0 && yPos + y < board.getBoardHeight() && xPos + x >= 0 && xPos + x < board.getBoardWidth() && board.getBoardPosition(xPos + x, yPos + y) != type && board.getBoardPosition(xPos + x, yPos + y) != 0) {
                    if (board.getPiece(xPos + x, yPos + y) != null && board.getPiece(xPos + x, yPos + y).getName().equals("Knight"))
                        return (true);
                }
            }
        }
        return (false);
    }

    public void findAllCheckPieces(Board board, int xPos, int yPos, int type) {
        int y;
        int x;
        int opponentType;
        if (type == 1)
            opponentType = 2;
        else
            opponentType = 1;
        for (x = xPos - 1; x >= 0; x--) {
            if (board.getBoardPosition(x, yPos) == type)
                break;
            else if (board.getBoardPosition(x, yPos) == opponentType) {
                if (board.getPiece(x, yPos) != null && (board.getPiece(x, yPos).getName().equals("Queen") || board.getPiece(x, yPos).getName().equals("Rook")))
                    board.checkPieces.add(board.getPiece(x, yPos));
                else
                    break;
            }
        }
        for (x = xPos + 1; x < board.getBoardWidth(); x++) {
            if (board.getBoardPosition(x, yPos) == type)
                break;
            else if (board.getBoardPosition(x, yPos) == opponentType) {
                if (board.getPiece(x, yPos) != null && (board.getPiece(x, yPos).getName().equals("Queen") || board.getPiece(x, yPos).getName().equals("Rook")))
                    board.checkPieces.add(board.getPiece(x, yPos));
                else
                    break;
            }
        }
        for (y = yPos - 1; y >= 0; y--) {
            if (board.getBoardPosition(xPos, y) == type)
                break;
            else if (board.getBoardPosition(xPos, y) == opponentType) {
                if (board.getPiece(xPos, y) != null && (board.getPiece(xPos, y).getName().equals("Queen") || board.getPiece(xPos, y).getName().equals("Rook")))
                    board.checkPieces.add(board.getPiece(xPos, y));
                else
                    break;
            }
        }
        for (y = yPos + 1; y < board.getBoardHeight(); y++) {
            if (board.getBoardPosition(xPos, y) == type)
                break;
            else if (board.getBoardPosition(xPos, y) == opponentType) {
                if (board.getPiece(xPos, y) != null && (board.getPiece(xPos, y).getName().equals("Queen") || board.getPiece(xPos, y).getName().equals("Rook")))
                    board.checkPieces.add(board.getPiece(xPos, y));
                else
                    break;
            }
        }
        for (y = yPos - 1, x = xPos - 1; y >= 0 && x >= 0; y--, x--) {
            if (board.getBoardPosition(x, y) == type)
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (y == yPos - 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (type == 1 && board.getPiece(x, y).getName().equals("Pawn")))
                    board.checkPieces.add(board.getPiece(x, y));
                else if (board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    board.checkPieces.add(board.getPiece(x, y));
                else
                    break;
            }
        }
        for (y = yPos + 1, x = xPos + 1; y < board.getBoardHeight() && x < board.getBoardWidth(); y++, x++) {
            if (board.getBoardPosition(x, y) == type)
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (y == yPos + 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (type == 2 && board.getPiece(x, y).getName().equals("Pawn")))
                    board.checkPieces.add(board.getPiece(x, y));
                else if (board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    board.checkPieces.add(board.getPiece(x, y));
                else
                    break;
            }
        }
        for (y = yPos - 1, x = xPos + 1; y >= 0 && x < board.getBoardWidth(); y--, x++) {
            if (board.getBoardPosition(x, y) == type)
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (y == yPos - 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (type == 1 && board.getPiece(x, y).getName().equals("Pawn")))
                    board.checkPieces.add(board.getPiece(x, y));
                else if (board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    board.checkPieces.add(board.getPiece(x, y));
                else
                    break;
            }
        }
        for (y = yPos + 1, x = xPos - 1; y < board.getBoardHeight() && x >= 0; y++, x--) {
            if (board.getBoardPosition(x, y) == type)
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (y == yPos + 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (type == 2 && board.getPiece(x, y).getName().equals("Pawn")))
                    board.checkPieces.add(board.getPiece(x, y));
                if (board.getBoardPosition(x, y) != 0 && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    board.checkPieces.add(board.getPiece(x, y));
                else
                    break;
            }
        }
        for (y = -2; y <= 2; y++) {
            if (y != 0) {
                x = y % 2 == 0 ? 1 : 2;
                if (yPos + y >= 0 && yPos + y < board.getBoardHeight() && xPos - x >= 0 && xPos - x < board.getBoardWidth() && board.getBoardPosition(xPos - x, yPos + y) != type && board.getBoardPosition(xPos - x, yPos + y) != 0) {
                    if (board.getPiece(xPos - x, yPos + y) != null && board.getPiece(xPos - x, yPos + y).getName().equals("Knight"))
                        board.checkPieces.add(board.getPiece(xPos - x, yPos + y));
                }
                if (yPos + y >= 0 && yPos + y < board.getBoardHeight() && xPos + x >= 0 && xPos + x < board.getBoardWidth() && board.getBoardPosition(xPos + x, yPos + y) != type && board.getBoardPosition(xPos + x, yPos + y) != 0) {
                    if (board.getPiece(xPos + x, yPos + y) != null && board.getPiece(xPos + x, yPos + y).getName().equals("Knight"))
                        board.checkPieces.add(board.getPiece(xPos + x, yPos + y));
                }
            }
        }
    }

    public void findAllSaviorPieces(Board board, int xPos, int yPos, int type, boolean protect) {
        int y;
        int x;
        int opponentType;
        if (type == 1)
            opponentType = 2;
        else
            opponentType = 1;

        for (x = xPos - 1; x >= 0; x--) {
            if (board.getBoardPosition(x, yPos) == type)
                break;
            else if (board.getBoardPosition(x, yPos) == opponentType) {
                if (board.getPiece(x, yPos) != null && (board.getPiece(x, yPos).getName().equals("Queen") || board.getPiece(x, yPos).getName().equals("Rook")))
                    board.saviorPieces.add(board.getPiece(x, yPos));
                else
                    break;
            }
        }
        for (x = xPos + 1; x < board.getBoardWidth(); x++) {
            if (board.getBoardPosition(x, yPos) == type)
                break;
            else if (board.getBoardPosition(x, yPos) == opponentType) {
                if (board.getPiece(x, yPos) != null && (board.getPiece(x, yPos).getName().equals("Queen") || board.getPiece(x, yPos).getName().equals("Rook")))
                    board.saviorPieces.add(board.getPiece(x, yPos));
                else
                    break;
            }
        }
        for (y = yPos - 1; y >= 0; y--) {
            if (board.getBoardPosition(xPos, y) == type)
                break;
            else if (board.getBoardPosition(xPos, y) == opponentType) {
                if (opponentType == 2 && protect && y == yPos - 1 && board.getPiece(xPos, y) != null && board.getPiece(xPos, y).getName().equals("Pawn"))
                    board.saviorPieces.add(board.getPiece(xPos, y));
                if (opponentType == 2 && protect && y == yPos - 2 && board.getPiece(xPos, y) != null && board.getPiece(xPos, y).getName().equals("Pawn") && board.getPiece(xPos, y).isFirstTime())
                    board.saviorPieces.add(board.getPiece(xPos, y));
                if (board.getPiece(xPos, y) != null && (board.getPiece(xPos, y).getName().equals("Queen") || board.getPiece(xPos, y).getName().equals("Rook")))
                    board.saviorPieces.add(board.getPiece(xPos, y));
                else
                    break;
            }
        }
        for (y = yPos + 1; y < board.getBoardHeight(); y++) {
            if (board.getBoardPosition(xPos, y) == type)
                break;
            else if (board.getBoardPosition(xPos, y) == opponentType) {
                if (opponentType == 1 && protect && y == yPos + 1 && board.getPiece(xPos, y) != null && board.getPiece(xPos, y).getName().equals("Pawn"))
                    board.saviorPieces.add(board.getPiece(xPos, y));
                if (opponentType == 1 && protect && y == yPos + 2 && board.getPiece(xPos, y) != null && board.getPiece(xPos, y).getName().equals("Pawn") && board.getPiece(xPos, y).isFirstTime())
                    board.saviorPieces.add(board.getPiece(xPos, y));
                if (board.getPiece(xPos, y) != null && (board.getPiece(xPos, y).getName().equals("Queen") || board.getPiece(xPos, y).getName().equals("Rook")))
                    board.saviorPieces.add(board.getPiece(xPos, y));
                else
                    break;
            }
        }
        for (y = yPos - 1, x = xPos - 1; y >= 0 && x >= 0; y--, x--) {
            if (board.getBoardPosition(x, y) == type)
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (!protect && y == yPos - 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (type == 1 && board.getPiece(x, y).getName().equals("Pawn")))
                    board.saviorPieces.add(board.getPiece(x, y));
                if (board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    board.saviorPieces.add(board.getPiece(x, y));
                else
                    break;
            }
        }
        for (y = yPos + 1, x = xPos + 1; y < board.getBoardHeight() && x < board.getBoardWidth(); y++, x++) {
            if (board.getBoardPosition(x, y) == type)
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (!protect && y == yPos + 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (type == 2 && board.getPiece(x, y).getName().equals("Pawn")))
                    board.saviorPieces.add(board.getPiece(x, y));
                if (board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    board.saviorPieces.add(board.getPiece(x, y));
                else
                    break;
            }
        }

        for (y = yPos - 1, x = xPos + 1; y >= 0 && x < board.getBoardWidth(); y--, x++) {
            if (board.getBoardPosition(x, y) == type)
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (!protect && y == yPos - 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (type == 1 && board.getPiece(x, y).getName().equals("Pawn")))
                    board.saviorPieces.add(board.getPiece(x, y));
                if (board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    board.saviorPieces.add(board.getPiece(x, y));
                else
                    break;
            }
        }
        for (y = yPos + 1, x = xPos - 1; y < board.getBoardHeight() && x >= 0; y++, x--) {
            if (board.getBoardPosition(x, y) == type)
                break;
            else if (board.getBoardPosition(x, y) == opponentType) {
                if (!protect && y == yPos + 1 && board.getBoardPosition(x, y) != 0 && board.getPiece(x, y) != null && (type == 2 && board.getPiece(x, y).getName() == "Pawn"))
                    board.saviorPieces.add(board.getPiece(x, y));
                if (board.getBoardPosition(x, y) != 0 && (board.getPiece(x, y).getName().equals("Queen") || board.getPiece(x, y).getName().equals("Bishop")))
                    board.saviorPieces.add(board.getPiece(x, y));
                else
                    break;
            }
        }
        for (y = -2; y <= 2; y++) {
            if (y != 0) {
                x = y % 2 == 0 ? 1 : 2;
                if (yPos + y >= 0 && yPos + y < board.getBoardHeight() && xPos - x >= 0 && xPos - x < board.getBoardWidth() && board.getBoardPosition(xPos - x, yPos + y) != type && board.getBoardPosition(xPos - x, yPos + y) != 0) {
                    if (board.getPiece(xPos - x, yPos + y) != null && board.getPiece(xPos - x, yPos + y).getName().equals("Knight"))
                        board.saviorPieces.add(board.getPiece(xPos - x, yPos + y));
                }
                if (yPos + y >= 0 && yPos + y < board.getBoardHeight() && xPos + x >= 0 && xPos + x < board.getBoardWidth() && board.getBoardPosition(xPos + x, yPos + y) != type && board.getBoardPosition(xPos + x, yPos + y) != 0) {
                    if (board.getPiece(xPos + x, yPos + y) != null && board.getPiece(xPos + x, yPos + y).getName().equals("Knight"))
                        board.saviorPieces.add(board.getPiece(xPos + x, yPos + y));
                }
            }
        }
    }

    public boolean isCheckmate(Board chessboard, int xPos, int yPos, int type) {
        boolean checkmate = true;
        int x;
        int y;
        for (y = yPos - 1; y <= yPos + 1; y++) {
            for (x = xPos - 1; x <= xPos + 1; x++) {
                if (y >= 0 && y < chessboard.getBoardHeight() && x >= 0 && x < chessboard.getBoardWidth() && chessboard.getBoardPosition(x, y) != type) {
                    if (!isCheck(chessboard, x, y, type, true)) {
                        checkmate = false;
                        break;
                    }
                }
            }
            if (!checkmate)
                break;
        }
        if (chessboard.checkPieces.size() < 2) {
            Piece checkPiece = chessboard.checkPieces.get(0);
            canCapture(chessboard, checkPiece);
            canProtect(chessboard, xPos, yPos, checkPiece);
            if (!chessboard.saviorPieces.isEmpty()) {
                for (Iterator<Piece> piece = chessboard.saviorPieces.iterator(); piece.hasNext(); ) {
                    Piece item = piece.next();
                    item.setASavior(true);
                    if (verticalProtection(chessboard, item.getX(), item.getY(), item.getType()) || horizontalProtection(chessboard, item.getX(), item.getY(), item.getType()) ||
                            slashDiagonalProtection(chessboard, item.getX(), item.getY(), item.getType()) || backslashDiagonalProtection(chessboard, item.getX(), item.getY(), item.getType())) {
                        item.setASavior(false);
                        piece.remove();
                    }
                }
            }
            if (!chessboard.saviorPieces.isEmpty())
                checkmate = false;
        }
        return (checkmate);
    }

    public void canCapture(Board chessboard, Piece checkPiece) {
        findAllSaviorPieces(chessboard, checkPiece.getX(), checkPiece.getY(), checkPiece.getType(), false);
    }

    public void canProtect(Board chessboard, int xPos, int yPos, Piece checkPiece) {
        if (checkPiece.getName().equals("Knight") || checkPiece.getName().equals("Pawn"))
            return;
        if (xPos == checkPiece.getX() && yPos > checkPiece.getY())
            for (int y = checkPiece.getY() + 1; y < yPos; y++)
                findAllSaviorPieces(chessboard, checkPiece.getX(), y, checkPiece.getType(), true);
        if (xPos == checkPiece.getX() && yPos < checkPiece.getY())
            for (int y = checkPiece.getY() - 1; y > yPos; y--)
                findAllSaviorPieces(chessboard, checkPiece.getX(), y, checkPiece.getType(), true);
        if (xPos > checkPiece.getX() && yPos == checkPiece.getY())
            for (int x = checkPiece.getX() + 1; x < xPos; x++)
                findAllSaviorPieces(chessboard, x, checkPiece.getY(), checkPiece.getType(), true);
        if (xPos < checkPiece.getX() && yPos == checkPiece.getY())
            for (int x = checkPiece.getX() - 1; x > xPos; x--)
                findAllSaviorPieces(chessboard, x, checkPiece.getY(), checkPiece.getType(), true);
        int y = checkPiece.getY() + 1;
        if (xPos > checkPiece.getX() && yPos > checkPiece.getY())
            for (int x = checkPiece.getX() + 1; x < xPos && y < yPos; x++, y++)
                findAllSaviorPieces(chessboard, x, y, checkPiece.getType(), true);
        y = checkPiece.getY() - 1;
        if (xPos < checkPiece.getX() && yPos < checkPiece.getY())
            for (int x = checkPiece.getX() - 1; x > xPos && y > yPos; x--, y--)
                findAllSaviorPieces(chessboard, x, y, checkPiece.getType(), true);
        y = checkPiece.getY() + 1;
        if (xPos < checkPiece.getX() && yPos > checkPiece.getY())
            for (int x = checkPiece.getX() - 1; x > xPos && y < yPos; x--, y++)
                findAllSaviorPieces(chessboard, x, y, checkPiece.getType(), true);
        y = checkPiece.getY() - 1;
        if (xPos > checkPiece.getX() && yPos < checkPiece.getY())
            for (int x = checkPiece.getX() + 1; x < xPos && y > yPos; x++, y--)
                findAllSaviorPieces(chessboard, x, y, checkPiece.getType(), true);
    }

    public boolean isThisProtecting(Board chessboard, int xPos, int yPos, int type) {
        Piece checkPiece = chessboard.checkPieces.get(0);
        if (chessboard.getKing(type).getX() == checkPiece.getX() && chessboard.getKing(type).getY() > checkPiece.getY())
            if (xPos == chessboard.getKing(type).getX() && yPos < chessboard.getKing(type).getY() && yPos > checkPiece.getY())
                return (true);
        if (chessboard.getKing(type).getX() == checkPiece.getX() && chessboard.getKing(type).getY() < checkPiece.getY())
            if (xPos == chessboard.getKing(type).getX() && yPos > chessboard.getKing(type).getY() && yPos < checkPiece.getY())
                return (true);
        if (chessboard.getKing(type).getX() > checkPiece.getX() && chessboard.getKing(type).getY() == checkPiece.getY())
            if (yPos == chessboard.getKing(type).getY() && xPos < chessboard.getKing(type).getX() && xPos > checkPiece.getX())
                return (true);
        if (chessboard.getKing(type).getX() < checkPiece.getX() && chessboard.getKing(type).getY() == checkPiece.getY())
            if (yPos == chessboard.getKing(type).getY() && xPos > chessboard.getKing(type).getX() && xPos < checkPiece.getX())
                return (true);
        int y = checkPiece.getY();
        if (chessboard.getKing(type).getX() > checkPiece.getX() && chessboard.getKing(type).getY() > checkPiece.getY())
            for (int x = checkPiece.getX(); x < chessboard.getKing(type).getX() && y < chessboard.getKing(type).getY(); x++, y++)
                if (xPos == x && yPos == y)
                    return (true);
        y = checkPiece.getY();
        if (chessboard.getKing(type).getX() < checkPiece.getX() && chessboard.getKing(type).getY() < checkPiece.getY())
            for (int x = checkPiece.getX(); x > chessboard.getKing(type).getX() && y > chessboard.getKing(type).getY(); x--, y--)
                if (xPos == x && yPos == y)
                    return (true);
        y = checkPiece.getY();
        if (chessboard.getKing(type).getX() < checkPiece.getX() && chessboard.getKing(type).getY() > checkPiece.getY())
            for (int x = checkPiece.getX(); x > chessboard.getKing(type).getX() && y < chessboard.getKing(type).getY(); x--, y++)
                if (xPos == x && yPos == y)
                    return (true);
        y = checkPiece.getY();
        if (chessboard.getKing(type).getX() > checkPiece.getX() && chessboard.getKing(type).getY() < checkPiece.getY())
            for (int x = checkPiece.getX(); x < chessboard.getKing(type).getX() && y > chessboard.getKing(type).getY(); x++, y--)
                if (xPos == x && yPos == y)
                    return (true);
        return (false);
    }
}
