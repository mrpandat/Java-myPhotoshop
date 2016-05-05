package filter.basic;

import filter.Filter;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class AllBlue implements Filter
{
    @Override
    public BufferedImage perform(BufferedImage img)
    {
        BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        Color blue = new Color(0, 76, 148);
        for (int i = 0; i < img.getWidth(); i++)
            for (int j = 0; j < img.getHeight(); j++)
                res.setRGB(i, j, blue.getRGB());
        return res;
    }

    @Override
    public String getName()
    {
        return "All blue";
    }
}
