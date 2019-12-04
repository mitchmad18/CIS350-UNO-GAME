package GameModel;

import CardModel.wildCard;
import GameView.card;
import Interface.gameConstants;
import gameControl.gameController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private AI[] AI;
    // Card dealer instance //
    private cardDealer dealer;
    // game check //
    private boolean gameOver = false;
    // //
    private gameController gc;
    // Game Direction //
    private Direction dir = Direction.RIGHT;
    // Special draw helper //
    private String setSpecial = "No";
//    // Special draw turn helper //
//    private int specialDone

    /******************************************************************************
     * Default class constructor creates instance of UNO game.
     * @param mode - game mode.
     ******************************************************************************/
    public unoGame(int mode, int cardsDealt, String specialDraw, String pName, gameController gc) {
        updatePanel.setgMODE(mode);
        gameMODE = mode;
        this.gc = gc;

        // Create game players //
        String name;
        if (pName.equals("")) {
            name = "No name";
        } else {
            name = pName;
        }
        Player p1 = new Player(name);
        Player p2 = null;
        Player p3 = null;
        Player p4 = null;
        AI = new AI[3];

        for (int i = 0; i < gameMODE; i++) {

            if (i == 0) {
                AI[0] = new AI("AI-1");
                p2 = AI[0];
            } else if (i == 1) {
                AI[1] = new AI("AI-2");
                p3 = AI[1];
            } else if (i == 2) {
                AI[2] = new AI("AI-3");
                p4 = AI[2];
            } else
                System.out.println("Something went wrong.");
        }

        p1.nextPlayerTurn(); // FIX PLAYER ROTATION
        if (mode == 1)
            players = new Player[]{p1, p2};
        else if (mode == 2)
            players = new Player[]{p1, p2, p3};
        else if (mode == 3)
            players = new Player[]{p1, p2, p3, p4};

        // Create game dealer //
        dealer = new cardDealer();
        getDealer().setFirstDeal(cardsDealt);
        setSpecial = specialDraw; // IMPLEMENT
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
        Player next;
        for (Player p : players) {
            if (p.isPlayerTurn()) {
                next = getNextPlayer();
                for (int i = 0; i < numCards; i++)
                    next.drawCard(getCard());
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
        Player next;

        for(Player p: players){
            if(p.isPlayerTurn()){
                next = getNextPlayer();
                p.nextPlayerTurn();
                next.nextPlayerTurn();
                whoseTurn();
                break;
            }
        }

        if (isAITurn()) {
            playAI(gc.peekTopCard());
            gc.getSession().refreshPanel();
        }
    }

    /******************************************************************************
     * This method checks for the AI's turn.
     * @return - true if AI can make a move - else false.
     ******************************************************************************/
    public boolean isAITurn() {

        for (int i = 0; i < gameMODE; i++) {
            if (AI[i].isPlayerTurn())
                return true;
        }
        return false;
    }

    /******************************************************************************
     * This methods controls the play for AI turn.
     * @param topCard - card for play comparison.
     ******************************************************************************/
    public void playAI(card topCard) {
        for (int i = 0; i < gameMODE; i++) {
            if (AI[i].isPlayerTurn()) {
                boolean done = AI[i].play(topCard, gc);

                if (!done) {
                    drawCard(topCard);
                }
            }
        }
    }

    public int playAIWild() {
        for (int i = 0; i < gameMODE; i++) {
            if (AI[i].isPlayerTurn()) {
                return AI[i].pickWildColor();
            }
        }
        return -1;
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
        int[] n = new int[4];
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
        }

        for (Player p : players) {
            if (!p.hasCards()) {
                gameOver = true;
                JOptionPane.showMessageDialog(null,p.getName() + " WINS!",
                        "GAME OVER", JOptionPane.OK_OPTION);
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

        else return cardPlayed.getType() == WILD;

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

    /******************************************************************************
     * This method returns the instance of this UNO games dealer.
     * @return - card dealer.
     ******************************************************************************/
    public cardDealer getDealer() {
        return dealer;
    }


    /******************************************************************************
     * For game reverse.
     ******************************************************************************/
    public void changeDirection() {
        if (dir == Direction.RIGHT) {
            dir = Direction.LEFT;

        } else if (dir == Direction.LEFT) {
            dir = Direction.RIGHT;
        }
    }

    public String getSpecial() {
        return setSpecial;
    }

//    public void getSpecialTurnDone(){
//        return
//    }
//    public void setSpecialTurnDone() {
//        setSpecial = "No";
//    }

    public int getGameMode() {
        return gameMODE;
    }

    public Player getNextPlayer() {

        // Right Direction
        if (dir == Direction.RIGHT) {
            for (Player p : players) {

                // Mode = 1 //
                if (gameMODE == 1)
                    if (p.isPlayerTurn() && p == players[0]) {
                        return players[1];
                    } else if (p.isPlayerTurn() && p == players[1]) {
                        return players[0];
                    }

                // Mode = 2 //
                if (gameMODE == 2) {
                    if (p.isPlayerTurn() && p == players[0]) {
                        return players[1];
                    } else if (p.isPlayerTurn() && p == players[1]) {
                        return players[2];
                    } else if (p.isPlayerTurn() && p == players[2]) {
                        return players[0];
                    }
                }

                // Mode = 3 //
                if (gameMODE == 3) {
                    if (p.isPlayerTurn() && p == players[0]) {
                        return players[1];
                    } else if (p.isPlayerTurn() && p == players[1]) {
                        return players[2];
                    } else if (p.isPlayerTurn() && p == players[2]) {
                        return players[3];
                    } else if (p.isPlayerTurn() && p == players[3]) {
                        return players[0];
                    }
                }
            }
        }

//        // Left Direction
        if (dir == Direction.LEFT) {
            for (Player p : players) {

                // Mode = 1 //
                if (gameMODE == 1)
                    if (p.isPlayerTurn() && p == players[0]) {
                        return players[1];
                    } else if (p.isPlayerTurn() && p == players[1]) {
                        return players[0];
                    }

                // Mode = 2 //
                if (gameMODE == 2) {
                    if (p.isPlayerTurn() && p == players[0]) {
                        return players[2];
                    } else if (p.isPlayerTurn() && p == players[2]) {
                        return players[1];
                    } else if (p.isPlayerTurn() && p == players[1]) {
                        return players[0];
                    }
                }

                // Mode = 3 //
                if (gameMODE == 3) {
                    if (p.isPlayerTurn() && p == players[0]) {
                        return players[3];
                    } else if (p.isPlayerTurn() && p == players[3]) {
                        return players[2];
                    } else if (p.isPlayerTurn() && p == players[2]) {
                        return players[1];
                    } else if (p.isPlayerTurn() && p == players[1]) {
                        return players[0];
                    }
                }
            }
        }
        return players[0];
    }
}
