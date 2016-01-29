import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class Board {

    private int[][] permutation;

    public abstract Board move(Direction direction, int times);

    public Board(int rows, int columns) {
        this.permutation = new int[rows][columns];
        Point.range(rows, columns)
                .forEach(point -> ((BiConsumer<Integer, Integer>) (r, c) -> this.permutation[r][c] = r * columns + c)
                        .accept(point.row(), point.column()));
    }

    public Board move(Direction direction) {
        return move(direction, 1);
    }

    @Override
    public String toString() {
        int log = (int) Math.ceil(Math.log10(permutation.length * permutation[0].length)) + 1;
        return String.join("\n", Stream.of(permutation).flatMap(s ->
                Stream.of(String.join("",
                   Arrays.stream(s).mapToObj(g -> String.format("%" + log + "d", g))
                   .collect(Collectors.toList())))
            ).collect(Collectors.toList())
        );
    }
}
