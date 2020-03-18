package com.volkodav4ik.paint.shapes;

import java.io.Serializable;

public interface Shape extends Serializable {

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
