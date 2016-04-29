package filter;

import java.awt.image.BufferedImage;

/**
 * All your filters must implement this interface.
 * Don't modify it.
 */
public interface Filter
{
    public BufferedImage perform (BufferedImage img);
    String getName();
}

