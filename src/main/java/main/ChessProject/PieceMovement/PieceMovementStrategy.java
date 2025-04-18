package main.ChessProject.PieceMovement;

import main.ChessProject.Models.Board;
import main.ChessProject.Models.Square;

import java.util.List;

public interface PieceMovementStrategy {
    List<Square> getAllowedMoves(Board board);
}
