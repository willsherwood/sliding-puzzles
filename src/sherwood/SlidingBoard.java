package sherwood;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;

public class SlidingBoard extends Board {
    private boolean moving;

    public SlidingBoard(int r, int c, File file) {
        super(r, c, file);
    }


    @Override
    public boolean move(Direction direction, int times) {
        if (moving || blank.equals(blank.delta(direction).limit(permutation.length, permutation[0].length)))
            return false;

        moving = true;

        ImageView g = this.permutation[blank.delta(direction).row()][blank.delta(direction).column()];
        animate(g, direction).play();

        this.permutation[blank.row()][blank.column()] = g;
        this.blank = this.blank.delta(direction);
        return true;
    }

    private Timeline animate(ImageView g, Direction direction) {
        Timeline out = new Timeline();
        out.getKeyFrames().addAll(
                new KeyFrame(new Duration(50), new KeyValue(g.xProperty(), g.getX() - tileSize * direction.dc)),
                new KeyFrame(new Duration(50), new KeyValue(g.yProperty(), g.getY() - tileSize * direction.dr))
//                new KeyFrame(new Duration(1), new KeyValue(g.rotateProperty(), 90 + g.getRotate()))
        );
        out.setOnFinished(a -> moving = false);
        return out;
    }
}
