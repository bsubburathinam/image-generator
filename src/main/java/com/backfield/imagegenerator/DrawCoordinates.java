package com.backfield.imagegenerator;

public class DrawCoordinates
{
    private int x = 0;
    private int y = 0;

    public DrawCoordinates(
        int xValue,
        int yValue
    )
    {
        this.x = xValue;
        this.y = yValue;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
