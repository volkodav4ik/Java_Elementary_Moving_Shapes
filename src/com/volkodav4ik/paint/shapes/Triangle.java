package com.volkodav4ik.paint.shapes;

import com.volkodav4ik.paint.Board;
import com.volkodav4ik.paint.DisplayDriver;

public class Triangle extends BaseShape implements Shape {


    public Triangle(Board board, DisplayDriver displayDriver, double x, double y, double size, MyColor color) {
        super(board, displayDriver, x, y, size, color);
    }

    @Override
    public void draw() {
        displayDriver.setColor(color.toHex());
        displayDriver.drawTriangle(x, y, size);
    }

    @Override
    public void selectDraw() {
        displayDriver.colorOfSelection(color.toHex());
        displayDriver.drawSelectedTriangle(x, y, size);
    }
}
