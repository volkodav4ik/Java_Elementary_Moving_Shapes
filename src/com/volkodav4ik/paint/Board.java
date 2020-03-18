package com.volkodav4ik.paint;

import com.volkodav4ik.Const;
import com.volkodav4ik.paint.shapes.*;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final DisplayDriver displayDriver;
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
        selectLastAdd();
    }

    public void addSquare() {
        shapes.add(new Square(this, displayDriver, Const.START_X, Const.START_Y, Const.FIXED_SIZE, MyColor.VIOLET));
        selectLastAdd();
    }

    public void addTriangle() {
        shapes.add(new Triangle(this, displayDriver, Const.START_X, Const.START_Y, Const.FIXED_SIZE, MyColor.GREEN));
        selectLastAdd();
    }

    private void allNonSelected(){
        for (Shape shape : shapes) {
            shape.setSelected(false);
        }
    }

    private void selectLastAdd(){
        allNonSelected();
        shapes.get(shapes.size()-1).setSelected(true);
    }

    public void addAllShapes() {
        for (Shape shape : shapes) {
            shape.setSelected(true);
        }
    }

    public void selectShape() {
        if (shapes.isEmpty()) {
            System.out.println("Nothing to select!");
        }
        if (numberOfShape >= shapes.size()) {
            numberOfShape = 0;
        }
        allNonSelected();
        shapes.get(numberOfShape).setSelected(true);
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

    public void selectByMouse(double x, double y) {
        int count = 0;
        for (Shape shape : shapes) {
            if (shape instanceof Square) {
                if (x >= shape.getX() && x <= (shape.getX() + shape.getSize())
                        && y >= shape.getY() && y <= (shape.getY() + shape.getSize())) {
                    if (shape.ifSelected()){
                        shape.setSelected(false);
                    } else {
                        shape.setSelected(true);
                    }
                    count++;
                }
            }
            if (shape instanceof Triangle) {
                if (triangleByCoordinate(shape, x, y)) {
                    if (shape.ifSelected()){
                        shape.setSelected(false);
                    } else {
                        shape.setSelected(true);
                    }
                    count++;
                }
            }
            if (shape instanceof Oval) {
                if ((Math.pow(((shape.getX() + shape.getSize()/2) - x), 2)
                        + Math.pow(((shape.getY() + shape.getSize()/2) - y), 2)) <= Math.pow(shape.getSize()/2, 2)) {
                    if (shape.ifSelected()){
                        shape.setSelected(false);
                    } else {
                        shape.setSelected(true);
                    }
                    count++;
                }
            }
        }
        if (count == 0) {
            for (Shape shapeNonSelected : shapes) {
                shapeNonSelected.setSelected(false);
            }
        }
    }

    private boolean triangleByCoordinate(Shape shape, double x, double y) {
        double aX = shape.getX();
        double aY = shape.getY() + Math.sqrt(0.75 * shape.getSize() * shape.getSize());
        double bX = shape.getX() + (shape.getSize() / 2);
        double bY = shape.getY();
        double cX = shape.getX() + shape.getSize();
        double A = (aX - x) * (bY - aY) - (bX - aX) * (aY - y);
        double B = (bX - x) * (aY - bY) - (cX - bX) * (bY - y);
        double C = -1 * (aX - cX)*(aY - y);
        return (A > 0 && B > 0 && C > 0) || (A < 0 && B < 0 && C < 0);
    }
}
