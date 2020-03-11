package com.volkodav4ik.paint;

import com.volkodav4ik.Const;
import com.volkodav4ik.paint.shapes.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final DisplayDriver displayDriver;
    private int previousNumber;
    private int numberOfShape;

    private List<Shape> shapes = new ArrayList<>();

    public Board(DisplayDriver displayDriver) {
        this.displayDriver = displayDriver;

    }

    public void drawFrame() {
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                shape.selectDraw();
            } else {
                shape.draw();
            }
        }
    }

    public void addOval() {
        shapes.add(new Oval(this, displayDriver, Const.START_X, Const.START_Y, Const.FIXED_SIZE, MyColor.RED));
    }

    public void addSquare() {
        shapes.add(new Square(this, displayDriver, Const.START_X, Const.START_Y, Const.FIXED_SIZE, MyColor.VIOLET));
    }

    public void addTriangle() {
        shapes.add(new Triangle(this, displayDriver, Const.START_X, Const.START_Y, Const.FIXED_SIZE, MyColor.GREEN));
    }

    public void selectShape() {
        if (numberOfShape >= shapes.size()) {
            numberOfShape = 0;
            previousNumber = 0;
        }
        shapes.get(previousNumber).setSelected(false);
        shapes.get(numberOfShape).setSelected(true);
        previousNumber = numberOfShape;
        numberOfShape++;
    }

    public void moveRight() {
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                if (!(shape.getX() >= (Const.BOARD_WIDTH - shape.getSize()))) {
                    shape.setX(shape.getX() + Const.STEP_OF_MOVEMENT);
                }
            }
        }
    }

    public void moveLeft() {
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                if (!(shape.getX() <= 0)) {
                    shape.setX(shape.getX() - Const.STEP_OF_MOVEMENT);
                }
            }
        }
    }

    public void moveUp() {
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                if (!(shape.getY() <= 0)) {
                    shape.setY(shape.getY() - Const.STEP_OF_MOVEMENT);
                }
            }
        }
    }

    public void moveDown() {
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                if (!(shape.getY() >= (Const.BOARD_WIDTH - shape.getSize()))) {
                    shape.setY(shape.getY() + Const.STEP_OF_MOVEMENT);
                }
            }
        }
    }

    public void increaseSize() {
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                if (((Const.BOARD_WIDTH - Const.STEP_OF_MOVEMENT) > (shape.getX() + shape.getSize()))
                        && ((Const.BOARD_HEIGHT - Const.STEP_OF_MOVEMENT) > (shape.getY() + shape.getSize()))) {
                    shape.setSize(shape.getSize() + Const.VALUE_OF_CHANGING_SIZE);
                }
            }
        }
    }

    public void decreaseSize() {
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                if (shape.getSize() > Const.VALUE_OF_CHANGING_SIZE) {
                    shape.setSize(shape.getSize() - Const.VALUE_OF_CHANGING_SIZE);
                }
            }
        }
    }

    public void delete() {
        shapes.removeIf(Shape::ifSelected);
    }
}
