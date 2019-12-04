package gameControl;

import CardModel.wildCard;
import GameModel.Player;
import GameModel.unoGame;
import GameView.card;
import GameView.gameSession;
import GameView.welcomeDialog;
import Interface.gameConstants;
import Interface.unoConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/******************************************************************************
 * The gameController class serves as the main control of the game. This class
 * sets the rules to be followed by players and controls.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public class gameController implements gameConstants {

    // Boolean to control player moves //
    private boolean canPlay;
    // Uno game instance //
    private unoGame game;
    // Uno game session instance //
    private gameSession session;
    // Represents cards of discard pile //
    private List<card> cardsPlayed;
    // Represents mode of game //
    private int gameMode;
    // Welcome dialog //
    private welcomeDialog welcome;
    // Welcome dialog data //
    private String[] data = new String[4];

    /******************************************************************************
     * Empty default class constructor
     ******************************************************************************/
    public gameController(JFrame parent) {
        welcomeSetUp(parent);
        cardsPlayed = new ArrayList<card>();

        // First card on discard pile //
        card firstCard = game.getCard();
        if (firstCard.getType() == unoConstants.WILD) {
            while (firstCard.getType() == unoConstants.WILD) {
                game.recreateDealer();
                firstCard = game.getCard();
            }
        }

        changeFirstCard(firstCard);
        cardsPlayed.add(firstCard);
        session = new gameSession(game, firstCard);

        // Get player who's turn it is //
        game.whoseTurn();
        canPlay = true;
        session.refreshPanel();
    }

    /******************************************************************************
     * Method to return mode of the game after user has been prompted to select
     * option. *TO BE CORRECTED for multi-AI ONLY**
     * @return - mode of game.
     ******************************************************************************/
    private void welcomeSetUp(JFrame parent) {
        welcome = new welcomeDialog(parent);
        data = welcome.run();

        // Game mode // Fix game mode entry set up only 1(AI) & 2(MANUAL) work but
        // wrong for now //
        try {
            // Game Mode - num of A.I players //
            gameMode = Integer.parseInt(data[0]);

            // Instantiate new game after mode entered //
            game = new unoGame(gameMode, Integer.parseInt(data[1]), data[2], data[3], this);

        } catch (NumberFormatException e) {
            System.out.println("Error 1");
        }

    }


    /******************************************************************************
     * Method to control the play of cards within the game.
     * @param cardClicked - card selected by player.
     ******************************************************************************/
    public void playCard(card cardClicked) {
        System.out.println(cardClicked.toString());
        // If not player turn - Alert //
        if (!isPlayerTurn(cardClicked)) {
            updatePanel.setError("Sorry, not your turn");
            updatePanel.repaint();
        }

        // If player turn continue //
        else {

            // Check for valid move //
            if (isValidMove(cardClicked)) {

                cardClicked.removeMouseListener(CARDLISTEN);
                cardsPlayed.add(cardClicked);
                game.removeCardPlayed(cardClicked);

                // Action card check //
                if (cardClicked.getType() == ACTION || cardClicked.getType() == WILD) {
                    performActionCard(cardClicked);
                }

                // Special Draw 2 //
                if(cardClicked.getValue() == DRAW2 && cardsPlayed.get(cardsPlayed.size()-2).getValue()
                        != DRAW2 && game.getSpecial() == "Yes"){
                    session.updatePanel(cardClicked);
                    session.refreshPanel();
                    getResults();

                }else {
                    // Switch player turn //
                    session.updatePanel(cardClicked);
                    game.changeTurn();
                    session.refreshPanel();
                    getResults();
                }


            } else {
                updatePanel.setError("Sorry, invalid move");
                updatePanel.repaint();
            }
        }

        // Play for AI //
        if (canPlay()) {
            if (game.isAITurn()) {
                game.playAI(peekTopCard());
                session.refreshPanel();
            }
        }
    }


    /******************************************************************************
     * * Method to set color of WILD card if first on discard pile.
     * ** TO BE CORRECTED TO REPLACE WITH card not of TYPE:WILD**
     * @param firstCard - first card on discard pile.
     ******************************************************************************/
    private void changeFirstCard(card firstCard) {
        firstCard.removeMouseListener(CARDLISTEN);
        if (firstCard.getType() == WILD) {
            int random = new Random().nextInt() % 4;
            try {
                ((wildCard) firstCard).setWildColor(cardCOLORS[Math.abs(random)]);
            } catch (Exception e) {
                System.out.print("Something went wrong changing first card");
            }
        }
    }

    /******************************************************************************
     * Method to get the current game session.
     * @return - this session.
     ******************************************************************************/
    public gameSession getSession() {
        return this.session;
    }

    /******************************************************************************
     * Method to display results of game END for player notification & stop game.
     ******************************************************************************/
    public void getResults() {
        if (game.gameOver()) {
            canPlay = false;
            updatePanel.updateText("GAME OVER");
        }
    }

    /******************************************************************************
     * Method to control play of cards based on player turn.
     * @param cardClicked - card selected by player.
     * @return - true if player turn - else false.
     ******************************************************************************/
    public boolean isPlayerTurn(card cardClicked) {
        for (Player p : game.getPlayers()) {
            if (p.hasCard(cardClicked) && p.isPlayerTurn())
                return true;
        }
        return false;
    }

    /******************************************************************************
     * Method to check for valid card play in game.
     * ** TO BE CORRECTED TESTING FOR BUG FIX **
     * @param cardPlayed - card played.
     * @return - true if valid move - else false.
     ******************************************************************************/
    public boolean isValidMove(card cardPlayed) {
        card topCard = peekTopCard();

//        if(game.getSpecial() == "No") {
        if (cardPlayed.getColor().equals(topCard.getColor())
                || cardPlayed.getValue().equals(topCard.getValue()))
            return true;

        else if (cardPlayed.getType() == WILD)
            return true;

        else if (topCard.getType() == WILD) {
            Color color = ((wildCard) topCard).getWildColor();
            return color.equals(cardPlayed.getColor());
        }
//        } if (game.getSpecial() == "Yes"){
//            if(cardPlayed.getValue().equals(DRAW2)){
//                game.resetSpecial();
//            }
//        }

        return false;
    }

    /******************************************************************************
     * Method to control TYPE: ACTION cards played only.
     * @param action - card action
     ******************************************************************************/
    private void performActionCard(card action) {

        if (action.getValue().equals(DRAW2)) {
            game.drawPlus(2);
//            if(game.getSpecial() == "Yes")
//                game.changeTurn();
        } else if (action.getValue().equals(REVERSE))
            game.changeDirection();

        else if (action.getValue().equals(SKIP))
            game.changeTurn();

        else if (action.getValue().equals(WCOLORPICK)) {
            if (game.isAITurn()) {
                ((wildCard) action).setWildColor(cardCOLORS[game.playAIWild()]);
            } else {
                ((wildCard) action).setWildColor(cardCOLORS[pickColor()]);
            }

        } else if (action.getValue().equals(WDRAW4)) {
            if (game.isAITurn()) {
                ((wildCard) action).setWildColor(cardCOLORS[game.playAIWild()]);
            } else {
                ((wildCard) action).setWildColor(cardCOLORS[pickColor()]);
            }
            game.drawPlus(4);
        }
    }

    public int pickColor() {
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("RED");
        colors.add("YELLOW");
        colors.add("GREEN");
        colors.add("BLUE");

        String colorPicked = (String) JOptionPane.showInputDialog(null,
                "Choose card color", "WILD CARD: ",
                JOptionPane.DEFAULT_OPTION, null, colors.toArray(), RED);

        return colors.indexOf(colorPicked);
    }

    /******************************************************************************
     * Method to draw a card from draw pile and determine if can make a valid move
     * based on discard pile.
     ******************************************************************************/
    public void requestCard() {
        game.drawCard(peekTopCard());

        if (gameMode == AIMode && canPlay()) {
            if (game.isAITurn()) {
                game.playAI(peekTopCard());
            }
        }
        session.refreshPanel();
    }

    /******************************************************************************
     * Method to notify if a player is allowed to make a move.
     * @return - true if can make a move - else false.
     ******************************************************************************/
    public boolean canPlay() {
        return canPlay;
    }

    /******************************************************************************
     * Method to get the top card of the discard pile.
     * @return - card for play comparison.
     ******************************************************************************/
    public card peekTopCard() {
        return cardsPlayed.get(cardsPlayed.size() - 1);
    }

    /******************************************************************************
     * Method to set if a player says UNO.
     ******************************************************************************/
    public void returnSaidUNO() {
        game.setSaidUNO();
    }

    /******************************************************************************
     * Fix for mult player mode > 1 after special skip
     ******************************************************************************/
    public void sendSkip() {
        if (canPlay)
            game.changeTurn();

        if (game.isAITurn()) {
            game.playAI(peekTopCard());
        }
    }

}
