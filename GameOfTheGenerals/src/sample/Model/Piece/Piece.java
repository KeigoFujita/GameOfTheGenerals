package sample.Model.Piece;

import javafx.scene.paint.Color;

public class Piece {

    private String name;
    private int x;
    private int y;
    private int rank;
    private String urlLocation;
    private boolean isNewLocation;

    public String getUrlLocation() {
        return urlLocation;
    }

    public Piece(String name, int x, int y, int rank, String urlLocation) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.rank = rank;
        this.urlLocation = urlLocation;
        this.isNewLocation = true;
    }

    public Piece(String name, int x, int y, int rank) {

        this.name = name;
        this.x = x;
        this.y = y;
        this.rank = rank;
        this.isNewLocation = true;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRank() {
        return rank;
    }

    public void setUrlLocation(String urlLocation) {
        this.urlLocation = urlLocation;
    }

    public boolean isNewLocation() {
        return isNewLocation;
    }

    public void setIsNewLocation(boolean newLocation) {
        isNewLocation = newLocation;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
