package sherwood;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sherwood.gui.GameBar;

import java.io.File;
import java.util.stream.Collectors;

public class Main extends Application {

    private static Main main;
    public static Label moves;
    public Board board;
    private Pane game;
    private int movesInt;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        main = this;
        BorderPane full = new BorderPane();
        moves = new Label("0");
        game = new Pane();

        board = new SlidingBoard(7, 7, new File("image.jpg"));
        game.getChildren().addAll(board.images().collect(Collectors.toList()));

        full.setRight(game);

        ToolBar bar = new GameBar(this);
        full.setTop(bar);

        ObservableList<String> comData = FXCollections.observableArrayList();
        ListView<String> commutators = new ListView<>(comData);
        full.setLeft(commutators);

        Scene scene = new Scene(full);
        game.setOnKeyPressed(e -> {
            System.out.println(e);
            if (e.getCode().isArrowKey())
                if (board.move(Direction.valueFor(e.getCode()))) {
                    moves.setText(++movesInt+"");
                }
            e.consume();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
        game.requestFocus();

    }

    public void reset(int rows, int columns) {
        board = new SlidingBoard(rows, columns, new File("image.jpg"));
        game.getChildren().clear();
        game.getChildren().addAll(board.images().collect(Collectors.toList()));
        moves.setText("0");
        movesInt = 0;
    }

    public void focus() {
        game.requestFocus();
    }

    public static Main get() {
        return main;
    }
}
