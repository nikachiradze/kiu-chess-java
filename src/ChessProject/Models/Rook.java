package ChessProject.Models;

import ChessProject.Models.Enum.Pieces;
import ChessProject.PieceMovement.MovementStrategyFactory;

import java.util.LinkedList;
import java.util.List;

public class Rook extends Piece {

    public Rook(int color, Square initSq, String img_file) {
        super(color, initSq, img_file);
    }

    @Override
    public List<Square> getLegalMoves(Board b) {
        return new MovementStrategyFactory(Pieces.ROOK, this).strategy.getAllowedMoves(b);
    }

}
