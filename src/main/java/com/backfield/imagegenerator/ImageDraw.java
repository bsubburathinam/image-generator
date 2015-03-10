package com.backfield.imagegenerator;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ImageDraw {

    private BufferedImage image;

    private int squareSize;
    private int numberOfSquares;

    private Random random = new Random();

    private final int baseColor = 0x313A75;

    private final int[] baseColorPalette = {0x182157, 0x080F3A, 0x525B92, 0x7C93AF};

    private final int compliment = 0xAA8A39;

    private final int[] complimentPalette = {0xFFE7AA, 0xD4B66A, 0x806115, 0x553D00};

    private List<Drawable> drawables = Arrays.asList(
            new Drawable() { // lower left
                @Override
                public void draw(DrawAttributes coordinates, ImageDraw imageDraw) {
                    int color = imageDraw.nextColor();
                    for (int squareX = 0; squareX < imageDraw.getSquareSize(); squareX++) {
                        for (int squareY = squareX; squareY < imageDraw.getSquareSize(); squareY++) {
                            imageDraw.getImage().setRGB(coordinates.getX() * imageDraw.getSquareSize() + squareX, coordinates.getY() * imageDraw.getSquareSize() + squareY, color);
                        }
                    }
                }
            },
            new Drawable() { // lower right
                @Override
                public void draw(DrawAttributes coordinates, ImageDraw imageDraw) {
                    int color = imageDraw.nextColor();
                    for (int squareX = 0; squareX < imageDraw.getSquareSize(); squareX++) {
                        for (int squareY = (imageDraw.getSquareSize() - squareX); squareY < imageDraw.getSquareSize(); squareY++) {
                            imageDraw.getImage().setRGB(coordinates.getX() * imageDraw.getSquareSize() + squareX, coordinates.getY() * imageDraw.getSquareSize() + squareY, color);
                        }
                    }

                }
            },
            new Drawable() { // upper left
                @Override
                public void draw(DrawAttributes coordinates, ImageDraw imageDraw) {
                    int color = imageDraw.nextColor();
                    for (int squareX = 0; squareX < imageDraw.getSquareSize(); squareX++) {
                        for (int squareY = 0; squareY < imageDraw.getSquareSize(); squareY++) {
                            imageDraw.getImage().setRGB(coordinates.getX() * imageDraw.getSquareSize() + squareX, coordinates.getY() * imageDraw.getSquareSize() + squareY, color);
                        }
                    }
                }
            },
            new Drawable() { // upper right
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
    );

    public ImageDraw(int squareSize, int numberOfSquares) {
        this.squareSize = squareSize;
        this.numberOfSquares = numberOfSquares;
        this.image = new BufferedImage(this.squareSize * this.numberOfSquares, this.squareSize * this.numberOfSquares, BufferedImage.TYPE_INT_RGB);
    }

    public void setSeed(int hash) {
        this.random.setSeed(hash);
    }

    public int getSquareSize() {
        return squareSize;
    }

    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int nextColor() {
        float rand = this.random.nextFloat();
        int base;
        int[] palette;
        if(rand < 0.1) {
            base = compliment;
            palette = complimentPalette;
        } else {
            base = baseColor;
            palette = baseColorPalette;
        }
        if(rand < 0.6) {
            return base;
        } else {
            return palette[(int)(rand * 4)];
        }
    }

    public BufferedImage draw() {
        int x;
        int y;
        for(x = 0; x < this.numberOfSquares; x++) {
            for(y = 0; y < this.numberOfSquares; y++) {
                DrawAttributes coordinates = new DrawAttributes(x, y);
                int rand = this.random.nextInt(3);
                Drawable drawable = this.drawables.get(rand);
                drawable.draw(coordinates, this);
            }
        }
        return this.image;
    }

}
