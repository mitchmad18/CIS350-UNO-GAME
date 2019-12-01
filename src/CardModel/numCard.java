package CardModel;

import GameView.card;

import java.awt.*;

/******************************************************************************
 * The numCard class serves as an UNO "card" of TYPE: NUMBER. This class will
 * be implemented to identify number cards from other card types.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author add name
 ******************************************************************************/
public class numCard extends card {

    /******************************************************************************
     * Empty default class constructor to get card TYPE.
     ******************************************************************************/
    public numCard() {
    }

    /******************************************************************************
     * Default class constructor with params.
     * @param cColor - color of card.
     * @param cValue - specific card action.
     ******************************************************************************/
    public numCard(Color cColor, String cValue) {
        super(cColor, NUMBERS, cValue);
    }
}
