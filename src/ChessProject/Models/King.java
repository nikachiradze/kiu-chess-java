package ChessProject.Models;

import ChessProject.Models.Enum.Pieces;
import ChessProject.PieceMovement.MovementStrategyFactory;

import java.util.LinkedList;
import java.util.List;

public class King extends Piece {


    public King(int color, Square initSq, String img_file) {
        super(color, initSq, img_file);
    }

    @Override
    public List<Square> getLegalMoves(Board b) {
        return new MovementStrategyFactory(Pieces.KING, this).strategy.getAllowedMoves(b);
    }

}
