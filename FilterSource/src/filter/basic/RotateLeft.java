package filter.basic;

import filter.Filter;

import java.awt.image.BufferedImage;

/**
 * Created by jean on 25/04/2016.
 */
public class RotateLeft implements Filter {
    @Override
    public BufferedImage perform(BufferedImage img) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage res = new BufferedImage(h, w, img.getType());

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                res.setRGB(j, w - i - 1, img.getRGB(i, j));
            }
        }
        return res;

    }

    @Override
    public String getName() {
        return "Rotate left";
    }
}
