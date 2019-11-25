package GameView;

import Interface.gameConstants;
import gameControl.gameController;

import javax.swing.*;

/******************************************************************************
 * The gameFrame class represents the main frame of the game its panels
 * to be set up and displayed.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public class gameFrame extends JFrame implements gameConstants {

    // Uno game session instance //
    private gameSession gamePanel;
    // Uno game controller instance //
    private gameController gameController;

    /******************************************************************************
     * Empty default class constructor.
     ******************************************************************************/
    public gameFrame() {
        gameController = new gameController();
        CARDLISTEN.setController(gameController);
        BUTTONLISTEN.setController(gameController);

        gamePanel = gameController.getSession();
        add(gamePanel);
    }
}
