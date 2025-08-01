package Piece;

import Main.GamePanel;

public class Rook extends Piece{

    public Rook(int color, int col, int row){
        super(color, col, row);

        if(color == GamePanel.WHITE){
            image = getImage("/piece/w-rook");
        }
        else{
            image = getImage("/piece/b-rook");
        }

    }

    public boolean canMove(int targetCol, int targetRow){
        if (isOnBoard(targetCol,targetRow) && !isSameSquare(targetCol, targetRow)){
            if (targetCol == preCol || targetRow == preRow){
                if (isValidSquare(targetCol,targetRow) && !pieceIsOnStraightLine(targetCol, targetRow)){
                    return true;
                }


            }

        }
        return false;
    }

}
