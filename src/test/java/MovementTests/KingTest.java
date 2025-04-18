package MovementTests;

import main.ChessProject.Models.*;
import main.ChessProject.PieceMovement.KingMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KingTest {

    private Board board;
    private Piece king;
    private Square kingSquare;
    private KingMovement kingMovement;

    @BeforeEach
    void setup() {
        board = new Board();
        Square[][] squareArray = board.getSquareArray();
        for (Square[] row : squareArray) {
            for (Square square : row) {
                square.removePiece();
            }
        }
        king = new King(0, squareArray[0][0], "/bking.png");
        kingSquare = board.getSquareArray()[0][0];
        king.setPosition(kingSquare);
        kingMovement = new KingMovement(king);
    }

    @Test
    void testKingMovementFromCorner() {
        List<Square> moves = kingMovement.getAllowedMoves(board);
        assertEquals(3, moves.size()); // Only 3 moves from (0,0)
    }

    @Test
    void testKingMovesFromCenter() {
        Square center = board.getSquareArray()[4][4];
        king.setPosition(center);
        center.setPiece(king);

        KingMovement kingMovement = new KingMovement(king);
        List<Square> moves = kingMovement.getAllowedMoves(board);

        assertEquals(8, moves.size()); // all directions open
    }

    @Test
    void testKingBlockedByFriendlyPieces() {
        Square center = board.getSquareArray()[4][4];
        king.setPosition(center);
        center.setPiece(king);

        // Surround king with friendly pieces
        int[][] deltas = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] d : deltas) {
            int newY = 4 + d[0];
            int newX = 4 + d[1];
            Square square = board.getSquareArray()[newY][newX];
            Piece pawn = new Pawn(king.getColor(), square, "/wpawn.png");
            square.setPiece(pawn);
            pawn.setPosition(square);
        }

        List<Square> moves = kingMovement.getAllowedMoves(board);
        assertEquals(0, moves.size()); // All blocked by friendly pieces
    }

    @Test
    void testKingWithEnemyPiecesAround() {
        Square center = board.getSquareArray()[4][4];
        king.setPosition(center);
        center.setPiece(king);

        // Surround king with enemy pieces
        int[][] deltas = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] d : deltas) {
            int newY = 4 + d[0];
            int newX = 4 + d[1];
            Square square = board.getSquareArray()[newY][newX];
            Piece pawn = new Pawn(1, square, "/bpawn.png");
            square.setPiece(pawn);
            pawn.setPosition(square);
        }

        List<Square> moves = kingMovement.getAllowedMoves(board);
        assertEquals(8, moves.size());
    }

    @Test
    void testKingNextToEdgeButNotCorner() {
        Square edge = board.getSquareArray()[0][4];
        king.setPosition(edge);
        edge.setPiece(king);

        List<Square> moves = kingMovement.getAllowedMoves(board);
        assertEquals(5, moves.size());
    }


}
