package ChessProject.Views;

import ChessProject.Models.Board;
import ChessProject.Models.CheckmateDetector;
import ChessProject.Models.Piece;
import ChessProject.Models.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

public class PaintBoard extends JPanel implements MouseListener, MouseMotionListener {
    Board board;


    public PaintBoard(Board board) {
        this.board = board;

        setLayout(new GridLayout(8, 8, 0, 0));
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                int xMod = x % 2;
                int yMod = y % 2;

                Square[][] squares = this.board.getSquareArray();

                if ((xMod == 0 && yMod == 0) || (xMod == 1 && yMod == 1)) {
                    this.add(new PaintSquare(squares[x][y]));
                } else {
                    this.add(new PaintSquare(squares[x][y]));
                }
            }
        }

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square[][] squares = this.board.getSquareArray();
                Square sq = squares[y][x];
                PaintSquare paintSquare = new PaintSquare(sq);
                paintSquare.paintComponent(g);

            }
        }

        Piece currPiece = this.board.getCurrPiece();

        if (currPiece != null) {
            if ((currPiece.getColor() == 1 && this.board.getTurn())
                    || (currPiece.getColor() == 0 && this.board.getTurn())) {
                final Image i = currPiece.getImage();
                g.drawImage(i, this.board.getCurrX(), this.board.getCurrY(), null);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.board.setCurrX(e.getX());
        this.board.setCurrY(e.getY());


        PaintSquare ps = (PaintSquare) this.getComponentAt(new Point(e.getX(), e.getY()));
        Square sq = ps.getSquare();

        if (sq.isOccupied()) {
            this.board.setCurrPiece(sq.getOccupyingPiece());
            if (this.board.getCurrPiece().getColor() == 0 && this.board.getTurn())
                return;
            if (this.board.getCurrPiece().getColor() == 1 && !this.board.getTurn())
                return;
            sq.setDisplay(false);
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PaintSquare ps = (PaintSquare) this.getComponentAt(new Point(e.getX(), e.getY()));
        Square sq = ps.getSquare();
        Piece currPiece = this.board.getCurrPiece();
        boolean whiteTurn = this.board.getTurn();
        GameWindow g = this.board.getGameWindow();
        if (this.board.getCurrPiece() != null) {
            if (currPiece.getColor() == 0 && whiteTurn)
                return;
            if (currPiece.getColor() == 1 && !whiteTurn)
                return;

            List<Square> legalMoves = currPiece.getLegalMoves(this.board);
            CheckmateDetector cmd = this.board.getCheckmateDetector();
            this.board.setMovableSquares(cmd.getAllowableSquares(whiteTurn));


            if (legalMoves.contains(sq) && this.board.getMovableSquares().contains(sq)
                    && cmd.testMove(currPiece, sq)) {
                sq.setDisplay(true);
                currPiece.move(sq);
                cmd.update();

                if (cmd.blackCheckMated()) {
                    this.board.setCurrPiece(null);
                    repaint();
                    this.removeMouseListener(this);
                    this.removeMouseMotionListener(this);
                    g.checkmateOccurred(0);
                } else if (cmd.whiteCheckMated()) {
                    this.board.setCurrPiece(null);
                    repaint();
                    this.removeMouseListener(this);
                    this.removeMouseMotionListener(this);
                    g.checkmateOccurred(1);
                } else {
                    this.board.setCurrPiece(null);
                    this.board.setTurn(!whiteTurn);
                    this.board.setMovableSquares(cmd.getAllowableSquares(whiteTurn));
                }

            } else {
                currPiece.getPosition().setDisplay(true);
                this.board.setCurrPiece(currPiece);
            }
        }

        repaint();
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        this.board.setCurrX(e.getX() - 24);
        this.board.setCurrY(e.getY() - 24);

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
