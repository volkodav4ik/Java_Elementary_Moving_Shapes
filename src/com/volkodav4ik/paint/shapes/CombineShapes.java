package com.volkodav4ik.paint.shapes;

import com.volkodav4ik.paint.Board;

import java.util.ArrayList;
import java.util.List;

public class CombineShapes implements Shape {

    private List<Shape> combineShapes = new ArrayList<>();
    private boolean selected = false;

    public CombineShapes(List<Shape> combineShapes) {
        setCombineShapes(combineShapes);
    }

    private void setCombineShapes(List<Shape> combineShapes) {
        this.combineShapes.clear();
        for (Shape shape : combineShapes) {
            addShape(shape);
        }
    }

    private void addShape(Shape shape) {
        if (shape instanceof CombineShapes) {
            CombineShapes list = (CombineShapes) shape;
            combineShapes.addAll(list.getCombineShapes());
        } else {
            combineShapes.add(shape);
        }
    }

    public List<Shape> getCombineShapes() {
        return combineShapes;
    }

    @Override
    public void draw() {
        for (Shape shape : combineShapes) {
            shape.draw();
        }
    }

    @Override
    public void selectDraw() {
        for (Shape shape : combineShapes) {
            shape.selectDraw();
        }
    }

    @Override
    public void move(Board.Direction direction) {
        for (Shape shape : combineShapes){
            shape.move(direction);
        }
    }

    @Override
    public void increaseSize() {
        for (Shape shape : combineShapes){
            shape.increaseSize();
        }
    }

    @Override
    public void decreaseSize() {
        for (Shape shape : combineShapes){
            shape.decreaseSize();
        }
    }

    @Override
    public boolean ifSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean b) {
        selected = b;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public double getSize() {
        return 0;
    }
}
