package gameControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/******************************************************************************
 * The buttonListener class contains the games button listener. It extends
 * ActionListener, which will receive all button events on the game board.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author Myren Mitchell
 ******************************************************************************/
public class buttonListener implements ActionListener {

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
     * Method to control the draw button - to draw a card
     ******************************************************************************/
    public void drawCard() {
        if (gController.canPlay())
            gController.requestCard();
    }

    /******************************************************************************
     * Method to control the say UNO button - to say UNO.
     ******************************************************************************/
    public void sayUNO() {
        if (gController.canPlay())
            gController.returnSaidUNO();
    }

    /******************************************************************************
     * Implement for mult - only works mode 1
     ******************************************************************************/
    public void skip() {
        if (gController.canPlay())
            gController.sendSkip();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }
}
