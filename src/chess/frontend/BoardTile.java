package chess.frontend;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardTile extends JLabel implements MouseListener {
    int[] position = new int[2];
    public BoardTile(int x, int y){
        position[0] = x;
        position[1] = y;
        this.addMouseListener(this);
    }

    public int[] GetPosition(){ return position; }

    //region MouseListener Methods
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("I was clicked at position " + position[0] + ", " + position[1] );
        Client.Reference.TileClicked(position[0], position[1]);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
    //endregion
}
