package Piece;

import Main.Board;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;

//Super class for all pieces
public class Piece {
    public BufferedImage image;
    public int x,y;
    public  int col, row, preCol, preRow;
    public int colour;

    public Piece hittingP;

    public Piece(int color,int col,int row){
        this.col = col;
        this.row=row;
        this.colour = color;
        x = getX(col);
        y = getY(row);
        preCol = col;
        preRow = row;
    }

    //based on image path get buffer image
    public BufferedImage getImage(String imagePath){
        BufferedImage image  = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public int getX(int col){
        return col * Board.SQUARE_SIZE; // pos is at [0,0] its 0,0.  pos is at [2,4] its 200,400. (because Board.SQUARE_SIZE is 100)
    }

    public int getY(int row){
        return row * Board.SQUARE_SIZE;
    }

    public int getCol(int x){
        return (x+ Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;

    }

    public int getRow(int y){
        return (y+ Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE;

    }


    public int getIndex(){
        for(int index = 0 ; index < GamePanel.simPieces.size();index++){
            if(GamePanel.simPieces.get(index) ==this){
                return index;

            }
        }
        return 0;
    }


    public void updatePosition(){
        x = getX(col);
        y = getY(row);
        preCol = getCol(x);
        preRow = getRow(y);
    }

    //move piece back to where it was picked up from
    public void resetPosition(){
        col = preCol;
        row = preRow;
        x = getX(col);
        y = getY(row);
    }

    //overridden based on piece
    public boolean canMove(int targetCol, int targetRow){
        return false;
    }

    //check which piece moved piece is hitting/ moving too
    public Piece getHittingPiece(int targetCol, int targetRow){
        for (Piece piece : GamePanel.simPieces){
            if( piece.col == targetCol && piece.row == targetRow && piece != this){
                return piece;
            }
        }
        return null;
    }

    //play has to be on board
    public boolean isOnBoard(int targetCol, int targetRow){
        if((targetCol >= 0 && targetCol <= 7) && (targetRow >= 0 && targetRow <= 7)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isValidSquare(int targetCol, int targetRow){
        hittingP = getHittingPiece(targetCol,targetRow);
        if(hittingP == null){
            return true;
        }
        else{
            if( hittingP.colour != this.colour){
                return true;
            }
            else{
                hittingP = null;

            }
        }
        return false;
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image,x,y,Board.SQUARE_SIZE,Board.SQUARE_SIZE,null);
    }



}
