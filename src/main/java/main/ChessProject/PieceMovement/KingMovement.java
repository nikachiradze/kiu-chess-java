package main.ChessProject.PieceMovement;

import main.ChessProject.Models.Board;
import main.ChessProject.Models.Piece;
import main.ChessProject.Models.Square;

import java.util.LinkedList;
import java.util.List;

public class KingMovement implements PieceMovementStrategy {

    private Piece king;

    public KingMovement(Piece king) {
        this.king = king;
    }


    @Override
    public List<Square> getAllowedMoves(Board board) {
        LinkedList<Square> allowedMoves = new LinkedList<Square>();

        Square[][] chessBoard = board.getSquareArray();

        Square position = king.getPosition();
        int x = position.getXNum();
        int y = position.getYNum();


        for (int i = 1; i > -2; i--) {
            for (int k = 1; k > -2; k--) {
                if (!(i == 0 && k == 0)) {
                    try {
                        if (!chessBoard[y + k][x + i].isOccupied() ||
                                chessBoard[y + k][x + i].getOccupyingPiece().getColor()
                                        != king.getColor()) {
                            allowedMoves.add(chessBoard[y + k][x + i]);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
        }

        return allowedMoves;
    }
}
