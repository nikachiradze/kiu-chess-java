package main.ChessProject.PieceMovement;

import main.ChessProject.Helper.MovementHelper;
import main.ChessProject.Models.Board;
import main.ChessProject.Models.Piece;
import main.ChessProject.Models.Square;

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
