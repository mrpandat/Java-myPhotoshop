package filter.basic;

import filter.Filter;

import java.awt.image.BufferedImage;

public class VerticalFlip implements Filter {
    @Override
    public BufferedImage perform(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage res = new BufferedImage(w,h, img.getType());
        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j <= img.getHeight() / 2; j++) {
                res.setRGB(i, j, img.getRGB(i, img.getHeight() - 1 - j));
                res.setRGB(i, img.getHeight() - 1 - j, img.getRGB(i, j));
            }
        return res;
    }

    @Override
    public String getName() {
        return "Vertical Flip";
    }
}
