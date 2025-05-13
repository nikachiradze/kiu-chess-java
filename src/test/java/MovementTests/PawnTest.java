package MovementTests;

import main.ChessProject.Models.Board;
import main.ChessProject.Models.Pawn;
import main.ChessProject.Models.Piece;
import main.ChessProject.Models.Square;
import main.ChessProject.PieceMovement.PawnMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {

    private Board board;
    private Square pawnSquare;

    @BeforeEach
    void setup() {
        board = new Board();
        Square[][] squareArray = board.getSquareArray();
        for (Square[] row : squareArray) {
            for (Square square : row) {
                square.removePiece();
            }
        }
        pawnSquare = board.getSquareArray()[1][0];
    }

    @Test
    void testPawnInitialMove() {
        Piece pawn = new Pawn(0, pawnSquare);
        pawn.setPosition(pawnSquare);
        PawnMovement pawnMovement = new PawnMovement(pawn, false);
        List<Square> moves = pawnMovement.getAllowedMoves(board);
        assertEquals(2, moves.size());
    }

    @Test
    void testPawnAfterFirstMove() {
        Piece pawn = new Pawn(0, pawnSquare);
        pawn.setPosition(pawnSquare);
        PawnMovement pawnMovement = new PawnMovement(pawn, true);
        List<Square> moves = pawnMovement.getAllowedMoves(board);
        assertEquals(1, moves.size());
    }
}
