package com.volkodav4ik;

import com.volkodav4ik.paint.Board;
import com.volkodav4ik.paint.DisplayDriver;
import com.volkodav4ik.paint.DisplayDriverImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private GraphicsContext gc;
    private Board board;
    private boolean closed = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(Const.BOARD_WIDTH, Const.BOARD_HEIGHT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Game SOTa");
        stage.show();
        gc = canvas.getGraphicsContext2D();
        new Thread(this::runGame).start();

        DisplayDriver displayDriver = new DisplayDriverImpl(gc);
        board = new Board(displayDriver);
        scene.setOnKeyPressed(this::handleKeyPressed);
        scene.setOnMouseClicked(this::handlerMouseClicked);

    }

    private void handlerMouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.isControlDown()){
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            board.selectByMouse(x, y);
        }
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A){
            board.selectAllShapes();
        }
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.K){
            board.cloneShapes();
        }
        switch (keyEvent.getCode()) {
            case Q:
                board.addOval();
                break;
            case W:
                board.addSquare();
                break;
            case E:
                board.addTriangle();
                break;
            case S:
                board.selectShape();
                break;
            case RIGHT:
                board.moveRight();
                break;
            case LEFT:
                board.moveLeft();
                break;
            case UP:
                board.moveUp();
                break;
            case DOWN:
                board.moveDown();
                break;
            case ADD:
                board.increaseSize();
                break;
            case SUBTRACT:
                board.decreaseSize();
                break;
            case DELETE:
                board.delete();
                break;
        }
    }

    @Override
    public void stop() {
        closed = true;
    }

    private void runGame() {
        while (!closed) {
            Platform.runLater(this::drawFrame);
            try {
                Thread.sleep(Const.PAUSE);
            } catch (InterruptedException e) {
                break;
            }

        }
    }

    private void drawFrame() {
        gc.clearRect(0, 0, Const.BOARD_WIDTH, Const.BOARD_HEIGHT);
        board.drawFrame();
    }
}
