package com.backfield.imagegenerator;

import java.awt.image.BufferedImage;

public interface Drawable
{
    public void draw(DrawAttributes coordinates, BufferedImage image);
}
