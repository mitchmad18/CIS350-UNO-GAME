package GameModel;

import CardModel.cardDeck;
import GameView.card;
import Interface.gameConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/******************************************************************************
 * The cardDealer class serves dealer of the game. This class is to be used
 * to deal cards from the deck to the players.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public class cardDealer implements gameConstants {

    // Card deck instance - start deck//
    private cardDeck deck;
    // Shuffled deck ready for play use //
    private List<card> cardStack;
    // Number of cards dealt //
    private int numDealt = firstDeal;

    /******************************************************************************
     * Empty default class constructor.
     ******************************************************************************/
    public cardDealer() {
        this.deck = new cardDeck();
    }

    /******************************************************************************
     * This method takes the start deck of cards and shuffles them randomly.
     * @return shuffled deck of cards.
     * ******************************************************************************/
    public List<card> shuffle() {
        List<card> startDeck = deck.getBaseDeck();
        List<card> shuffledDeck = new ArrayList<card>();

        while (!startDeck.isEmpty()) {
            int deckTotal = startDeck.size();
            Random rnd = new Random();
            int pos = (Math.abs(rnd.nextInt())) % deckTotal;

            card randCard = startDeck.get(pos);
            startDeck.remove(pos);
            shuffledDeck.add(randCard);
        }

        cardStack = new ArrayList<card>();
        for (card card : shuffledDeck) {
            cardStack.add(card);
        }

        return cardStack;
    }

    /******************************************************************************
     * This method will distribute 7 cards to each player of the game.
     * @param players - players of the game.
     ******************************************************************************/
    public void dealCards(Player[] players) {

        try {
            if (cardStack.size() == 0) {
                this.shuffle();
            }

            for (int i = 0; i < numDealt; i++) {
                for (Player p : players) {
                    p.drawCard(drawCard());

                }
            }
        } catch (Exception var4) {
            System.out.println("NO cards to shuffle. All cards are gone.");
        }
    }

    /******************************************************************************
     * Method to draw a card from the card deck.
     * @return - card drawn from deck.
     ******************************************************************************/
    public card drawCard() {
        card drawnCard = cardStack.get(cardStack.size() - 1);
        cardStack.remove(cardStack.size() - 1);
        return drawnCard;
    }

    /******************************************************************************
     * Method to set the number of cards dealt at game start.
     * @param numberToDeal - number of cards to deal.
     ******************************************************************************/
    public void setFirstDeal(int numberToDeal) {
        numDealt = numberToDeal;
    }

}
