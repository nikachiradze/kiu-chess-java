package main.ChessProject.PieceMovement;

import main.ChessProject.Helper.MovementHelper;
import main.ChessProject.Models.Board;
import main.ChessProject.Models.Piece;
import main.ChessProject.Models.Square;

import java.util.List;

public class QueenMovement implements PieceMovementStrategy {

    private Piece queen;

    public QueenMovement(Piece queen) {
        this.queen = queen;

    }

    @Override
    public List<Square> getAllowedMoves(Board board) {
        return MovementHelper.getCombinedMoves(board, queen);
    }
}
