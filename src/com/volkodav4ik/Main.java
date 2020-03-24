package com.volkodav4ik;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.volkodav4ik.paint.Board;
import com.volkodav4ik.paint.DisplayDriver;
import com.volkodav4ik.paint.DisplayDriverImpl;
import com.volkodav4ik.save.Save;
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
import java.io.*;

public class Main extends Application {

    private GraphicsContext gc;
    private Board board;
    private boolean closed = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
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
        if (mouseEvent.isControlDown()) {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            board.selectByMouse(x, y);
        }
    }

    private void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
            board.selectAllShapes();
        }
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.K) {
            board.cloneShapes();
        }
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.C) {
            board.addCombineShape();
        }
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.S) {
            saveGame();
        }
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.L) {
            loadGame();
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
            case R:
                board.selectShape();
                break;
            case RIGHT:
                board.move(Board.Direction.RIGHT);
                break;
            case LEFT:
                board.move(Board.Direction.LEFT);
                break;
            case UP:
                board.move(Board.Direction.UP);
                break;
            case DOWN:
                board.move(Board.Direction.DOWN);
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

    private void saveGame() {
        Gson gson = new GsonBuilder().create();
        String jsonSave = gson.toJson(board.getSaveClass());
        System.out.println(jsonSave);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Const.SAVE_FILE_NAME))) {
            writer.write(jsonSave);
        } catch (IOException e) {
            System.out.println("Can't save to file.");
        }
    }

    private void loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Const.SAVE_FILE_NAME))) {
            String stringForParsing = reader.readLine();
            Gson gson = new GsonBuilder().create();
            Save save = gson.fromJson(stringForParsing, Save.class);
            board.loadFromSave(save);
        } catch (IOException e) {
            System.out.println("File \"save\" can't be found!");
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
