package com.backfield.imagegenerator;

public class DrawAttributes
{
    private int x = 0;
    private int y = 0;
    private int color = 0;

    public DrawAttributes(
        int xValue,
        int yValue,
        int colorValue
    )
    {
        this.x = xValue;
        this.y = yValue;
        this.color = colorValue;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
