package GameInfo;

import CardInfo.Card;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 * The player class will keep track of the player's name and hand which is
 * represented in an array
 ******************************************************************************/
public class Player {

    // Player name
    public String name;
    // Player hand of cards
    public List<Card> playerHand;
    // Boolean control for player turn
    public boolean isPlayerTurn;
    // Boolean control for one card left
    public boolean playerSaysUNO;
    // Amount of cards player has played
    private int playedCards = 0;

    /******************************************************************************
     * The default constructor of a player. Everyone needs a name, even robots.
     * @param name the name of the player or computer
     ******************************************************************************/
    public Player(String name) {
        setName(name);
        playerHand = new ArrayList<Card>();
    }

    /******************************************************************************
     * @return the name of the player
     ******************************************************************************/
    public String getName() {
        return this.name;
    }

    /******************************************************************************
     * @param name the name of the player to set
     ******************************************************************************/
    public void setName(String name) {
        this.name = name;
    }

    /******************************************************************************
     * @param card the card of specific type being added to players hand.
     ******************************************************************************/
    public void drawCard(Card card) {
        playerHand.add(card);
    }

    /******************************************************************************
     * The action of playing a card from the player's hand.
     * @param card the card that the player will be playing and discarding
     * @return the same card that is passed in
     ******************************************************************************/
    public Card playCard(Card card) {
        this.playerHand.remove(card);
        playedCards++;
        return card;
    }

    /******************************************************************************
     * Method to return cards in this players hand.
     * @return players hand.
     ******************************************************************************/
    public List<Card> getPlayerHand() {
        return playerHand;
    }

    /******************************************************************************
     * Method to return the total amount of cards in players hand.
     * @returns card hand total.
     ******************************************************************************/
    public int getPlayerHandTotal() {
        return playerHand.size();
    }

    /******************************************************************************
     * @param card is the card being compared to the cards in the hand of this player.
     * @return boolean returns true if card is in hand - else false.
     ******************************************************************************/
    public boolean hasCard(Card card) {
        return playerHand.contains(card);
    }

    /******************************************************************************
     * Method turns game to next player.
     ******************************************************************************/
    public void nextPlayerTurn() {
        isPlayerTurn = (isPlayerTurn) ? false : true;
    }

    /******************************************************************************
     * @return true if is this players turn - else false.
     ******************************************************************************/
    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    /******************************************************************************
     * @return true if players hand is empty - else false.
     ******************************************************************************/
    public boolean hasCards() {
        return playerHand.isEmpty() ? false : true;
    }

    /******************************************************************************
     * @return true if player said UNO - else false.
     ******************************************************************************/
    public boolean playerSaidUNO() {
        return playerSaysUNO;
    }

    /******************************************************************************
     * @param answer true/ false to set state of player saying uno.
     ******************************************************************************/
    public void setSayUNO(boolean answer) {
        playerSaysUNO = answer;
    }

    /******************************************************************************
     * Method sets the cards in this players hand.
     ******************************************************************************/
    public void setCards() {
        playerHand = new ArrayList<Card>();
    }

}
