package ChessProject.Models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

public abstract class Piece {
    private final int color;
    private Square currentSquare;
    private BufferedImage img;

    public Piece(int color, Square initSq, String img_file) {
        this.color = color;
        this.currentSquare = initSq;

        try {
            if (this.img == null) {
                this.img = ImageIO.read(getClass().getResource(img_file));
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public boolean move(Square fin) {
        Piece occup = fin.getOccupyingPiece();

        if (occup != null) {
            if (occup.getColor() == this.color) return false;
            else fin.capture(this);
        }

        currentSquare.removePiece();
        this.currentSquare = fin;
        currentSquare.put(this);
        return true;
    }

    public Square getPosition() {
        return currentSquare;
    }

    public void setPosition(Square sq) {
        this.currentSquare = sq;
    }

    public int getColor() {
        return color;
    }

    public BufferedImage getImage() {
        return img;
    }




    // No implementation, to be implemented by each subclass
    public abstract List<Square> getLegalMoves(Board b);
}