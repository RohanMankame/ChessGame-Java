package Main;

import java.awt.*;

public class Board {
    final int MAX_COL = 8;
    final int MAX_ROW = 8;
    public static final int  SQUARE_SIZE = 100;
    public static final int  HALF_SQUARE_SIZE = SQUARE_SIZE/2;

    int colour = 0;
    // draw chess board
    public void draw(Graphics2D g2){
        for (int row = 0 ; row < MAX_ROW ; row++ ) {
            for (int col = 0 ; col < MAX_COL ; col++ ) {

                if (colour == 0){
                    g2.setColor(new Color(102,0,204)); //set dark colour
                    colour = 1; //change colour for next position
                }
                else{
                    g2.setColor(new Color(229,204,255)); //set light colour
                    colour = 0; //change colour for next position
                }

                g2.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // x , y , width , height
            }
            //after we finish a row we don't want to change colour from last col colour for next row colour, so undo the colour change for this case
            if (colour == 0){
                colour = 1;
            }
            else{
                colour = 0;
            }
        }
    }



}
