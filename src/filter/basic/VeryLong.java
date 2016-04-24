package filter.basic;

import filter.Filter;

import java.awt.image.BufferedImage;
import java.util.Random;

public class VeryLong implements Filter
{
    @Override
    public BufferedImage perform(BufferedImage img)
    {
        BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        for (int i = 0; i < img.getWidth(); i++)
        {
            for (int j = 0; j < img.getHeight(); j++)
            {
                res.setRGB(i, j, new Random().nextInt());

                try
                {
                    Thread.sleep(1);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return res;
    }

    @Override
    public String getName()
    {
        return "Very long";
    }
}
