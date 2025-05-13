package main.ChessProject.Models;

import main.ChessProject.Models.Enum.Pieces;
import main.ChessProject.PieceMovement.MovementStrategyFactory;

import java.util.List;

public class Rook extends Piece {

    public Rook(int color, Square initSq) {
        super(color, initSq);
    }

    @Override
    public List<Square> getLegalMoves(Board b) {
        return new MovementStrategyFactory(Pieces.ROOK, this).strategy.getAllowedMoves(b);
    }

}
