package sample.Model.Piece;

import javafx.scene.paint.Color;

public class Flag extends Piece {
    public Flag(int x, int y) {
        super("Flag", x, y, 1,"src\\sample\\Images\\Flag.png");
    }

    public Flag() {
        super("Flag", 0, 0, 1,"src\\sample\\Images\\Flag.png");
    }


}
