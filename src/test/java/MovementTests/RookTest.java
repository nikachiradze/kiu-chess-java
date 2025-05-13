package MovementTests;

import main.ChessProject.Models.Board;
import main.ChessProject.Models.Piece;
import main.ChessProject.Models.Rook;
import main.ChessProject.Models.Square;
import main.ChessProject.PieceMovement.RookMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RookTest {

    private Board board;
    private Square[][] squareArray;

    @BeforeEach
    void setup() {
        board = new Board();
        squareArray = board.getSquareArray();
        for (int x = 0; x < squareArray.length; x++) {
            for (int y = 0; y < squareArray[x].length; y++) {
                squareArray[x][y].removePiece();
            }
        }
    }

    @Test
    void testRookMovementFromCorner() {
        Piece rook = new Rook(0, squareArray[0][0]);
        rook.setPosition(squareArray[0][0]);
        RookMovement rookMovement = new RookMovement(rook);

        List<Square> moves = rookMovement.getAllowedMoves(board);
        assertEquals(14, moves.size());
    }

    @Test
    void testRookBlockedByAllies() {
        Piece rook = new Rook(0, squareArray[0][0]);
        rook.setPosition(squareArray[0][0]);
        squareArray[0][1].setPiece(new Rook(0, squareArray[0][1])); // same team
        squareArray[1][0].setPiece(new Rook(0, squareArray[1][0])); // same team

        RookMovement rookMovement = new RookMovement(rook);
        List<Square> moves = rookMovement.getAllowedMoves(board);
        assertEquals(0, moves.size());
    }

    @Test
    void testRookCanCaptureEnemies() {
        Piece rook = new Rook(0, squareArray[0][0]);
        rook.setPosition(squareArray[0][0]);
        squareArray[0][3].setPiece(new Rook(1, squareArray[0][3])); // enemy
        squareArray[4][0].setPiece(new Rook(1, squareArray[4][0])); // enemy

        RookMovement rookMovement = new RookMovement(rook);
        List<Square> moves = rookMovement.getAllowedMoves(board);
        assertEquals(7, moves.size());
    }

    @Test
    void testRookFromCenter() {
        Piece rook = new Rook(0, squareArray[4][4]);
        rook.setPosition(squareArray[4][4]);

        RookMovement rookMovement = new RookMovement(rook);
        List<Square> moves = rookMovement.getAllowedMoves(board);
        assertEquals(14, moves.size());
    }

    @Test
    void testRookSurroundedByAllies() {
        Piece rook = new Rook(0, squareArray[4][4]);
        rook.setPosition(squareArray[4][4]);
        squareArray[3][4].setPiece(new Rook(0, squareArray[3][4]));
        squareArray[5][4].setPiece(new Rook(0, squareArray[5][4]));
        squareArray[4][3].setPiece(new Rook(0, squareArray[4][3]));
        squareArray[4][5].setPiece(new Rook(0, squareArray[4][5]));

        RookMovement rookMovement = new RookMovement(rook);
        List<Square> moves = rookMovement.getAllowedMoves(board);
        assertEquals(0, moves.size());
    }
}
