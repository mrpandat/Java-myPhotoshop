package filter.basic;

import filter.Filter;

import java.awt.image.BufferedImage;

public class Grayscale implements Filter {
    @Override
    public BufferedImage perform(BufferedImage img) {

        int c, avg;
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage res = new BufferedImage( width,height, img.getType());


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height ; j++) {
                c = img.getRGB(i, j);

                //calculate the average color of the pixel
                avg = (((c >> 16) & 0xff) + ((c >> 8) & 0xff) + (c & 0xff)) / 3;
                //replace the rgb value with avg value
                c = (((c >> 24) & 0xff) << 24) | (avg << 16) | (avg << 8) | avg;

                res.setRGB(i, j, c);
            }
        }
        return res;
    }

    @Override
    public String getName() {
        return "Grayscale";
    }
}
