package filter.basic;

import filter.Filter;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;

public class FrenchFlag implements Filter {
    @Override
    public BufferedImage perform(BufferedImage img) {
        Color blue = new Color(0, 0, 255, 50);
        Color white = new Color(255, 255, 255, 50);
        Color red = new Color(255, 0, 0, 50);
        Graphics g = img.getGraphics();
        int twidth = img.getWidth() / 3;
        int height = img.getHeight();


        g.setColor(blue);

        g.fillRect(0, 0, twidth, height);
        g.setColor(white);

        g.fillRect(twidth, 0, twidth * 2, height);
        g.setColor(red);

        g.fillRect(2 * twidth, 0, twidth * 3, height);
        return img;

    }


    @Override
    public String getName() {
        return "French Flag";
    }
}
