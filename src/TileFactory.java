import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class TileFactory {

    public static ImageView thumb(int r, int c, Board board, Image image) {
        return new ImageView(new WritableImage(image.getPixelReader(),
                c * board.tileSize, r * board.tileSize, board.tileSize, board.tileSize));
    }
}
