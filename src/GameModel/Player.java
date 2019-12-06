package GameModel;

import GameView.card;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 * The player class will represent each player of the game including AI, and
 * keep track of the player's name and hand which is represented in an array.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author Myren Mitchell
 ******************************************************************************/
public class Player {

    // Player name //
    public String name = null;
    // Player hand of cards //
    public List<card> playerHand;
    // Boolean control for player turn //
    public boolean isPlayerTurn = false;
    // Boolean control for one card left //
    public boolean playerSaysUNO = false;
    // Amount of cards player has played //
    private int cardsPlayed = 0;

    /******************************************************************************
     * Empty default class constructor creates player with hand.
     ******************************************************************************/
    public Player() {
        playerHand = new ArrayList<card>();
    }

    /******************************************************************************
     * Default constructor with params. Everyone needs a name, even robots.
     * @param name - the name of the player or computer.
     ******************************************************************************/
    public Player(String name) {
        setName(name);
        playerHand = new ArrayList<card>();
    }

    /******************************************************************************
     * This method is to get the name of a player.
     * @return - name of the player
     ******************************************************************************/
    public String getName() {
        return this.name;
    }

    /******************************************************************************
     * This method is to set name of a player.
     * @param name - name of the player.
     ******************************************************************************/
    public void setName(String name) {
        this.name = name;
    }

    /******************************************************************************
     * This method is to draw a card from deck and add to this players hand.
     * @param card - card drawn.
     ******************************************************************************/
    public void drawCard(card card) {
        playerHand.add(card);
    }

    /******************************************************************************
     * This method is the action of playing a card from the player's hand and
     * discarding.
     * @param card - card played.
     * @return - card played.
     ******************************************************************************/
    public card playCard(card card) {
        this.playerHand.remove(card);
        cardsPlayed++;
        return card;
    }

    /******************************************************************************
     * This method is to return cards in this players hand in Array form.
     * @return - players hand of cards.
     ******************************************************************************/
    public List<card> getPlayerHand() {
        return playerHand;
    }

    /******************************************************************************
     * This method is to return the total amount of cards in players hand.
     * @return - card hand total.
     ******************************************************************************/
    public int getPlayerHandTotal() {
        return playerHand.size();
    }

    /******************************************************************************
     * This method is to check if player has card.
     * @param card - card for play comparison.
     * @return - true if card is in hand - else false.
     ******************************************************************************/
    public boolean hasCard(card card) {
        return playerHand.contains(card);
    }

    /******************************************************************************
     * This method turns game to next player. Like toggle function.
     ******************************************************************************/
    public void nextPlayerTurn() {
        isPlayerTurn = !isPlayerTurn;
    }

    /******************************************************************************
     * This method is checks if it is a players turn
     * @return - true if is this players turn - else false.
     ******************************************************************************/
    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    /******************************************************************************
     * @param turn - is it the player's turn?
     ******************************************************************************/
    public void setPlayerTurn(boolean turn) {
        isPlayerTurn = turn;
    }

    /******************************************************************************
     * This method is checks if a players hand is empty.
     * @return - true if players hand is empty - else false.
     ******************************************************************************/
    public boolean hasCards() {
        return !playerHand.isEmpty();
    }

    /******************************************************************************
     * This method checks if a player has said UNO.
     * @return - true if player said UNO - else false.
     ******************************************************************************/
    public boolean playerSaidUNO() {
        return playerSaysUNO;
    }

    /******************************************************************************
     * This method sets the status of player saying UNO.
     * @param answer - set state of player saying uno.
     ******************************************************************************/
    public void setSayUNO(boolean answer) {
        playerSaysUNO = answer;
    }

    /******************************************************************************
     * This method sets the 7 cards in this players hand.
     ******************************************************************************/
    public void setCards() {
        playerHand = new ArrayList<card>();
    }

    /******************************************************************************
     * This method gets the total of cards in a players hand.
     * @return - card hand total.
     ******************************************************************************/
    public int cardsPlayedTotal() {
        return cardsPlayed;
    }

}
