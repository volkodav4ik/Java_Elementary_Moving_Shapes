package com.volkodav4ik.paint;

import com.volkodav4ik.Const;
import com.volkodav4ik.paint.shapes.MyColor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DisplayDriverImpl implements DisplayDriver {

    private final GraphicsContext gc;

    public DisplayDriverImpl(GraphicsContext gc) {
        this.gc = gc;
    }

    @Override
    public void setColor(String hex) {
        gc.setStroke(Color.web(MyColor.BLACK.toHex()));
        gc.setLineWidth(Const.LINE_WIDTH);
    }

    @Override
    public void colorOfSelection(String hex) {
        gc.setFill(Color.web(hex));
    }

    @Override
    public void drawOval(double x, double y, double diameter) {
        gc.strokeOval(x, y, diameter, diameter);
    }

    @Override
    public void drawTriangle(double x, double y, double side) {
        gc.strokePolygon(new double[]{x, x + side, x + (side / 2)}, new double[]{getYofTriangle(y, side), getYofTriangle(y, side), y}, 3);
    }

    @Override
    public void drawSquare(double x, double y, double side) {
        gc.strokeRect(x, y, side, side);
    }

    @Override
    public void drawSelectedOval(double x, double y, double size) {
        gc.fillOval(x, y, size, size);
    }

    @Override
    public void drawSelectedTriangle(double x, double y, double size) {
        gc.fillPolygon(new double[]{x, x + size, x + (size / 2)}, new double[]{getYofTriangle(y, size), getYofTriangle(y, size), y}, 3);
    }

    @Override
    public void drawSelectedSquare(double x, double y, double size) {
        gc.fillRect(x, y, size, size);
    }

    private double getYofTriangle(double y, double side) {
        return y + Math.sqrt(0.75 * side * side);
    }
}
