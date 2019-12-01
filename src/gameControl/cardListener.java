package gameControl;

import GameView.card;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/******************************************************************************
 * The cardListener class contains the games card listener. It extends
 * MouseAdapter, which will receive all mouse events on the game board.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author add name
 ******************************************************************************/
public class cardListener extends MouseAdapter {

    // Source of card clicked //
    private card source;
    // Game controller instance //
    gameController gController;

    /******************************************************************************
     * Method to set the controller of the game.
     * @param gameController - control of game.
     ******************************************************************************/
    public void setController(gameController gameController) {
        gController = gameController;
    }

    /******************************************************************************
     * Method to notify the press of each card on the game board.
     * @param e - mouse action.
     ******************************************************************************/
    public void mousePressed(MouseEvent e) {
        source = (card) e.getSource();

        try {
            if (gController.canPlay())
                gController.playCard(source);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /******************************************************************************
     * Method to notify mouse entry of each card on the game board.
     * @param e - mouse action.
     ******************************************************************************/
    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);

        source = (card) e.getSource();
        Point p = source.getLocation();
        p.y -= 20;
        source.setLocation(p);
    }

    /******************************************************************************
     * Method to notify mouse exiting of each card on the game board.
     * @param e - mouse action.
     ******************************************************************************/
    @Override
    public void mouseExited(MouseEvent e) {
        source = (card) e.getSource();
        Point p = source.getLocation();
        p.y += 20;
        source.setLocation(p);
    }

    /******************************************************************************
     * Method to notify the release of each card on the game board.
     * @param e - mouse action.
     ******************************************************************************/
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }
}
