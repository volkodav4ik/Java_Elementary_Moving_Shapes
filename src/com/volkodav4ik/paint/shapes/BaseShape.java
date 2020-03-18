package com.volkodav4ik.paint.shapes;

import com.volkodav4ik.Const;
import com.volkodav4ik.paint.Board;
import com.volkodav4ik.paint.DisplayDriver;

public abstract class BaseShape implements Shape {

    protected Board board;
    protected DisplayDriver displayDriver;
    protected double x;
    protected double y;
    protected double size;
    protected MyColor color;
    protected boolean selected = false;

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
    public void move(Board.Direction direction) {
        switch (direction) {
            case RIGHT:
                if (!(this.x >= (Const.BOARD_WIDTH - this.size))) {
                    this.x += +Const.STEP_OF_MOVEMENT;
                }
                break;
            case LEFT:
                if (!(this.x <= 0)) {
                    this.x -= Const.STEP_OF_MOVEMENT;
                }
                break;
            case UP:
                if (!(this.y <= 0)) {
                    this.y -= Const.STEP_OF_MOVEMENT;
                }
                break;
            case DOWN:
                if (!(this.y >= (Const.BOARD_WIDTH - this.size))) {
                    this.y += Const.STEP_OF_MOVEMENT;
                }
                break;
        }
    }

    @Override
    public void increaseSize() {
        if (((Const.BOARD_WIDTH - Const.STEP_OF_MOVEMENT) > (this.x + this.size))
                && ((Const.BOARD_HEIGHT - Const.STEP_OF_MOVEMENT) > (this.y + this.size))) {
            size += Const.VALUE_OF_CHANGING_SIZE;
        }
    }

    @Override
    public void decreaseSize() {
        if (size > Const.VALUE_OF_CHANGING_SIZE) {
            size -= Const.VALUE_OF_CHANGING_SIZE;
        }
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

    public boolean ifSelected() {
        return selected;
    }
}
