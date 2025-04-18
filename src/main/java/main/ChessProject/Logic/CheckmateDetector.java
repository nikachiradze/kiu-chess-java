package main.ChessProject.Logic;

import main.ChessProject.Models.*;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Component of the Chess game that detects check mates in the game.
 *
 * @author Jussi Lundstedt
 */
public class CheckmateDetector {
    private final Board b;
    private final LinkedList<Piece> wPieces;
    private final LinkedList<Piece> bPieces;
    private final LinkedList<Square> movableSquares;
    private final LinkedList<Square> squares;
    private final King bk;
    private final King wk;
    private final HashMap<Square, List<Piece>> wMoves;
    private final HashMap<Square, List<Piece>> bMoves;

    public CheckmateDetector(Board b, LinkedList<Piece> wPieces,
                             LinkedList<Piece> bPieces, King wk, King bk) {
        this.b = b;
        this.wPieces = wPieces;
        this.bPieces = bPieces;
        this.bk = bk;
        this.wk = wk;

        this.squares = new LinkedList<>();
        this.movableSquares = new LinkedList<>();
        this.wMoves = new HashMap<>();
        this.bMoves = new HashMap<>();

        Square[][] brd = b.getSquareArray();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square square = brd[y][x];
                squares.add(square);
                wMoves.put(square, new LinkedList<>());
                bMoves.put(square, new LinkedList<>());
            }
        }

        update();
    }

    public void update() {
        wMoves.values().forEach(List::clear);
        bMoves.values().forEach(List::clear);
        movableSquares.clear();

        updateMoves(wPieces, wMoves, King.class);
        updateMoves(bPieces, bMoves, King.class);
    }

    private void updateMoves(LinkedList<Piece> pieces,
                             HashMap<Square, List<Piece>> moveMap,
                             Class<?> excludedClass) {
        Iterator<Piece> iterator = pieces.iterator();
        while (iterator.hasNext()) {
            Piece p = iterator.next();
            if (!p.getClass().equals(excludedClass)) {
                if (p.getPosition() == null) {
                    iterator.remove();
                    continue;
                }
                for (Square move : p.getLegalMoves(b)) {
                    moveMap.get(move).add(p);
                }
            }
        }
    }

    public boolean blackInCheck() {
        update();
        if (wMoves.get(bk.getPosition()).isEmpty()) {
            movableSquares.addAll(squares);
            return false;
        }
        return true;
    }

    public boolean whiteInCheck() {
        update();
        if (bMoves.get(wk.getPosition()).isEmpty()) {
            movableSquares.addAll(squares);
            return false;
        }
        return true;
    }

    public boolean blackCheckMated() {
        if (!blackInCheck()) return false;
        if (canEvade(wMoves, bk)) return false;
        if (canCapture(bMoves, wMoves.get(bk.getPosition()), bk)) return false;
        return !canBlock(wMoves.get(bk.getPosition()), bMoves, bk);
    }

    public boolean whiteCheckMated() {
        if (!whiteInCheck()) return false;
        if (canEvade(bMoves, wk)) return false;
        if (canCapture(wMoves, bMoves.get(wk.getPosition()), wk)) return false;
        return !canBlock(bMoves.get(wk.getPosition()), wMoves, wk);
    }

    private boolean canEvade(Map<Square, List<Piece>> tMoves, King tKing) {
        boolean evade = false;
        for (Square sq : tKing.getLegalMoves(b)) {
            if (!testMove(tKing, sq)) continue;
            if (tMoves.get(sq).isEmpty()) {
                movableSquares.add(sq);
                evade = true;
            }
        }
        return evade;
    }

    private boolean canCapture(Map<Square, List<Piece>> poss,
                               List<Piece> threats, King k) {
        if (threats.size() != 1) return false;

        Square sq = threats.get(0).getPosition();
        boolean capture = false;

        if (k.getLegalMoves(b).contains(sq) && testMove(k, sq)) {
            movableSquares.add(sq);
            capture = true;
        }

        for (Piece p : new ConcurrentLinkedDeque<>(poss.get(sq))) {
            if (testMove(p, sq)) {
                movableSquares.add(sq);
                capture = true;
            }
        }

        return capture;
    }

    private boolean canBlock(List<Piece> threats,
                             Map<Square, List<Piece>> blockMoves, King k) {
        if (threats.size() != 1) return false;

        Square ts = threats.get(0).getPosition();
        Square ks = k.getPosition();
        Square[][] brd = b.getSquareArray();
        boolean blockable = false;

        int dx = Integer.compare(ts.getXNum(), ks.getXNum());
        int dy = Integer.compare(ts.getYNum(), ks.getYNum());

        if (!(dx == 0 || dy == 0 || Math.abs(dx) == Math.abs(dy))) return false;

        int x = ks.getXNum() + dx;
        int y = ks.getYNum() + dy;

        while (x != ts.getXNum() || y != ts.getYNum()) {
            Square blockSquare = brd[y][x];
            for (Piece p : new ConcurrentLinkedDeque<>(blockMoves.get(blockSquare))) {
                if (testMove(p, blockSquare)) {
                    movableSquares.add(blockSquare);
                    blockable = true;
                }
            }
            x += dx;
            y += dy;
        }

        return blockable;
    }

    public List<Square> getAllowableSquares(boolean isWhite) {
        movableSquares.clear();
        if (isWhite ? whiteInCheck() : blackInCheck()) {
            if (isWhite) whiteCheckMated();
            else blackCheckMated();
        }
        return movableSquares;
    }

    public boolean testMove(Piece p, Square sq) {
        Piece captured = sq.getOccupyingPiece();
        Square from = p.getPosition();

        p.move(sq);
        update();

        boolean valid = !(p.getColor() == 0 && blackInCheck() || p.getColor() == 1 && whiteInCheck());

        p.move(from);
        if (captured != null) sq.put(captured);

        update();
        movableSquares.addAll(squares);

        return valid;
    }
}