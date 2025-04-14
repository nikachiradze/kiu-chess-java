package ChessProject.Controllers;

import ChessProject.Models.Board;
import ChessProject.Models.CheckmateDetector;
import ChessProject.Models.Piece;
import ChessProject.Models.Square;
import ChessProject.Views.GameWindow;

import java.util.List;

public class BoardController {
    private Board board;
    private GameWindow gameWindow;


    public BoardController(Board b, GameWindow gw) {
        this.gameWindow = gw;
        this.board = b;
    }

    public void mousePressHandler(Square sq) {


        if (sq.isOccupied()) {
            Piece piece = sq.getOccupyingPiece();

            if (piece.getColor() == 0 && this.board.getTurn())
                return;
            if (piece.getColor() == 1 && !this.board.getTurn())
                return;
            this.board.setCurrPiece(piece);
            sq.setDisplay(false);
        }
    }

    public void mouseReleaseHandler(Square sq) {
        Piece currPiece = board.getCurrPiece();
        if (currPiece == null) return;

        List<Square> legalMoves = currPiece.getLegalMoves(board);
        List<Square> allowed = board.getCheckmateDetector().getAllowableSquares(board.getTurn());

        if (legalMoves.contains(sq) &&
                allowed.contains(sq) &&
                board.getCheckmateDetector().testMove(currPiece, sq)) {

            currPiece.move(sq);
            board.getCheckmateDetector().update();

            if (board.getCheckmateDetector().blackCheckMated()) {
                gameWindow.checkmateOccurred(0);
            } else if (board.getCheckmateDetector().whiteCheckMated()) {
                gameWindow.checkmateOccurred(1);
            } else {
                board.setTurn(!board.getTurn());
            }
        } else {
            currPiece.getPosition().setDisplay(true);
        }

        board.setCurrPiece(null);
    }


}
