package main.ChessProject.Models;

import main.ChessProject.Models.Enum.Pieces;
import main.ChessProject.PieceMovement.MovementStrategyFactory;

import java.util.List;

public class Queen extends Piece {

    public Queen(int color, Square initSq, String img_file) {
        super(color, initSq, img_file);
    }

    @Override
    public List<Square> getLegalMoves(Board b) {
        return new MovementStrategyFactory(Pieces.QUEEN, this).strategy.getAllowedMoves(b);
    }

}
