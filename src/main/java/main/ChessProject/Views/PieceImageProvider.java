package main.ChessProject.Views;

import main.ChessProject.Models.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PieceImageProvider {
    private static final Map<String, BufferedImage> imageCache = new HashMap<>();

    public static BufferedImage getImage(Piece piece) {
        String colorPrefix = piece.getColor() == 0 ? "w" : "b";
        String pieceName = getPieceName(piece);
        String filename = colorPrefix + pieceName + ".png";


        String path = "/" + filename;

        if (imageCache.containsKey(filename)) {
            return imageCache.get(filename);
        }

        try {
            BufferedImage img = ImageIO.read(PieceImageProvider.class.getResource(path));
            imageCache.put(filename, img);
            return img;
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Image not found: " + path);
            return null;
        }
    }

    private static String getPieceName(Piece piece) {
        if (piece instanceof Pawn) return "pawn";
        if (piece instanceof Knight) return "knight";
        if (piece instanceof Bishop) return "bishop";
        if (piece instanceof Rook) return "rook";
        if (piece instanceof Queen) return "queen";
        if (piece instanceof King) return "king";
        return "";
    }
}
