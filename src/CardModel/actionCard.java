package CardModel;

import GameView.card;

import java.awt.*;

/******************************************************************************
 * The actionCard class serves as an UNO "card" of TYPE: ACTION. This class will
 * be implemented to identify action cards from other card types.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author add name
 ******************************************************************************/
public class actionCard extends card {

    /******************************************************************************
     * Empty default class constructor to get card TYPE.
     ******************************************************************************/
    public actionCard() {
    }

    /******************************************************************************
     * Default class constructor with params.
     * @param cColor - color of card.
     * @param actionValue - specific card action.
     ******************************************************************************/
    public actionCard(Color cColor, String actionValue) {
        super(cColor, ACTION, actionValue);

    }
}
