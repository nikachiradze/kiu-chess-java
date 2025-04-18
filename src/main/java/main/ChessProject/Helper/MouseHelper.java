package main.ChessProject.Helper;

import main.ChessProject.Controllers.BoardController;
import main.ChessProject.Models.Square;
import main.ChessProject.Views.PaintBoard;
import main.ChessProject.Views.PaintSquare;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHelper implements MouseListener, MouseMotionListener {

    private BoardController controller;
    private PaintBoard paintBoard;

    public MouseHelper(BoardController controller, PaintBoard paintBoard) {
        this.controller = controller;
        this.paintBoard = paintBoard;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        this.paintBoard.setCurrX(e.getX());
        this.paintBoard.setCurrY(e.getY());


        PaintSquare ps = (PaintSquare) this.paintBoard.getComponentAt(new Point(e.getX(), e.getY()));
        Square sq = ps.getSquare();

//        handleMousePressed(Square square) -> handleMousePressed(sq);
        controller.mousePressHandler(sq);

        paintBoard.repaint();


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PaintSquare ps = (PaintSquare) this.paintBoard.getComponentAt(new Point(e.getX(), e.getY()));
        Square sq = ps.getSquare();

        controller.mouseReleaseHandler(sq);
        ps.repaint();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        paintBoard.setCurrX(e.getX() - 24);
        paintBoard.setCurrY(e.getY() - 24);

        paintBoard.repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
