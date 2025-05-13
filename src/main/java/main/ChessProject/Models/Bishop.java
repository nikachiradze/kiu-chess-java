package main.ChessProject.Models;

import main.ChessProject.Models.Enum.Pieces;
import main.ChessProject.PieceMovement.MovementStrategyFactory;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(int color, Square initSq) {
        super(color, initSq);
    }

    @Override
    public List<Square> getLegalMoves(Board b) {
        return new MovementStrategyFactory(Pieces.BISHOP, this).strategy.getAllowedMoves(b);
    }
}
