package com.backfield.imagegenerator;

public class LowerLeftDrawable implements Drawable
{
    @Override
    public void draw(DrawAttributes coordinates, ImageDraw imageDraw)
    {
        int color = imageDraw.nextColor();
        for (int squareX = 0; squareX < imageDraw.getSquareSize(); squareX++) {
            for (int squareY = squareX; squareY < imageDraw.getSquareSize(); squareY++) {
                imageDraw.getImage().setRGB(coordinates.getX() * imageDraw.getSquareSize() + squareX, coordinates.getY() * imageDraw.getSquareSize() + squareY, color);
            }
        }

    }
}
