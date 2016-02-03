package sherwood.gui;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import sherwood.Board;
import sherwood.Direction;
import sherwood.Main;
import sherwood.SlidingBoard;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.stream.Collectors;

public class GameBar extends ToolBar {

    public GameBar(Main main) {
        super();
        Button reset = new Button("Reset");
        Button scramble = new Button("Scramble");
        Label rowLabel = new Label("Rows");
        TextField rows = new TextField("7");
        Label columnLabel = new Label("Columns");
        TextField columns = new TextField("7");
        Label moveLabel = new Label("Moves: ");
        Label timeLabel = new Label("Duration: ");
        Label time = new Label("0");

        final boolean[] scrambling = {false};

        getItems().addAll(reset, scramble, rowLabel, rows, columnLabel, columns, moveLabel, Main.moves, timeLabel, time);

        reset.setOnMousePressed(e -> {
            main.reset(Integer.parseInt(rows.getText()), Integer.parseInt(columns.getText()));
        });
        scramble.setOnMousePressed(e -> {
                    if (!scrambling[0]) {
                        scramble.setText("Stop Scrambling");
                        SwingUtilities.invokeLater(() -> {
                            scrambling[0] = true;
                            Direction last = Direction.DOWN;
                            while (scrambling[0]) {
                                Direction next;
                                do
                                    next = Direction.values()[(int) (Math.random() * 4)];
                                while (last.opposite() == next);
                                last = next;
                                Main.get().board.move(next);
                            }
                        });
                    } else {
                        scramble.setText("Scramble");
                        scrambling[0] = false;
                    }
                }

        );
    }
}
