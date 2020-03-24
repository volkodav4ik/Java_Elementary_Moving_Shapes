package com.volkodav4ik.paint;

import com.volkodav4ik.Const;
import com.volkodav4ik.paint.shapes.*;
import com.volkodav4ik.save.Save;
import com.volkodav4ik.save.SaveShape;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public enum Direction {UP, DOWN, LEFT, RIGHT;}

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

    public void addCombineShape() {
        List<Shape> combineShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                combineShapes.add(shape);
            }
        }
        shapes.removeAll(combineShapes);
        shapes.add(new CombineShapes(combineShapes));
        selectLastAdd();
    }

    public void cloneShapes() {
        List<Shape> tmpList = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                if (shape instanceof Oval) {
                    tmpList.add(new Oval((Oval) shape));
                }
                if (shape instanceof Square) {
                    tmpList.add(new Square((Square) shape));
                }
                if (shape instanceof Triangle) {
                    tmpList.add(new Triangle((Triangle) shape));
                }
                if (shape instanceof CombineShapes) {
                    tmpList.add(new CombineShapes((CombineShapes) shape));
                }
                shape.setSelected(false);
            }
        }
        addClonesToMainList(tmpList);
    }

    public void selectAllShapes() {
        for (Shape shape : shapes) {
            shape.setSelected(true);
        }
    }

    public void delete() {
        shapes.removeIf(Shape::ifSelected);
    }

    public void selectShape() {
        if (shapes.isEmpty()) {
            System.out.println("Nothing to select!");
        }
        if (shapes.size() == 1) {
            changeSelectionIfClick(shapes.get(0));
        } else {
            if (numberOfShape >= shapes.size()) {
                numberOfShape = 0;
            }
            allNonSelected();
            shapes.get(numberOfShape).setSelected(true);
            numberOfShape++;
        }
    }

    public void move(Direction direction) {
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                shape.move(direction);
            }
        }
    }

    public void increaseSize() {
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                shape.increaseSize();
            }
        }
    }

    public void decreaseSize() {
        for (Shape shape : shapes) {
            if (shape.ifSelected()) {
                shape.decreaseSize();
            }
        }
    }

    public void selectByMouse(double x, double y) {
        int count = 0;
        for (Shape shape : shapes) {
            if (shape instanceof CombineShapes) {
                for (Shape combineShape : ((CombineShapes) shape).getCombineShapes()) {
                    if (combineShape instanceof Square) {
                        if (squareByCoordinate(combineShape, x, y)) {
                            changeSelectionIfClick(shape);
                            count++;
                        }
                    }
                    if (combineShape instanceof Triangle) {
                        if (triangleByCoordinate(combineShape, x, y)) {
                            changeSelectionIfClick(shape);
                            count++;
                        }
                    }
                    if (combineShape instanceof Oval) {
                        if (ovalByCoordinate(combineShape, x, y)) {
                            changeSelectionIfClick(shape);
                            count++;
                        }
                    }

                }
            }
            if (shape instanceof Square) {
                if (squareByCoordinate(shape, x, y)) {
                    changeSelectionIfClick(shape);
                    count++;
                }
            }
            if (shape instanceof Triangle) {
                if (triangleByCoordinate(shape, x, y)) {
                    changeSelectionIfClick(shape);
                    count++;
                }
            }
            if (shape instanceof Oval) {
                if (ovalByCoordinate(shape, x, y)) {
                    changeSelectionIfClick(shape);
                    count++;
                }
            }
        }
        if (count == 0) {
            allNonSelected();
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
        double C = -1 * (aX - cX) * (aY - y);
        return (A > 0 && B > 0 && C > 0) || (A < 0 && B < 0 && C < 0);
    }

    private boolean ovalByCoordinate(Shape shape, double x, double y) {
        return (Math.pow(((shape.getX() + shape.getSize() / 2) - x), 2)
                + Math.pow(((shape.getY() + shape.getSize() / 2) - y), 2)) <= Math.pow(shape.getSize() / 2, 2);
    }

    private boolean squareByCoordinate(Shape shape, double x, double y) {
        return x >= shape.getX() && x <= (shape.getX() + shape.getSize())
                && y >= shape.getY() && y <= (shape.getY() + shape.getSize());
    }

    private void changeSelectionIfClick(Shape shape) {
        if (shape.ifSelected()) {
            shape.setSelected(false);
        } else {
            shape.setSelected(true);
        }
    }

    private void addClonesToMainList(List<Shape> tmpList) {
        for (Shape cloneShape : tmpList) {
            cloneShape.setSelected(true);
            shapes.add(cloneShape);
        }
    }

    private void allNonSelected() {
        for (Shape shape : shapes) {
            shape.setSelected(false);
        }
    }

    private void selectLastAdd() {
        allNonSelected();
        shapes.get(shapes.size() - 1).setSelected(true);
    }

    public Save getSaveClass() {
        List<SaveShape> saveList = new ArrayList<>();
        for (Shape shape : shapes) {
            SaveShape save = SaveShape.createNewSaveShape(shape);
            saveList.add(save);
        }
        return new Save(saveList);
    }

    public void loadFromSave(Save save) {
        shapes.clear();
        List<SaveShape> saveList = save.getList();
        for (SaveShape saveShape : saveList) {
            Shape shape = SaveShape.createShape(saveShape, this, displayDriver);
            shapes.add(shape);
        }
    }

}
