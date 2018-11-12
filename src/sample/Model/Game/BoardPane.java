package sample.Model.Game;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class BoardPane extends VBox {

    private int x;
    private int y;
    private Color color;

    public BoardPane(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
