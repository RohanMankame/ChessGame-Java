package Piece;

import Main.GamePanel;

public class Pawn extends Piece{

    public Pawn(int color, int col, int row){
        super(color, col, row);

        if(color == GamePanel.WHITE){
            image = getImage("/piece/w-pawn");
        }
        else{
            image = getImage("/piece/b-pawn");
        }


    }

    public boolean canMove(int targetCol, int targetRow){
        if (isOnBoard(targetCol,targetRow) && !isSameSquare(targetCol, targetRow)){
            int moveValue;
            if(colour == GamePanel.WHITE){
                moveValue = -1;
            }
            else{
                moveValue = 1;
            }

            hittingP = getHittingPiece(targetCol,targetRow);
            if (targetCol == preCol && targetRow == preRow + moveValue && hittingP == null){
                return true;
            }

            if(targetCol == preCol && targetRow == preRow + 2*moveValue && hittingP == null && !moved && !pieceIsOnStraightLine(targetCol, targetRow)){
                return true;
            }

            if(Math.abs(targetCol-preCol)==1 && targetRow == preRow+moveValue && hittingP != null && hittingP.colour != this.colour){
                return true;
            }


        }
        return false;
    }

}
