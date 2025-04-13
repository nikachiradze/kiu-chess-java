package ChessProject.Views;

import java.awt.*;
import java.awt.image.BufferedImage;


public class PaintPiece {

    public static void draw(Graphics g, BufferedImage img) {
        int x = 0;
        int y = 0;
        g.drawImage(img, x, y, null);
    }


}
