package CardModel;

import GameView.card;

import java.awt.*;

/******************************************************************************
 * The wildCard class serves as an UNO "card" of TYPE: WILD. This class will
 * be implemented to identify wild cards from other card types.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author Myren Mitchell
 ******************************************************************************/
public class wildCard extends card {

    // Variable to set game color //
    private Color setColor;

    /******************************************************************************
     * Default class constructor with params.
     * @param cValue - specific card action.
     ******************************************************************************/
    public wildCard(String cValue) {
        super(BLACK, WILD, cValue);
    }

    /******************************************************************************
     * Method to get game color for WILD CARD.
     * @return - color chosen for game.
     ******************************************************************************/
    public Color getWildColor() {
        return setColor;
    }

    /******************************************************************************
     * Method to set game color when prompted for WILD CARD.
     * @param wColor - game card color.
     ******************************************************************************/
    public void setWildColor(Color wColor) {
        setColor = wColor;
    }

}
