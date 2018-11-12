package sample.Model.Game;

import javafx.scene.image.ImageView;

public class ImagePane extends ImageView {

    private int xLoc;
    private int yLoc;
    private int rank;


    public ImagePane(int x, int y) {
        this.xLoc = x;
        this.yLoc = y;
    }


    public int getxLoc() {
        return xLoc;
    }

    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }

    public ImagePane(int xLoc, int yLoc, int rank) {
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
