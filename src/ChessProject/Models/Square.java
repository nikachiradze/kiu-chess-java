package ChessProject.Models;

@SuppressWarnings("serial")
public class Square {
    private Board b;

    private final int color;
    private Piece occupyingPiece;
    private boolean dispPiece;

    private int xNum;
    private int yNum;

    public Square(Board b, int c, int xNum, int yNum) {

        this.b = b;
        this.color = c;
        this.dispPiece = true;
        this.xNum = xNum;
        this.yNum = yNum;


    }

    public int getColor() {
        return this.color;
    }

    public boolean isDispPiece() {
        return dispPiece;
    }

    public Piece getOccupyingPiece() {
        return occupyingPiece;
    }

    public boolean isOccupied() {
        return (this.occupyingPiece != null);
    }

    public int getXNum() {
        return this.xNum;
    }

    public int getYNum() {
        return this.yNum;
    }

    public void setDisplay(boolean v) {
        this.dispPiece = v;
    }

    public void put(Piece p) {
        this.occupyingPiece = p;
        p.setPosition(this);
    }

    public Piece removePiece() {
        Piece p = this.occupyingPiece;
        this.occupyingPiece = null;
        return p;
    }

    public void capture(Piece p) {
        Piece k = getOccupyingPiece();
        if (k.getColor() == 0) b.Bpieces.remove(k);
        if (k.getColor() == 1) b.Wpieces.remove(k);
        this.occupyingPiece = p;
    }


    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + xNum;
        result = prime * result + yNum;
        return result;
    }

}
