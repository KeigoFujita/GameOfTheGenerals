package sample.Model.Piece;

public class General extends Piece{


    public General(int x, int y, int star) {
        super(star + " Star General", x, y, star + 9);

        switch (star) {

            case 5:
                super.setUrlLocation("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\5Star.jpg");
                break;
            case 4:
                super.setUrlLocation("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\4StarGeneral.png");
                break;
            case 3:
                super.setUrlLocation("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\3StarGeneral.png");
                break;
            case 2:
                super.setUrlLocation("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\2StarGeneral.png");
                break;
            case 1:
                super.setUrlLocation("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\1StarGeneral.png");
                break;
            default:
                super.setUrlLocation("");
        }

    }

    public General(int star) {
        super(star + " Star General", 0, 0, star + 9);

        switch (star) {

            case 5:
                super.setUrlLocation("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\5Star.jpg");
                break;
            case 4:
                super.setUrlLocation("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\4StarGeneral.png");
                break;
            case 3:
                super.setUrlLocation("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\3StarGeneral.png");
                break;
            case 2:
                super.setUrlLocation("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\2StarGeneral.png");
                break;
            case 1:
                super.setUrlLocation("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\1StarGeneral.png");
                break;
            default:
                super.setUrlLocation("");
        }

    }



}
