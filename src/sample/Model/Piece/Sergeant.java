package sample.Model.Piece;

import javafx.scene.paint.Color;

public class Sergeant extends Piece{

    public Sergeant(int x, int y) {
        super("Sergeant", x, y, 3, "src\\sample\\Images\\Sergeant.png");

    }

    public Sergeant() {
        super("Sergeant", 0,0, 3, "src\\sample\\Images\\Sergeant.png");

    }
}
