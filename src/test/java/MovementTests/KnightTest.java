package MovementTests;

import main.ChessProject.Models.*;
import main.ChessProject.PieceMovement.KnightMovement;
import main.ChessProject.PieceMovement.QueenMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.ChessProject.Models.Knight;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightTest {

    private Board board;
    private Piece knight;
    private Square queenSquare;
    private KnightMovement knightMovement;

    @BeforeEach
    void setup() {
        board = new Board();
        Square[][] squareArray = board.getSquareArray();
        for (int x = 0; x < squareArray.length; x++) {
            for (int y = 0; y < squareArray[x].length; y++) {
                squareArray[x][y].removePiece();
            }
        }
        knight = new Knight(0, squareArray[0][0], "/brook.png");
        queenSquare = board.getSquareArray()[0][0];
        knight.setPosition(queenSquare);


        knightMovement = new KnightMovement(knight);
    }

    @Test
    void testKnightFromCenter() {
        knight.setPosition(board.getSquareArray()[3][3]);
        knightMovement = new KnightMovement(knight);

        List<Square> moves = knightMovement.getAllowedMoves(board);
        assertEquals(8, moves.size());
    }

    @Test
    void testKnightFromEdge() {
        knight.setPosition(board.getSquareArray()[0][3]);
        knightMovement = new KnightMovement(knight);

        List<Square> moves = knightMovement.getAllowedMoves(board);
        assertEquals(4, moves.size());
    }

    @Test
    void testKnightFromCorner() {
        knight.setPosition(board.getSquareArray()[7][7]);
        knightMovement = new KnightMovement(knight);

        List<Square> moves = knightMovement.getAllowedMoves(board);
        assertEquals(2, moves.size());
    }

    @Test
    void testKnightBlockedByFriendlyPieces() {
        knight.setPosition(board.getSquareArray()[3][3]);

        int[][] knightMoves = {
                {1, 2}, {2, 1}, {-1, 2}, {-2, 1},
                {-1, -2}, {-2, -1}, {1, -2}, {2, -1}
        };
        for (int[] move : knightMoves) {
            int x = 3 + move[0];
            int y = 3 + move[1];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                board.getSquareArray()[x][y].setPiece(new Pawn(0, board.getSquareArray()[x][y], "/bpawn.png"));
            }
        }

        knightMovement = new KnightMovement(knight);
        List<Square> moves = knightMovement.getAllowedMoves(board);
        // Should be 0 since all are blocked by friendly pieces
        assertEquals(0, moves.size());
    }

    @Test
    void testKnightCanCaptureEnemies() {
        knight.setPosition(board.getSquareArray()[3][3]);

        int[][] positions = {
                {5, 4}, {1, 2}
        };
        for (int[] pos : positions) {
            board.getSquareArray()[pos[0]][pos[1]].setPiece(new Pawn(1, board.getSquareArray()[pos[0]][pos[1]], "/bpawn.png"));
        }

        knightMovement = new KnightMovement(knight);
        List<Square> moves = knightMovement.getAllowedMoves(board);

        assertEquals(true, moves.contains(board.getSquareArray()[5][4]));
        assertEquals(true, moves.contains(board.getSquareArray()[1][2]));
    }


    @Test
    void testRookMovementFromCorner() {
        List<Square> moves = knightMovement.getAllowedMoves(board);
        assertEquals(2, moves.size());
    }


}
