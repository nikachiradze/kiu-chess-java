package MovementTests;

import main.ChessProject.Models.*;
import main.ChessProject.PieceMovement.QueenMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenTest {

    private Board board;
    private Piece queen;
    private Square queenSquare;
    private QueenMovement queenMovement;

    @BeforeEach
    void setup() {
        board = new Board();
        Square[][] squareArray = board.getSquareArray();
        for (int x = 0; x < squareArray.length; x++) {
            for (int y = 0; y < squareArray[x].length; y++) {
                squareArray[x][y].removePiece();
            }
        }
        queen = new Queen(0, squareArray[0][0], "/brook.png");
        queenSquare = board.getSquareArray()[0][0];
        queen.setPosition(queenSquare);


        queenMovement = new QueenMovement(queen);
    }

    @Test
    void testRookMovementFromCorner() {
        List<Square> moves = queenMovement.getAllowedMoves(board);
        assertEquals(21, moves.size());
    }

    @Test
    void testQueenCenterOfBoard() {
        queenSquare = board.getSquareArray()[3][3];
        queen.setPosition(queenSquare);

        List<Square> moves = new QueenMovement(queen).getAllowedMoves(board);
        assertEquals(27, moves.size());
    }

    @Test
    void testQueenBlockedByOwnPiece() {
        // Place queen at d4
        queenSquare = board.getSquareArray()[3][3];
        queen.setPosition(queenSquare);

        Piece friendlyPiece = new Pawn(0, board.getSquareArray()[5][3], "/bpawn.png");
        board.getSquareArray()[5][3].setPiece(friendlyPiece);

        List<Square> moves = new QueenMovement(queen).getAllowedMoves(board);

        assertEquals(24, moves.size());
        assertEquals(false, moves.contains(board.getSquareArray()[5][3]));
    }

    @Test
    void testQueenCanCaptureEnemy() {
        queenSquare = board.getSquareArray()[3][3];
        queen.setPosition(queenSquare);

        Piece enemyPiece = new Pawn(1, board.getSquareArray()[5][5], "/bpawn.png");
        board.getSquareArray()[5][5].setPiece(enemyPiece);

        List<Square> moves = new QueenMovement(queen).getAllowedMoves(board);

        assertEquals(true, moves.contains(board.getSquareArray()[5][5]));
    }

    @Test
    void testQueenSurroundedByFriendlyPieces() {
        // Place queen at d4
        queenSquare = board.getSquareArray()[3][3];
        queen.setPosition(queenSquare);

        // Surround with friendly pieces
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };
        for (int[] dir : directions) {
            int x = 3 + dir[0];
            int y = 3 + dir[1];
            Piece p = new Pawn(0, board.getSquareArray()[x][y], "/bpawn.png");
            board.getSquareArray()[x][y].setPiece(p);
        }

        List<Square> moves = new QueenMovement(queen).getAllowedMoves(board);

        assertEquals(0, moves.size());
    }

    @Test
    void testQueenTrappedBetweenEnemyAndFriendly() {
        queenSquare = board.getSquareArray()[3][3];
        queen.setPosition(queenSquare);

        Piece friendly = new Pawn(0, board.getSquareArray()[4][3], "/bpawn.png");
        board.getSquareArray()[4][3].setPiece(friendly);

        Piece enemy = new Pawn(1, board.getSquareArray()[2][3], "/bpawn.png");
        board.getSquareArray()[2][3].setPiece(enemy);

        List<Square> moves = new QueenMovement(queen).getAllowedMoves(board);

        assertEquals(true, moves.contains(board.getSquareArray()[2][3]));
        assertEquals(false, moves.contains(board.getSquareArray()[4][3]));
    }

    @Test
    void testQueenAtEdgeOfBoard() {
        queenSquare = board.getSquareArray()[7][7];
        queen.setPosition(queenSquare);

        List<Square> moves = new QueenMovement(queen).getAllowedMoves(board);
        assertEquals(21, moves.size());
    }


}
