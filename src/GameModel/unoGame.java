package GameModel;

import CardModel.wildCard;
import GameView.card;
import Interface.gameConstants;

import javax.swing.*;
import java.util.List;

/******************************************************************************
 * The unoGame class serves as an instance of the game. Sets up game, players
 * and etc.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public class unoGame implements gameConstants {

    // Card deck shuffled - ready //
    private static List<card> cardStack;
    // Players of game //
    private Player[] players;
    // Game mode //
    private int gameMODE;
    // AI instance //
    private AI AI;
    // Card dealer instance //
    private cardDealer dealer;
    // game check //
    private boolean gameOver;

    /******************************************************************************
     * Default class constructor creates instance of UNO game.
     * @param mode - game mode.
     ******************************************************************************/
    public unoGame(int mode) {
        gameMODE = mode;

        // Create game players //
        String name = (gameMODE == manualMode) ? JOptionPane.showInputDialog("Player 1") : "AI";
        String name2 = JOptionPane.showInputDialog("Player 2");
        // Add more player names here later //

        if (gameMODE == AIMode)
            AI = new AI();

        Player p1 = (gameMODE == AIMode) ? AI : new Player(name);
        Player p2 = new Player(name2);
        p2.nextPlayerTurn();
        players = new Player[]{p1, p2};

        // Create game dealer //
        dealer = new cardDealer();
        cardStack = dealer.shuffle();
        dealer.dealCards(players);
        gameOver = false;
    }

    public void recreateDealer() {
        dealer = new cardDealer();
        cardStack = dealer.shuffle();
        for (Player p : players) {
            p.playerHand.clear();
        }
        dealer.dealCards(players);
    }

    /******************************************************************************
     * This method retrieves the players of this game instance.
     * @return - players.
     ******************************************************************************/
    public Player[] getPlayers() {
        return players;
    }

    /******************************************************************************
     * This method gets a card from the deck.
     * @return - card drawn.
     ******************************************************************************/
    public card getCard() {
        return dealer.drawCard();
    }

    /******************************************************************************
     * This method plays and discards the card played by a player.
     * @param cardPlayed - card played from hand.
     ******************************************************************************/
    public void removeCardPlayed(card cardPlayed) {
        for (Player p : players) {
            if (p.hasCard(cardPlayed)) {
                p.playCard(cardPlayed);

                if (p.getPlayerHandTotal() == 1 && !p.playerSaidUNO()) {
                    updatePanel.setError(p.getName() + " Forgot to say UNO");
                    p.drawCard(getCard());
                    p.drawCard(getCard());
                } else if (p.getPlayerHandTotal() > 2) {
                    p.setSayUNO(false);
                }
            }
        }
    }

    /******************************************************************************
     * This method draws card and checks for play availability.
     * @param topCard - card for play comparision.
     ******************************************************************************/
    public void drawCard(card topCard) {
        boolean canPlay = false;

        for (Player p : players) {
            if (p.isPlayerTurn()) {
                card newCard = getCard();
                p.drawCard(newCard);
                canPlay = canPlay(topCard, newCard);
                System.out.println(p.getName() + " drew " + newCard.toString());
                break;
            }
        }
        if (!canPlay)
            changeTurn();
        else
            playAI(topCard);
    }

    /******************************************************************************
     * This method is used for the "DRAW2" and "DRAW4" cards. Adds cards to a
     * players hand.
     * @param numCards - number of cards to be drawn.
     ******************************************************************************/
    public void drawPlus(int numCards) {
        for (Player p : players) {
            if (!p.isPlayerTurn()) {
                for (int i = 0; i < numCards; i++)
                    p.drawCard(getCard());
            }
        }
    }

    /******************************************************************************
     * This method lets the game know which player is in turn.
     ******************************************************************************/
    public void whoseTurn() {
        for (Player p : players) {
            if (p.isPlayerTurn()) {
                updatePanel.updateText(p.getName() + "'s Turn");
                System.out.println(p.getName() + "'s Turn. Hand Count: " + p.getPlayerHandTotal());
            }
        }
        updatePanel.setDetail(cardsPlayedSize(), cardsRemaining());
        updatePanel.repaint();
    }

    /******************************************************************************
     * This methods switches the players turns and alerts the game.
     ******************************************************************************/
    public void changeTurn() {
        for (Player p : players) {
            p.nextPlayerTurn();
        }
        whoseTurn();
    }

    /******************************************************************************
     * This method checks for the AI's turn.
     * @return - true if AI can make a move - else false.
     ******************************************************************************/
    public boolean isAITurn() {
        return AI.isPlayerTurn();
    }

    /******************************************************************************
     * This methods controls the play for AI turn.
     * @param topCard - card for play comparison.
     ******************************************************************************/
    public void playAI(card topCard) {
        if (AI.isPlayerTurn()) {
            boolean done = AI.play(topCard);

            if (!done) {
                drawCard(topCard);
            }
        }
    }

    /******************************************************************************
     * This method returns the cards remaining in the shuffled deck.
     * @return - cards remaining for play.
     ******************************************************************************/
    public int cardsRemaining() {
        return cardStack.size();
    }

    /******************************************************************************
     * This method returns the number of cards played for each player.
     * @return - cards played size.
     ******************************************************************************/
    public int[] cardsPlayedSize() {
        int[] n = new int[2];
        int i = 0;
        for (Player p : players) {
            n[i] = p.cardsPlayedTotal();
            i++;
        }
        return n;
    }

    /******************************************************************************
     * This method checks the card deck size and player hand size for game
     * over status.
     * @return - true if end of game - else false.
     ******************************************************************************/
    public boolean gameOver() {
        if (cardStack.isEmpty()) {
            gameOver = true;
            return gameOver;
        }

        for (Player p : players) {
            if (!p.hasCards()) {
                gameOver = true;
                break;
            }
        }
        return gameOver;
    }

    /******************************************************************************
     * This method checks if a card can be played into the game.
     * @param topCard - card for play comparison.
     * @param cardPlayed - card played.
     * @return - true if card can be played - else false.
     ******************************************************************************/
    public boolean canPlay(card topCard, card cardPlayed) {

        if (topCard.getColor().equals(cardPlayed.getColor())
                || topCard.getValue().equals(cardPlayed.getValue()))
            return true;

        else if (topCard.getType() == WILD)
            return ((wildCard) topCard).getWildColor().equals(cardPlayed.getColor());

        else if (cardPlayed.getType() == WILD)
            return true;

        return false;
    }

    /******************************************************************************
     * This method checks if a player has said UNO.
     ******************************************************************************/
    public void checkSaidUNO() {
        for (Player p : players) {
            if (p.isPlayerTurn()) {
                if (p.getPlayerHandTotal() == 1 && !p.playerSaidUNO()) {
                    updatePanel.setError(p.getName() + " Forgot to say UNO");
                    p.drawCard(getCard());
                    p.drawCard(getCard());
                }
            }
        }
    }

    /******************************************************************************
     * This method sets a player saying UNO.
     ******************************************************************************/
    public void setSaidUNO() {
        for (Player p : players) {
            if (p.isPlayerTurn()) {
                if (p.getPlayerHandTotal() == 2) {
                    p.setSayUNO(true);
                    updatePanel.setError(p.getName() + " said UNO");
                }
            }
        }
    }

}
