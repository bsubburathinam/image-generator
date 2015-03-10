package com.backfield.imagegenerator;

public class UpperRightDrawable implements Drawable
{
    @Override
    public void draw(DrawAttributes coordinates, ImageDraw imageDraw) {
        int color = imageDraw.nextColor();
        for (int squareX = 0; squareX < imageDraw.getSquareSize(); squareX++) {
            for (int squareY = 0; squareY < squareX; squareY++) {
                imageDraw.getImage().setRGB(coordinates.getX() * imageDraw.getSquareSize() + squareX, coordinates.getY() * imageDraw.getSquareSize() + squareY, color);
            }
        }

    }
}
