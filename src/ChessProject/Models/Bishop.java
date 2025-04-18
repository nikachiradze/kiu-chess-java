package ChessProject.Models;

import ChessProject.Models.Enum.Pieces;
import ChessProject.PieceMovement.MovementStrategyFactory;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(int color, Square initSq, String img_file) {
        super(color, initSq, img_file);
    }

    @Override
    public List<Square> getLegalMoves(Board b) {
        return new MovementStrategyFactory(Pieces.BISHOP, this).strategy.getAllowedMoves(b);
    }
}
