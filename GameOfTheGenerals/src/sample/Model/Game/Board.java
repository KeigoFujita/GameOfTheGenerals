package sample.Model.Game;

import sample.Model.Piece.Piece;

import java.util.ArrayList;

public class Board {

    private int height;
    private int width;

    private int totalPrivate = 6;
    private int totalSpy = 2;
    private int totalGeneral5 = 1;
    private int totalGeneral4 = 1;
    private int totalGeneral3 = 1;
    private int totalGeneral2 = 1;
    private int totalGeneral1 = 1;
    private int totalColonel = 1;
    private int totalLtColonel = 1;
    private int totalMajor = 1;
    private int totalCaptain = 1;
    private int totalFLieutenant = 1;
    private int totalSLieutenant = 1;
    private int totalSergeant = 1;
    private int totalFlag = 1;



    private ArrayList<Piece> pieces;

    public Board(int height, int width) {

        this.pieces = new ArrayList<>();
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean addPiece(Piece newPiece){

        if(newPiece.isNewLocation() || newPiece.getY() < 5){

            return false;

        }
        for(Piece piece: pieces){

            if(newPiece.getX() == piece.getX() && newPiece.getY() == piece.getY()){

                System.out.println("invalid");
                return false;
            }

        }

        System.out.println("New Piece X: "+newPiece.getX());
        System.out.println("New Piece Y: "+newPiece.getY());
        switch (newPiece.getRank()){

            case 1: if(updatePieceCount(totalFlag))
                totalFlag--;
                pieces.add(newPiece);
                return true;
            case 2: if(updatePieceCount(totalPrivate))
                totalPrivate--;
                pieces.add(newPiece);
                return true;

            case 3: if(updatePieceCount(totalSergeant))
                        totalSergeant--;
                        pieces.add(newPiece);
                        return true;
            case 4: if(updatePieceCount(totalSLieutenant))
                totalSLieutenant--;
                pieces.add(newPiece);
                return true;
            case 5: if(updatePieceCount(totalFLieutenant))
                totalFLieutenant--;
                pieces.add(newPiece);
                return true;
            case 6: if(updatePieceCount(totalCaptain))
                totalCaptain--;
                pieces.add(newPiece);
                return true;
            case 7: if(updatePieceCount(totalMajor))
                totalMajor--;
                pieces.add(newPiece);
                return true;
            case 8: if(updatePieceCount(totalLtColonel))
                totalLtColonel--;

            case 9: if(updatePieceCount(totalColonel))
                totalColonel--;
                pieces.add(newPiece);
                return true;

            case 14: if(updatePieceCount(totalGeneral5))
                        totalGeneral5--;
                        pieces.add(newPiece);
                        return true;

            case 13: if(updatePieceCount(totalGeneral4))
                totalGeneral4--;
                pieces.add(newPiece);
                return true;

            case 12: if(updatePieceCount(totalGeneral3))
                totalGeneral3--;
                pieces.add(newPiece);
                return true;

            case 11: if(updatePieceCount(totalGeneral2))
                totalGeneral2--;
                pieces.add(newPiece);
                return true;

            case 10: if(updatePieceCount(totalGeneral1))
                totalGeneral1--;
                pieces.add(newPiece);
                return true;

            case 15: if(updatePieceCount(totalSpy))
                        totalSpy--;
                        pieces.add(newPiece);
                        return true;


            default: return false;
        }

    }



    public int getTotalPrivate() {
        return totalPrivate;
    }

    private boolean updatePieceCount(int limit){

        if(limit > 0)
            return true;
        return false;

    }

    public int getTotalSpy() {
        return totalSpy;
    }

    public int getTotalGeneral5() {
        return totalGeneral5;
    }

    public int getTotalGeneral4() {
        return totalGeneral4;
    }

    public int getTotalGeneral3() {
        return totalGeneral3;
    }

    public int getTotalGeneral2() {
        return totalGeneral2;
    }

    public int getTotalGeneral1() {
        return totalGeneral1;
    }

    public void reset(){

        pieces = new ArrayList<>();
        totalPrivate = 6;
        totalSpy = 2;
        totalGeneral5 = 1;
        totalGeneral4 = 1;
        totalGeneral3 = 1;
        totalGeneral2 = 1;
        totalGeneral1 = 1;
    }

    public boolean removePiece(Piece piece){

        for(Piece pieceDB: pieces){

            if(pieceDB.equals(piece)){

                pieces.remove(piece);
                return true;

            }
        }

        return false;

    }

}
