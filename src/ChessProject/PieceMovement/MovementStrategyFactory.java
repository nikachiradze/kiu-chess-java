package ChessProject.PieceMovement;

import ChessProject.Models.Enum.Pieces;
import ChessProject.Models.Piece;

public class MovementStrategyFactory {

    public PieceMovementStrategy strategy;

    public MovementStrategyFactory(Pieces pieceType, Piece piece) {
        if (pieceType == Pieces.KING) {
            this.strategy = new KingMovement(piece);
        }
        if (pieceType == Pieces.PAWN) {
            this.strategy = new PawnMovement(piece, false);
        }
        if (pieceType == Pieces.KNIGHT) {
            this.strategy = new KnightMovement(piece);
        }
        if (pieceType == Pieces.QUEEN) {
            this.strategy = new QueenMovement(piece);
        }
        if (pieceType == Pieces.BISHOP) {
            this.strategy = new BishopMovement(piece);
        }
        if (pieceType == Pieces.ROOK) {
            this.strategy = new RookMovement(piece);
        }
    }
}
