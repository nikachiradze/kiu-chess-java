package ChessProject.PieceMovement;

import ChessProject.Helper.MovementHelper;
import ChessProject.Models.Board;
import ChessProject.Models.Piece;
import ChessProject.Models.Square;

import java.util.ArrayList;
import java.util.List;

public class BishopMovement implements PieceMovementStrategy {

    private Piece bishop;

    public BishopMovement(Piece bishop) {
        this.bishop = bishop;
    }


    @Override
    public List<Square> getAllowedMoves(Board board) {
        return MovementHelper.getDiagonalMoves(board, bishop);
    }
}
