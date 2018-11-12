package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import sample.Model.Game.ImagePane;
import sample.Model.Piece.*;
import sample.Model.Game.Board;
import sample.Model.Game.BoardPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Controller{

    @FXML
    private GridPane gridBoard;

    @FXML
    private HBox boardContainer;

    @FXML
    private GridPane piecesHolder,piecesGrid;

    @FXML
    private Label lblPrivate;

    @FXML
    private Label lblSpy,lblGeneral5,lblGeneral4,lblGeneral3,lblGeneral2,lblGeneral1;

    @FXML
    private Button btnReset,btnUndo;

    private ArrayList<BoardPane> boardPanes = new ArrayList<>();
    private ArrayList<ImagePane> imagePanes = new ArrayList<>();
    private Board board;

    private ArrayList<String> urls;
    private ArrayList<Piece> pieces;

    private Piece currentPiece;
    private int currentPieceLocation = -1;
    private int lastPieceLocation = -1;

    private Piece lastPiece;

    private ImagePane currentImagePane;




    public void initialize(){

        board = new Board(8,9);
//        lblPrivate.setText("Private: "+board.getTotalPrivate());
//        lblSpy.setText("Spy: "+board.getTotalSpy());
//
//
//
//        lblGeneral5.setText("5 Star General: "+board.getTotalGeneral5());
//        lblGeneral4.setText("4 Star General: "+board.getTotalGeneral4());
//        lblGeneral3.setText("3 Star General: "+board.getTotalGeneral3());
//        lblGeneral2.setText("2 Star General: "+board.getTotalGeneral2());
//        lblGeneral1.setText("1 Star General: "+board.getTotalGeneral1());

        gridBoard= new GridPane();
        gridBoard.setMaxHeight(630);
        gridBoard.setMaxWidth(560);

        //load urls of images of the pieces
        loadURLS();


        //initialize Pieces without X and Y coordinates
        initPieces();


        //display Pieces in the pieces drawer
        displayPieces();

        //draw the Board
        drawBoard();


        //add On Click Listener to the buttons in GUI
        addListenersToButtons();

        boardContainer.getChildren().add(gridBoard);


    }

    private boolean addPiece(Piece piece, Board board, BoardPane bp){

        if(board.addPiece(piece)){

            try {

                FileInputStream inputStream = new FileInputStream(piece.getUrlLocation());
                Image image = new Image(inputStream);
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(70);
                imageView.setPreserveRatio(true);

                bp.getChildren().add(imageView);
                piece.setIsNewLocation(false);

                return true;

            }catch (FileNotFoundException e){
                e.printStackTrace();

            }

        }

        return false;
    }


    //BOARD FUNCTIONS

    //draw the Board panes in the grid
    private void drawBoard(){

        for(int i = 0; i <board.getWidth(); i++){

            for(int j = 0; j < board.getHeight(); j++){

                BoardPane bp = new BoardPane(i,j);
                bp.setMinSize(80,80);
                bp.setAlignment(Pos.CENTER);


                if(i%2 == 0){

                    if(j%2 == 0) {

                       // bp.setStyle("-fx-background-color: rgb(46,16,3)");

                        Color color = Color.rgb(46,16,3);
                        bp.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                        bp.setColor(color);

                    }else{

                        //bp.setStyle("-fx-background-color: rgb(206,154,64)");

                        Color color = Color.rgb(206,154,64);
                        bp.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                        bp.setColor(color);
                    }


                }else{

                    if(j%2 == 0) {

                        //bp.setStyle("-fx-background-color: rgb(206,154,64)");
                        Color color = Color.rgb(206,154,64);
                        bp.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                        bp.setColor(color);


                    }else{
                        //bp.setStyle("-fx-background-color: rgb(46,16,3)");
                        Color color = Color.rgb(46,16,3);
                        bp.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
                        bp.setColor(color);

                    }
                }


                addMouseEventToBoardPanes(bp);
                boardPanes.add(bp);
                gridBoard.add(bp,i,j);

            }

        }

    }

    //refreshes the the individual Board Panes in the Board
    private void refreshBoard(){

        for(BoardPane boardPane: boardPanes){

            if(boardPane.getChildren().size() > 0){
                boardPane.getChildren().remove(0);
            }

            for (Piece piece: pieces){



                if(boardPane.getX() == piece.getX()
                   && boardPane.getY() == piece.getY() && !piece.isNewLocation()){


                            try {

//                                if(boardPane.getChildren().size()==0) {

                            FileInputStream inputStream = new FileInputStream(piece.getUrlLocation());
                            Image image = new Image(inputStream);
                            ImageView imageView = new ImageView(image);
                            imageView.setFitWidth(70);
                            imageView.setPreserveRatio(true);

                            boardPane.getChildren().add(imageView);
                            break;

//                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

//                }else if(piece.isNewLocation() && boardPane.getChildren().size() > 0){
//
//                    boardPane.getChildren().remove(0);
                }

            }

        }

    }

    private void addMouseEventToBoardPanes(BoardPane bp){

        bp.setOnMouseClicked((MouseEvent e) -> {

            if(bp.getChildren().size() > 0){

                for(int i = 0; i < pieces.size(); i++){

                    if(bp.getX() == pieces.get(i).getX() && bp.getY() == pieces.get(i).getY()){

                        currentPieceLocation = i;

                    }
                }

            }else{

                if(currentPieceLocation!= -1) {

                    System.out.println("current Piece Location: "+currentPieceLocation);
                    board.removePiece(pieces.get(currentPieceLocation));

                    pieces.get(currentPieceLocation).setLocation(bp.getX(), bp.getY());
                    pieces.get(currentPieceLocation).setIsNewLocation(false);


                   if( board.addPiece(pieces.get(currentPieceLocation))) {

                       System.out.println("Added to the board");

                       refreshBoard();
                       refreshPiecesDrawer();


                   }

 //                  refreshBoard();
 //                  refreshPiecesDrawer();
//
//                   else{
//
//
//                       System.out.println("Invalid input to the board");
//                       pieces.get(currentPieceLocation).setIsNewLocation(true);
//                       pieces.get(currentPieceLocation).setLocation(-1,-1);
//
//
//
//                       refreshBoard();
//                       refreshPiecesDrawer();

//                   }
//
//
//                boolean isLast = true;
//
//                    for(Piece piece: pieces){
//
//                        if(currentPiece.getRank() == piece.getRank()){
//                            if(piece.isNewLocation()){
//
//                                isLast = false;
//                                System.out.println("Piece "+currentPiece.getName()+" is not the last.");
//                                break;
//
//                            }
//
//                        }
//
//                    }
//
//                    if(isLast){
//
//                        System.out.println("Piece "+currentPiece.getName()+" is the last.");
//                        System.out.println("Current Image pane Rank: "+currentImagePane.getRank());
//                        currentImagePane.setVisible(false);
//                        System.out.println(currentImagePane.isVisible());
//
//                    }
//
//                    lastPiece = currentPiece;


                    lastPieceLocation = currentPieceLocation;
                    currentPieceLocation = -1;

                }

            }



        });

        bp.setOnMouseEntered((MouseEvent e) ->{


            bp.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));


        });

        bp.setOnMouseExited((MouseEvent e) ->{

            bp.setBackground(new Background(new BackgroundFill(bp.getColor(), CornerRadii.EMPTY, Insets.EMPTY)));


        });


    }



    //IMAGE DRAWER FUNCTIONS
    private void initPieces(){

        pieces = new ArrayList<>();

        Flag flag = new Flag();
        pieces.add(flag);

        for(int i = 1; i <=6; i++ ){

            Private privatePiece = new Private();
            pieces.add(privatePiece);

        }

        for(int i = 1; i <=2; i++ ){

            Spy spy = new Spy();
            pieces.add(spy);

        }

        Sergeant sergeant = new Sergeant();
        pieces.add(sergeant);

        for(int i = 1; i <=2; i++ ){

            Lieutenant lieutenant = new Lieutenant(i);
            pieces.add(lieutenant);

        }

        Captain captain = new Captain();
        pieces.add(captain);


        Major major = new Major();
        pieces.add(major);

        LieutenantColonel lieutenantColonel = new LieutenantColonel();
        pieces.add(lieutenantColonel);

        Colonel colonel = new Colonel();
        pieces.add(colonel);

        for(int i = 1; i <=5; i++ ){

            General general = new General(i);
            pieces.add(general);

        }

        for(Piece piece: pieces){
            piece.setLocation(-1,-1);
        }
    }
    private void displayPieces(){

        piecesGrid = new GridPane();
        piecesGrid.setHgap(5);
        piecesGrid.setVgap(5);

        //count is for URL location of the Image
        int count = 0;

        for (int i = 0; i <3; i++){
            for(int j = 0; j < 5; j++){

                try {
                    if(count ==15)
                        break;

                    FileInputStream inputStream = new FileInputStream(urls.get(count));
                    Image image = new Image(inputStream);
                    ImagePane imagePane = new ImagePane(i,j,count+1);
                    imagePane.setImage(image);
                    imagePane.setFitWidth(100);
                    imagePane.setPreserveRatio(true);
                    piecesGrid.add(imagePane,i,j);

                    imagePanes.add(imagePane);
                    addMouseListenerToImagePane(imagePane);


                    count++;

                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }


            }
        }



        piecesHolder.getChildren().add(piecesGrid);


    }
    private void refreshPiecesDrawer(){

                for (ImagePane imagePane: imagePanes) {

                    for (Piece piece : pieces) {


                        if(imagePane.getRank() == piece.getRank() && piece.isNewLocation() && !imagePane.isVisible()){

                            imagePane.setVisible(true);



                        }else if(imagePane.getRank() == piece.getRank() && !piece.isNewLocation() && imagePane.isVisible()){

                            imagePane.setVisible(false);


                        }


                    }

                }
        }

    private void addMouseListenerToImagePane(ImagePane imagePane){

        imagePane.setOnMouseClicked((  MouseEvent e) -> {


            currentImagePane = imagePane;

            for(int i = 0; i < pieces.size(); i++){

                if(pieces.get(i).getRank() == imagePane.getRank() && pieces.get(i).isNewLocation()){

                   currentPieceLocation = i;
                   break;

                }
            }


        });



    }




    private void loadURLS(){

        urls = new ArrayList<>();

        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\Flag.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\private.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\Sergeant.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\1stLieutenant.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\2ndLieutenenant.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\Captain.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\Major.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\Liutenant Colonel.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\Colonel.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\1StarGeneral.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\2StarGeneral.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\3StarGeneral.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\4StarGeneral.png");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\5Star.jpg");
        urls.add("C:\\Users\\orioque35\\IntellIjProject\\GameOfTheGenerals\\src\\sample\\Images\\Spy.png");


    }

    private void removePieceInBoardGUI(int x, int y){

        for(BoardPane boardPane: boardPanes){

            if(boardPane.getX() == x && boardPane.getY() == y){

                boardPane.getChildren().removeAll();

            }
        }



    }

    private void addListenersToButtons(){

        btnReset.setOnAction((ActionEvent e) ->{

            board.reset();
            pieces = null;
            urls = null;
            currentImagePane = null;
            currentPiece = null;
            piecesHolder.getChildren().remove(piecesGrid);

            loadURLS();
            initPieces();
            displayPieces();
            drawBoard();

            lastPieceLocation = -1;
            currentPieceLocation = -1;

            lblPrivate.setText("Private: "+board.getTotalPrivate());
            lblSpy.setText("Spy: "+board.getTotalSpy());
            lblGeneral5.setText("5 Star General 5: "+board.getTotalGeneral5());
            lblGeneral4.setText("4 Star General 4: "+board.getTotalGeneral4());
            lblGeneral3.setText("3 Star General 3: "+board.getTotalGeneral3());
            lblGeneral2.setText("2 Star General 2: "+board.getTotalGeneral2());
            lblGeneral1.setText("1 Star General 1: "+board.getTotalGeneral1());

        });

        btnUndo.setOnMouseClicked((MouseEvent e) ->{

          board.removePiece(pieces.get(lastPieceLocation));
          pieces.get(lastPieceLocation).setLocation(-1,-1);
          pieces.get(lastPieceLocation).setIsNewLocation(true);



          refreshBoard();
          refreshPiecesDrawer();


        });


    }

    private void updatePieceList(Piece newPiece){

        for(Piece piece: pieces){



        }


    }


}





