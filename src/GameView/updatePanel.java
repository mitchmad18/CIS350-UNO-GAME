package GameView;

import javax.swing.*;
import java.awt.*;

/******************************************************************************
 * The updatePanel represents the panel of game updates.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public class updatePanel extends JPanel {

    // Uno game user - player //
    private int user;
    // Uno game AI - player //
    private int AI;
    // Uno game  //
    private int rest;
    // Uno game error messages //
    private String error;
    // Uno game message text //
    private String text;
    // Update panels center //
    private int panelCenter;

    /******************************************************************************
     * Default class constructor.
     ******************************************************************************/
    public updatePanel() {
        setPreferredSize(new Dimension(275, 200));
        setOpaque(false);
        error = "";
        text = "Game Begins";
        updateText(text);
    }

    /******************************************************************************
     * This method sets the display for the update panel.
     * @param g - graphics.
     ******************************************************************************/
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        panelCenter = getWidth() / 2;
        printMessage(g);
        printError(g);
        printDetail(g);
    }

    /******************************************************************************
     * This method prints the messages to be displayed within the game.
     * @param g - graphics.
     ******************************************************************************/
    private void printMessage(Graphics g) {
        Font newFont = new Font("Calibri", Font.BOLD, 25);

        // Determines word width for position
        FontMetrics fm = this.getFontMetrics(newFont);
        int xPos = panelCenter - fm.stringWidth(text) / 2;

        g.setFont(newFont);
        g.setColor(new Color(255, 255, 255));
        g.drawString(text, xPos, 75);

    }

    /******************************************************************************
     * This method prints the errors displayed within the game.
     * @param g - graphics.
     ******************************************************************************/
    public void printError(Graphics g) {
        if (!error.isEmpty()) {
            Font newFont = new Font("Calibri", Font.PLAIN, 25);

            // Determines word width for position
            FontMetrics fm = this.getFontMetrics(newFont);
            int xPos = panelCenter - fm.stringWidth(error) / 2;

            g.setFont(newFont);
            g.setColor(Color.red);
            g.drawString(error, xPos, 35);
            error = "";
        }
    }

    /******************************************************************************
     * This method prints the details of the game to be displayed.
     * @param g - graphics.
     ******************************************************************************/
    public void printDetail(Graphics g) {
        Font newFont = new Font("Calibri", Font.BOLD, 25);
        FontMetrics fm = this.getFontMetrics(newFont);
        g.setColor(new Color(127, 127, 127));

        // Determines word width for position
        String text = "Cards Played";
        int xPos = panelCenter - fm.stringWidth(text) / 2;
        g.setFont(newFont);
        g.drawString(text, xPos, 120);

        text = "Remaining: " + rest;
        xPos = panelCenter - fm.stringWidth(text) / 2;
        g.drawString(text, xPos, 180);

        // Detail
        newFont = new Font("Calibri", Font.PLAIN, 20);
        g.setFont(newFont);
        fm = this.getFontMetrics(newFont);

        text = "You : " + user + " PC : " + AI;
        xPos = panelCenter - fm.stringWidth(text) / 2;
        g.drawString(text, xPos, 140);

        text = String.valueOf(rest);
        xPos = panelCenter - fm.stringWidth(text) / 2;
        //g.drawString(text, xPos, 190);
    }

    /******************************************************************************
     * This method is used to set the update text.
     * @param update - updated text.
     ******************************************************************************/
    public void updateText(String update) {
        text = update;
    }

    /******************************************************************************
     * This method is used to set the error text.
     * @param errorMessage - error text.
     ******************************************************************************/
    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    /******************************************************************************
     * This method is used to set the game detail text.
     * @param playedCards - played cards of each player.
     * @param remaining - cards remaining in game deck.
     ******************************************************************************/
    public void setDetail(int[] playedCards, int remaining) {
        user = playedCards[1];
        AI = playedCards[0];
        rest = remaining;

    }

}
