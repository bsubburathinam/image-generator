package com.backfield.imagegenerator;

import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageDraw {

    private BufferedImage image;

    private int squareSize;
    private int numberOfSquares;

    private int x = 0;
    private int y = 0;

    private Random random = new Random();

    private final int baseColor = 0x313A75;

    private final int[] baseColorPalette = {0x182157, 0x080F3A, 0x525B92, 0x7C93AF};

    private final int compliment = 0xAA8A39;

    private final int[] complimentPalette = {0xFFE7AA, 0xD4B66A, 0x806115, 0x553D00};

    public ImageDraw(int squareSize, int numberOfSquares) {
        this.squareSize = squareSize;
        this.numberOfSquares = numberOfSquares;
        this.image = new BufferedImage(this.squareSize * this.numberOfSquares, this.squareSize * this.numberOfSquares, BufferedImage.TYPE_INT_RGB);
    }

    public void setSeed(int hash) {
        this.random.setSeed(hash);
    }

    private void drawLowerLeftTriangle() {
        int color = this.nextColor();
        for (int squareX = 0; squareX < this.squareSize; squareX++) {
            for (int squareY = squareX; squareY < this.squareSize; squareY++) {
                this.image.setRGB(this.x * this.squareSize + squareX, this.y * this.squareSize + squareY, color);
            }
        }
    }

    private void drawLowerRightTriangle() {
        int color = this.nextColor();
        for (int squareX = 0; squareX < this.squareSize; squareX++) {
            for (int squareY = (this.squareSize - squareX); squareY < this.squareSize; squareY++) {
                this.image.setRGB(this.x * this.squareSize + squareX, this.y * this.squareSize + squareY, color);
            }
        }
    }

    private void drawUpperLeftTriangle() {
        int color = this.nextColor();
        for (int squareX = 0; squareX < this.squareSize; squareX++) {
            for (int squareY = 0; squareY < this.squareSize; squareY++) {
                this.image.setRGB(this.x * this.squareSize + squareX, this.y * this.squareSize + squareY, color);
            }
        }
    }

    private void drawUpperRightTriangle() {
        int color = this.nextColor();
        for (int squareX = 0; squareX < this.squareSize; squareX++) {
            for (int squareY = 0; squareY < squareX; squareY++) {
                this.image.setRGB(this.x * this.squareSize + squareX, this.y * this.squareSize + squareY, color);
            }
        }
    }

    private void drawSquare() {
        int color = this.nextColor();
        for(int squareX = 0; squareX < this.squareSize; squareX++) {
            for(int squareY = 0; squareY < this.squareSize; squareY++) {
                this.image.setRGB(this.x * this.squareSize + squareX, this.y * this.squareSize + squareY, color);
            }
        }
    }

    private int nextColor() {
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
        for(this.x = 0; this.x < this.numberOfSquares; this.x++) {
            for(this.y = 0; this.y < this.numberOfSquares; this.y++) {
                float rand = this.random.nextFloat();
                if(rand < 0.2) {
                    this.drawSquare();
                } else if(rand < 0.4) {
                    this.drawUpperRightTriangle();
                } else if(rand < 0.6) {
                    this.drawUpperLeftTriangle();
                } else if(rand < 0.8) {
                    this.drawLowerRightTriangle();
                } else {
                    this.drawLowerLeftTriangle();
                }
            }
        }
        return this.image;
    }

}
