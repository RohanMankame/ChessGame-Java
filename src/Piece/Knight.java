package Piece;

import Main.GamePanel;

public class Knight extends Piece{

    public Knight(int color, int col, int row){
        super(color, col, row);

        if(color == GamePanel.WHITE){
            image = getImage("/piece/w-knight");
        }
        else{
            image = getImage("/piece/b-knight");
        }
    }

    public boolean canMove(int targetCol, int targetRow){
        if(isOnBoard(targetCol,targetRow)){
            // needs to be 2*1 or 1*2
            if(Math.abs(targetCol-preCol)*Math.abs(targetRow-preRow) == 2){
                if(isValidSquare(targetCol,targetRow)){
                    return true;
                }

            }

        }
        return false;
    }




}
