package main.ChessProject.Models;

import main.ChessProject.Models.Enum.Pieces;
import main.ChessProject.PieceMovement.MovementStrategyFactory;

import java.util.List;

public class Knight extends Piece {

    public Knight(int color, Square initSq) {
        super(color, initSq);
    }

    @Override
    public List<Square> getLegalMoves(Board b) {
        return new MovementStrategyFactory(Pieces.KNIGHT, this).strategy.getAllowedMoves(b);
    }

}
