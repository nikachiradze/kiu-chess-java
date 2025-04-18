package ChessProject.PieceMovement;

import ChessProject.Models.Board;
import ChessProject.Models.Square;

import java.util.List;

public interface PieceMovementStrategy {
    List<Square> getAllowedMoves(Board board);
}
