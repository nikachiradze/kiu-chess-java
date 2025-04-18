package ChessProject.Models;

import ChessProject.Models.Enum.Pieces;
import ChessProject.PieceMovement.MovementStrategyFactory;

import java.util.LinkedList;
import java.util.List;

public class Knight extends Piece {

    public Knight(int color, Square initSq, String img_file) {
        super(color, initSq, img_file);
    }

    @Override
    public List<Square> getLegalMoves(Board b) {
        return new MovementStrategyFactory(Pieces.KNIGHT, this).strategy.getAllowedMoves(b);
    }

}
