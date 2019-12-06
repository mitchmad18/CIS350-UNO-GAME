package GameView;

import javax.swing.*;
import java.awt.*;

/******************************************************************************
 * The updatePanel represents the panel of game updates.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author Myren Mitchell
 ******************************************************************************/
public class updatePanel extends JPanel {

    // Uno game user - player //
    private int user;
    // Uno game AI - player //
    private int A1;
    // Uno game AI - player //
    private int A2;
    // Uno game AI - player //
    private int A3;
    // Uno game  //
    private int rest;
    // Uno game error messages //
    private String error;
    // Uno game message text //
    private String text;
    // Update panels center //
    private int panelCenter;
    // //
    private int gMODE = -1;

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
        String extraText = "";
        int xPos = panelCenter - fm.stringWidth(text) / 2;
        g.setFont(newFont);
        g.drawString(text, xPos, 115);

        text = "Remaining: " + rest;
        xPos = panelCenter - fm.stringWidth(text) / 2;
        g.drawString(text, xPos, 190);

        // Detail
        newFont = new Font("Calibri", Font.PLAIN, 20);
        g.setFont(newFont);
        fm = this.getFontMetrics(newFont);

        if (gMODE == 1) {
            text = "You : " + user + "  AI(1) : " + A1;

        } else if (gMODE == 2) {
            text = "You : " + user + "  AI(1) : " + A1;
            extraText = "AI(2) : " + A2;

        } else if (gMODE == 3) {
            text = "You : " + user + "  AI(1) : " + A1;
            extraText = "AI(2) : " + A2 + "  AI(3) : " + A3;
        }
        xPos = panelCenter - fm.stringWidth(text) / 2;
        g.drawString(text, xPos, 140);

        xPos = panelCenter - fm.stringWidth(extraText) / 2;
        g.drawString(extraText, xPos, 165);


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

        if (gMODE == 1) {
            user = playedCards[0];
            A1 = playedCards[1];
            rest = remaining;
        } else if (gMODE == 2) {
            user = playedCards[0];
            A1 = playedCards[1];
            A2 = playedCards[2];
            rest = remaining;
        } else if (gMODE == 3) {
            user = playedCards[0];
            A1 = playedCards[1];
            A2 = playedCards[2];
            A3 = playedCards[3];
            rest = remaining;
        }
    }

    public void setgMODE(int mode) {
        gMODE = mode;
    }

}
