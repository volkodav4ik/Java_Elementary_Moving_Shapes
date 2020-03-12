package com.volkodav4ik.paint.shapes;

public interface Shape {

    void draw();

    void selectDraw();

    boolean ifSelected();

    void setSelected(boolean b);

    double getX();

    double getY();

    double getSize();

    void setX(double x);

    void setY(double y);

    void setSize(double size);


}
