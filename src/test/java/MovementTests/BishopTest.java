package MovementTests;

import main.ChessProject.Models.*;
import main.ChessProject.PieceMovement.BishopMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BishopTest {

    private Board board;
    private Piece bishop;
    private Square bishopSquare;
    private BishopMovement bishopMovement;

    @BeforeEach
    void setup() {
        board = new Board();
        Square[][] squareArray = board.getSquareArray();
        for (Square[] row : squareArray) {
            for (Square square : row) {
                square.removePiece();
            }
        }
        bishop = new Bishop(0, squareArray[0][0]);
        bishopSquare = board.getSquareArray()[0][0];
        bishop.setPosition(bishopSquare);
        bishopMovement = new BishopMovement(bishop);
    }

    @Test
    void testBishopMovementFromCenter() {
        // Bishop at d4 (3,3)
        bishopSquare = board.getSquareArray()[3][3];
        bishop.setPosition(bishopSquare);
        bishopMovement = new BishopMovement(bishop);

        List<Square> moves = bishopMovement.getAllowedMoves(board);
        assertEquals(13, moves.size()); // 4 diagonal directions: max 7 moves in each minus overlaps
    }

    @Test
    void testBishopMovementFromEdge() {
        // Bishop at a4 (0,3)
        bishopSquare = board.getSquareArray()[0][3];
        bishop.setPosition(bishopSquare);
        bishopMovement = new BishopMovement(bishop);

        List<Square> moves = bishopMovement.getAllowedMoves(board);
        assertEquals(7, moves.size());
    }

    @Test
    void testBishopBlockedByFriendlyPiece() {
        // Bishop at d4 (3,3)
        bishopSquare = board.getSquareArray()[3][3];
        bishop.setPosition(bishopSquare);

        // Friendly piece at f6 (5,5) — same diagonal
        Piece friendly = new Pawn(0, board.getSquareArray()[5][5]);
        board.getSquareArray()[5][5].setPiece(friendly);

        bishopMovement = new BishopMovement(bishop);
        List<Square> moves = bishopMovement.getAllowedMoves(board);

        // Should be blocked before f6
        assertEquals(false, moves.contains(board.getSquareArray()[5][5]));
    }

    @Test
    void testBishopCanCaptureEnemy() {
        // Bishop at d4 (3,3)
        bishopSquare = board.getSquareArray()[3][3];
        bishop.setPosition(bishopSquare);

        // Enemy piece at f6 (5,5)
        Piece enemy = new Pawn(1, board.getSquareArray()[5][5]);
        board.getSquareArray()[5][5].setPiece(enemy);

        bishopMovement = new BishopMovement(bishop);
        List<Square> moves = bishopMovement.getAllowedMoves(board);

        // Should include f6 (5,5)
        assertEquals(true, moves.contains(board.getSquareArray()[5][5]));
    }

    @Test
    void testBishopSurroundedByPieces() {
        // Bishop at d4 (3,3)
        bishopSquare = board.getSquareArray()[3][3];
        bishop.setPosition(bishopSquare);

        // Friendly pieces on all diagonals
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] d : directions) {
            int x = 3 + d[0];
            int y = 3 + d[1];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                board.getSquareArray()[x][y].setPiece(new Pawn(0, board.getSquareArray()[x][y]));
            }
        }

        bishopMovement = new BishopMovement(bishop);
        List<Square> moves = bishopMovement.getAllowedMoves(board);

        assertEquals(0, moves.size());
    }


    @Test
    void testBishopMovementFromCorner() {
        List<Square> moves = bishopMovement.getAllowedMoves(board);
        assertEquals(7, moves.size());
    }
}
