package main.ChessProject.Views;

import main.ChessProject.Models.Piece;
import main.ChessProject.Models.Square;

import javax.swing.*;
import java.awt.*;

public class PaintSquare extends JComponent {

    private Square square;


    public PaintSquare(Square square) {
        this.square = square;
        this.setBorder(BorderFactory.createEmptyBorder());

    }


    public Square getSquare() {
        return this.square;
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        if (this.square.getColor() == 1) {
            g.setColor(new Color(221, 192, 127));
        } else {
            g.setColor(new Color(101, 67, 33));
        }

        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        if (this.square.getOccupyingPiece() != null && this.square.isDispPiece()) {
            Piece occupyingPiece = this.square.getOccupyingPiece();
            PaintPiece.draw(g, occupyingPiece.getImage());
        }
    }
}
