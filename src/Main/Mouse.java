package Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    public int x,y;
    public boolean pressed;

    @Override
    public void mousePressed(MouseEvent e){
        pressed = true;


    }
    @Override
    public void mouseReleased(MouseEvent e){
        pressed = false;


    }
    @Override
    public void mouseDragged(MouseEvent e){
        //get mouse current position on board
        x = e.getX();
        y = e.getY();


    }
    @Override
    public void mouseMoved(MouseEvent e){
        //get mouse current position on board
        x = e.getX();
        y = e.getY();


    }

}
