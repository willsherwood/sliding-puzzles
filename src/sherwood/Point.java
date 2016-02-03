package sherwood;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Point {

    private final int row, column;

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Stream<Point> range(int r, int c) {
        return IntStream.range(0, r).boxed()
                .flatMap(row -> IntStream.range(0, c)
                .mapToObj(column -> new Point(row, column)));
    }

    public final int column() {
        return column;
    }

    public final int row() {
        return row;
    }

    public Point limit(int row, int column) {
        return new Point(
                Math.min(Math.max(this.row, 0), row - 1),
                Math.min(Math.max(this.column, 0), column - 1)
        );
    }

    public Point delta(Direction direction) {
        return new Point(row + direction.dr, column + direction.dc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (row != point.row) return false;
        return column == point.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }

    @Override
    public String toString() {
        return "sherwood.Point{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
