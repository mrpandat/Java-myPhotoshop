package filter.basic;

import filter.Filter;

import java.awt.image.BufferedImage;

public class HorizontalFlip implements Filter {
    @Override
    public BufferedImage perform(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage res = new BufferedImage(w, h, img.getType());
        for (int i = 0; i <= img.getWidth()/2; i++)
            for (int j = 0; j < img.getHeight(); j++) {
                res.setRGB(i, j, img.getRGB(w - 1 - i,j));
                res.setRGB(w - 1 - i, j, img.getRGB(i, j));
            }
        return res;
    }

    @Override
    public String getName() {
        return "Horizontal Flip";
    }
}
