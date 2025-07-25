package Piece;

import Main.Board;

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
        return col * Board.SQUARE_SIZE; // pos is at [0,0] its 0,0.  pos is at [2,4] its 200,400. (because Board.SQUARESIZE is 100)
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


    public void draw(Graphics2D g2){
        g2.drawImage(image,x,y,Board.SQUARE_SIZE,Board.SQUARE_SIZE,null);
    }


}
