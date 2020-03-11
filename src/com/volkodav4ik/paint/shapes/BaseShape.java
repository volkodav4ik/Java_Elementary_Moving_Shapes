package com.volkodav4ik.paint.shapes;

import com.volkodav4ik.paint.Board;
import com.volkodav4ik.paint.DisplayDriver;

public abstract class BaseShape implements Shape {

    protected Board board;
    protected DisplayDriver displayDriver;
    protected double x;
    protected double y;
    protected double size;
    protected MyColor color;
    public boolean selected = false;

    public BaseShape(Board board, DisplayDriver displayDriver, double x, double y, double size, MyColor color) {
        this.board = board;
        this.displayDriver = displayDriver;
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public void setColor(MyColor color) {
        this.color = color;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setSize(double size) {
        this.size = size;
    }

    public boolean ifSelected() {
        return selected;
    }
}
