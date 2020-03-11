package com.volkodav4ik.paint;

public interface DisplayDriver {

    void setColor(String hex);

    void colorOfSelection(String hex);

    void drawOval(double x, double y, double diameter);

    void drawTriangle(double x, double y, double side);

    void drawSquare(double x, double y, double side);

    void drawSelectedOval(double x, double y, double size);

    void drawSelectedTriangle(double x, double y, double size);

    void drawSelectedSquare(double x, double y, double size);
}
