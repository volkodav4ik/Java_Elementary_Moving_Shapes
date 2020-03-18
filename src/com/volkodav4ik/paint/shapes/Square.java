package com.volkodav4ik.paint.shapes;

import com.volkodav4ik.paint.Board;
import com.volkodav4ik.paint.DisplayDriver;

public class Square extends BaseShape implements Shape {


    public Square(Board board, DisplayDriver displayDriver, double x, double y, double size, MyColor color) {
        super(board, displayDriver, x, y, size, color);
    }

    public Square(Square original) {
        super(original.board, original.displayDriver, original.x, original.y, original.size, original.color);
    }
    @Override
    public void draw() {
        displayDriver.setColor(color.toHex());
        displayDriver.drawSquare(x, y, size);
    }

    @Override
    public void selectDraw() {
        displayDriver.colorOfSelection(color.toHex());
        displayDriver.drawSelectedSquare(x, y, size);
}
}
