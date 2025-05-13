package main.ChessProject.Models;

import java.util.List;

public abstract class Piece {
    private final int color;
    private Square currentSquare;

    public Piece(int color, Square initSq) {
        this.color = color;
        this.currentSquare = initSq;


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


    // No implementation, to be implemented by each subclass
    public abstract List<Square> getLegalMoves(Board b);
}