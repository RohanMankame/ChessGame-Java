package Main;

import Piece.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{


    public static final int WIDTH = 1100;
    public static final int HEIGHT = 800;
    public int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    //booleans
    boolean canMove;
    boolean validSquare;


    //Pieces
    public static ArrayList<Piece>  pieces = new ArrayList<>();
    public static ArrayList<Piece>  simPieces = new ArrayList<>();
    Piece activePiece;

    //Color
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    int currentColour = WHITE;



    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH,HEIGHT)); // set width & height of panel
        setBackground(Color.BLACK); // set background colour
        addMouseMotionListener(mouse);
        addMouseListener(mouse);

        setPieces(); //set pieces
        copyPieces(pieces, simPieces); // copy pieces to simPieces
    }

    // Instantiate Thread
    public void launchGame(){
        gameThread = new Thread(this); // crate thread
        gameThread.start(); //calls run method, starts thread
    }

    public void setPieces() {
        //White pieces
        pieces.add(new Pawn(WHITE,0,6));
        pieces.add(new Pawn(WHITE,1,6));
        pieces.add(new Pawn(WHITE,2,6));
        pieces.add(new Pawn(WHITE,3,6));
        pieces.add(new Pawn(WHITE,4,6));
        pieces.add(new Pawn(WHITE,5,6));
        pieces.add(new Pawn(WHITE,6,6));
        pieces.add(new Pawn(WHITE,7,6));
        pieces.add(new Knight(WHITE,1,7));
        pieces.add(new Knight(WHITE,6,7));
        pieces.add(new Rook(WHITE,0,7));
        pieces.add(new Rook(WHITE,7,7));
        pieces.add(new Bishop(WHITE,2,7));
        pieces.add(new Bishop(WHITE,5,7));
        pieces.add(new King(WHITE,4,4));
        pieces.add(new Queen(WHITE,3,7));

        //Black pieces
        pieces.add(new Pawn(BLACK,0,1));
        pieces.add(new Pawn(BLACK,1,1));
        pieces.add(new Pawn(BLACK,2,1));
        pieces.add(new Pawn(BLACK,3,1));
        pieces.add(new Pawn(BLACK,4,1));
        pieces.add(new Pawn(BLACK,5,1));
        pieces.add(new Pawn(BLACK,6,1));
        pieces.add(new Pawn(BLACK,7,1));
        pieces.add(new Knight(BLACK,1,0));
        pieces.add(new Knight(BLACK,6,0));
        pieces.add(new Rook(BLACK,0,0));
        pieces.add(new Rook(BLACK,7,0));
        pieces.add(new Bishop(BLACK,2,0));
        pieces.add(new Bishop(BLACK,5,0));
        pieces.add(new King(BLACK,3,0));
        pieces.add(new Queen(BLACK,4,0));

    }

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target){
        target.clear();
        for (int i = 0; i<source.size() ;i++){
            target.add(source.get(i));
        }
    }

    // runnable
    @Override
    public void run() {
        // game loop:
        //  A sequence of processes that run continously as long as the game is still running
        //  uses System.nanoTime() to measure elapsed time and calls update and repaint every 1/60th of a second
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if (delta >= 1){
                update();
                repaint();
                delta--;
            }
        }

    }

    // update game board every turn
    public void update() {
        //Mouse is clicked on piece
        if(mouse.pressed){
            if(activePiece == null){
                for (Piece piece: simPieces){
                    if(piece.colour == currentColour && piece.col == mouse.x/Board.SQUARE_SIZE && piece.row == mouse.y/Board.SQUARE_SIZE){
                        activePiece = piece;
                    }
                }
            }
            //active piece is not null
            else{
                //holding piece
                simulate();

            }
        }
        //make sure piece is moved to valid location
        if(mouse.pressed == false){
            if(activePiece!= null){
                if (validSquare){
                    //copy simulation to actual board
                    copyPieces(simPieces,pieces);

                    activePiece.updatePosition();
                }
                else{
                    //restore original board
                    copyPieces(pieces,simPieces);
                    activePiece.resetPosition();
                    activePiece=null;
                    }
                }

            }
        }



    public void simulate(){
        canMove =false;
        validSquare=false;

        copyPieces(pieces,simPieces);


        activePiece.x = mouse.x - Board.HALF_SQUARE_SIZE;
        activePiece.y = mouse.y- Board.HALF_SQUARE_SIZE;
        activePiece.col = activePiece.getCol(activePiece.x);
        activePiece.row = activePiece.getRow(activePiece.y);

        if(activePiece.canMove(activePiece.col,activePiece.row)){
            canMove = true;

            if(activePiece.hittingP != null){
                simPieces.remove(activePiece.hittingP.getIndex());
            }

            validSquare = true;
        }

    }


    // handle graphics
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //Board
        board.draw(g2);

        //pieces
        for(Piece p: simPieces){
            p.draw(g2);
        }
        //Highlight curr square if moving piece is bing moved
        if (activePiece != null){
            if(canMove) {
                g2.setColor(Color.PINK);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                g2.fillRect(activePiece.col * Board.SQUARE_SIZE, activePiece.row * Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
            //draw active piece in the end so not hidden by board or coloured squares
            activePiece.draw(g2);
        }


    }



}
