import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public abstract class Board {

    private Image image;
    private ImageView[][] permutation;

    public final int tileSize;


    public abstract Board move(Direction direction, int times);

    public Board(int rows, int columns, File file) {
        try {
            image = new Image(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        tileSize = (int) (image.getWidth() / rows);
        this.permutation = new ImageView[rows][columns];
        Point.range(rows, columns).filter(point -> point.row() != point.column() || point.row() < rows - 1)
                .forEach(point -> ((BiConsumer<Integer, Integer>) (r, c) -> {
                    this.permutation[r][c] = TileFactory.thumb(r, c, this, image);
                    this.permutation[r][c].setX(c * tileSize);
                    this.permutation[r][c].setY(r * tileSize);
                }).accept(point.row(), point.column()));
    }

    public Board move(Direction direction) {
        return move(direction, 1);
    }

    public Stream<ImageView> images() {
        return Arrays.stream(permutation).flatMap(Arrays::stream).filter(Objects::nonNull);
    }

    @Override
    public String toString() {
//        int log = (int) Math.ceil(Math.log10(permutation.length * permutation[0].length)) + 1;
//        return String.join("\n", Stream.of(permutation).flatMap(s ->
//                Stream.of(String.join("",
//                   Arrays.stream(s).mapToObj(g -> String.format("%" + log + "d", g))
//                   .collect(Collectors.toList())))
//            ).collect(Collectors.toList())
//        );
        return super.toString();
    }
}
