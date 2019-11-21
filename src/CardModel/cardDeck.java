package CardModel;

import GameView.card;
import Interface.gameConstants;
import gameControl.cardListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 * The cardDeck class serves as the actual deck of cards for the UNO game.
 * This class creates the base deck of 108 to be played in the game.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public class cardDeck implements gameConstants {

    // Represents the base starting deck of Uno //
    private List<card> baseDeck;
    // Represents the number cards added to the base deck of Uno //
    private final List<numCard> numberCards;
    // Represents the action cards added to the base deck of Uno //
    private final List<actionCard> actionCards;
    // Represents the wild cards added to the base deck of Uno //
    private final List<wildCard> wildCards;

    /******************************************************************************
     * Empty default class constructor creating card deck on call.
     ******************************************************************************/
    public cardDeck() {
        baseDeck = new ArrayList<card>();
        numberCards = new ArrayList<numCard>();
        actionCards = new ArrayList<actionCard>();
        wildCards = new ArrayList<wildCard>();

        startDeck(); // Deck creation
        addCardListener(CARDLISTEN);
    }

    /******************************************************************************
     * The method creates the base deck with all the colors, actions, and wild
     * cards. This should all add up to 108 cards.
     ******************************************************************************/
    public void startDeck() {
        for (Color color : cardCOLORS) {
            // 4 number "0" cards of representing each color
            baseDeck.add(new numCard(color, Integer.toString(cardNUMBERS[0])));

            // 72 number cards > "0", 18 for each color
            for (int i = 1; i < cardNUMBERS.length; ++i) {
                baseDeck.add(new numCard(color, Integer.toString(cardNUMBERS[i])));
                baseDeck.add(new numCard(color, Integer.toString(cardNUMBERS[i])));
            }

            // 24 action cards, 2 of each color
            for (String action : actionTypes) {
                baseDeck.add(new actionCard(color, action));
                baseDeck.add(new actionCard(color, action));
            }
        }

        // 8 wild action cards, 4 of each
        for (String wAction : wildTypes) {
            for (int i = 0; i < 4; i++) {
                baseDeck.add(new wildCard(wAction));
            }
        }

    }

    /******************************************************************************
     * Method to retrieve the deck of cards created for the game.
     * @return - the base deck of UNO.
     ******************************************************************************/
    public List<card> getBaseDeck() {
        return this.baseDeck;
    }

    /******************************************************************************
     * Method to add a listener to each card of the provided deck.
     * @param listener - card click listener.
     ******************************************************************************/
    public void addCardListener(cardListener listener) {
        for (card card : baseDeck)
            card.addMouseListener(listener);
    }

}
