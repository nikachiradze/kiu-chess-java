package main.ChessProject.Views;

import main.ChessProject.Controllers.BoardController;
import main.ChessProject.Helper.MouseHelper;
import main.ChessProject.Models.Board;
import main.ChessProject.Models.Piece;
import main.ChessProject.Models.Square;

import javax.swing.*;
import java.awt.*;

public class PaintBoard extends JPanel {
    Board board;

    private int currX, currY;

    private BoardController boardController;

    public PaintBoard(Board board, GameWindow gw) {
        this.board = board;
        this.boardController = new BoardController(board, gw);

        MouseHelper mouseHelper = new MouseHelper(boardController, this);
        addMouseListener(mouseHelper);
        addMouseMotionListener(mouseHelper);

        setLayout(new GridLayout(8, 8, 0, 0));
//        this.addMouseListener(this);
//        this.addMouseMotionListener(this);


        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));
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

    }

    public void setCurrX(int currX) {
        this.currX = currX;
    }

    public void setCurrY(int currY) {
        this.currY = currY;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square[][] squares = this.board.getSquareArray();
                Square sq = squares[y][x];
//                sq.setDisplay(true);
                PaintSquare paintSquare = new PaintSquare(sq);
                paintSquare.paintComponent(g);

            }
        }

        Piece currPiece = this.board.getCurrPiece();

        if (currPiece != null) {
            if ((currPiece.getColor() == 1 && this.board.getTurn())
                    || (currPiece.getColor() == 0 && this.board.getTurn())) {
                final Image i = PieceImageProvider.getImage(currPiece);
                g.drawImage(i, this.board.getCurrX(), this.board.getCurrY(), null);
            }
        }
    }


}
