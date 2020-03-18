package com.volkodav4ik.paint.shapes;

import com.volkodav4ik.paint.Board;

import java.io.Serializable;

public interface Shape extends Serializable {

    void draw();

    void selectDraw();

    void move(Board.Direction direction);

    void increaseSize();

    void decreaseSize();

    boolean ifSelected();

    void setSelected(boolean b);

    double getX();

    double getY();

    double getSize();

}
