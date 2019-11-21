package Interface;

import GameView.updatePanel;
import gameControl.buttonListener;
import gameControl.cardListener;

import java.awt.*;

/******************************************************************************
 * The gameConstants interface represents the unique constants of each UNO
 * card.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public interface gameConstants extends unoConstants {

    // Card deck total //
    int deckTOTAL = 108;
    // Cards dealt to players //
    int firstDeal = 7;
    // Card listener instance //
    cardListener CARDLISTEN = new cardListener();
    // Button listener instance //
    buttonListener BUTTONLISTEN = new buttonListener();
    // Card colors options //
    Color[] cardCOLORS = {RED, YELLOW, GREEN, BLUE};
    Color wildCardCOLOR = BLACK;
    // Card number options //
    int[] cardNUMBERS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    // Card action types //
    String[] actionTypes = {REVERSE, SKIP, DRAW2};
    // Card wild action types //
    String[] wildTypes = {WCOLORPICK, WDRAW4};
    // vs AI mode
    int AIMode = 1;
    // Manual mode  ** TO BE CORRECTED ? ** //
    int manualMode = 2;
    // Game modes //
    int[] gameModes = {AIMode, manualMode};
    // Update panel //
    updatePanel updatePanel = new updatePanel();

}
