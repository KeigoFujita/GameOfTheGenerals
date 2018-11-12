package sample.Model.Piece;

public class Lieutenant extends Piece{

    public Lieutenant(int x, int y,int star) {
        super(star+" Lieutenant", x, y, star + 3);

        switch (star){

            case 1: super.setUrlLocation("src\\sample\\Images\\1stLieutenant.png");
            break;

            case 2: super.setUrlLocation("src\\sample\\Images\\2ndLieutenenant.png");
            break;

            default: super.setUrlLocation("");

        }

    }

    public Lieutenant(int star) {
        super(star+" Lieutenant", 0, 0, star + 3);

        switch (star){

            case 1: super.setUrlLocation("src\\sample\\Images\\1stLieutenant.png");
                break;

            case 2: super.setUrlLocation("src\\sample\\Images\\2ndLieutenenant.png");
                break;

            default: super.setUrlLocation("");

        }

    }

}
