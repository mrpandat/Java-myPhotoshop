package filter.basic;

import filter.Filter;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class Binary implements Filter {
    @Override
    public BufferedImage perform(BufferedImage img) {

        Color white = new Color(255, 255, 255);
        Color black = new Color(0, 0, 0);
        int moy = (black.getRGB() + white.getRGB()) / 2;

        BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                if (img.getRGB(i, j) > moy)
                    res.setRGB(i, j, white.getRGB());
                else
                    res.setRGB(i, j, black.getRGB());
            }
        }
        return res;
    }

    @Override
    public String getName() {
        return "Binary";
    }
}
