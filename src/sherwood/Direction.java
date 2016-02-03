package sherwood;

import javafx.scene.input.KeyCode;

public enum Direction {
    UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

    public final int dr, dc;

    Direction(int dr, int dc) {
        this.dr = dr;
        this.dc = dc;
    }

    public static Direction valueFor(KeyCode keyCode) {
        return valueOf(keyCode.name());
    }

    public Direction opposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        throw new RuntimeException();
    }
}
