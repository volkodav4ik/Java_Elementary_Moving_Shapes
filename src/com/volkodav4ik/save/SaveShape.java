package com.volkodav4ik.save;

import com.volkodav4ik.paint.Board;
import com.volkodav4ik.paint.DisplayDriver;
import com.volkodav4ik.paint.shapes.*;

import java.util.ArrayList;
import java.util.List;

public class SaveShape {

    public enum ShapeType {OVAL, TRIANGLE, SQUARE, COMBINESHAPES;}

    private List<SaveShape> list;

    private ShapeType shapeType;
    private double size;
    private double x;
    private double y;
    private boolean selected;

    public SaveShape() {
    }

    public List<SaveShape> getList() {
        return list;
    }

    public static SaveShape createNewSaveShape(Shape shape) {
        SaveShape result = new SaveShape();
        if (shape instanceof BaseShape) {
            BaseShape baseShape = (BaseShape) shape;
            result.size = baseShape.getSize();
            result.x = baseShape.getX();
            result.y = baseShape.getY();
            result.selected = baseShape.ifSelected();
        }
        if (shape instanceof Oval) {
            result.shapeType = ShapeType.OVAL;
        }
        if (shape instanceof Triangle) {
            result.shapeType = ShapeType.TRIANGLE;
        }
        if (shape instanceof Square) {
            result.shapeType = ShapeType.SQUARE;
        }
        if (shape instanceof CombineShapes) {
            CombineShapes combineShapes = (CombineShapes) shape;
            result.list = new ArrayList<>();
            for (Shape combineShape : combineShapes.getCombineShapes()) {
                result.list.add(createNewSaveShape(combineShape));
            }
            result.shapeType = ShapeType.COMBINESHAPES;
            result.selected = combineShapes.ifSelected();
        }
        return result;
    }

    public static Shape createShape(SaveShape saveShape, Board board, DisplayDriver displayDriver) {
        if (saveShape.shapeType == ShapeType.OVAL) {
            Oval oval = new Oval(board, displayDriver, saveShape.x, saveShape.y, saveShape.size, MyColor.RED);
            if (saveShape.selected) {
                oval.setSelected(true);
            }
            return oval;
        }
        if (saveShape.shapeType == ShapeType.TRIANGLE) {
            Triangle triangle = new Triangle(board, displayDriver, saveShape.x, saveShape.y, saveShape.size, MyColor.GREEN);
            if (saveShape.selected) {
                triangle.setSelected(true);
            }
            return triangle;
        }
        if (saveShape.shapeType == ShapeType.SQUARE) {
            Square square = new Square(board, displayDriver, saveShape.x, saveShape.y, saveShape.size, MyColor.VIOLET);
            if (saveShape.selected) {
                square.setSelected(true);
            }
            return square;
        }
        if (saveShape.shapeType == ShapeType.COMBINESHAPES) {
            List<Shape> combineShapes = new ArrayList<>();
            List<SaveShape> tmpList = saveShape.getList();
            for (SaveShape savedShape : tmpList) {
                combineShapes.add(createShape(savedShape, board, displayDriver));
            }
            CombineShapes result = new CombineShapes(combineShapes);
            if (saveShape.selected) {
                result.setSelected(true);
            }
            return result;
        }
        return null;
    }
}
