package GameView;

import CardModel.wildCard;
import Interface.gameConstants;

import javax.swing.*;
import java.awt.*;

/******************************************************************************
 * The tablePanel class represents the center table of each game to hold
 * cards discarded.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author Myren Mitchell
 ******************************************************************************/
public class tablePanel extends JPanel implements gameConstants {

    // Card for player comparision - discard pile //
    private card topCard;
    // Table panel //
    private JPanel table;

    /******************************************************************************
     * Default class constructor.
     * @param firstCard first game card.
     ******************************************************************************/
    public tablePanel(card firstCard) {
        setOpaque(false);
        setLayout(new GridBagLayout());

        topCard = firstCard;
        table = new JPanel();
        table.setBackground(new Color(64, 64, 64));

        setUpTable();
        setComponents();
    }

    /******************************************************************************
     * This method sets up the table for the game.
     ******************************************************************************/
    private void setUpTable() {
        table.setPreferredSize(new Dimension(500, 200));
        table.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        table.add(topCard, c);

    }

    /******************************************************************************
     * This method sets the components for the table of the game.
     ******************************************************************************/
    private void setComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 130, 0, 45);
        add(table, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(0, 1, 0, 1);
        add(updatePanel, c);
    }

    /******************************************************************************
     * This method sets the cards played by the players onto the table panel.
     * @param cardPlayed - card played.
     ******************************************************************************/
    public void setCardPlayed(card cardPlayed) {
        table.removeAll();
        topCard = cardPlayed;
        setUpTable();
        setBackgroundColor(cardPlayed);
    }

    /******************************************************************************
     * This method sets the background color of table panel, based on card played.
     * @param cardPlayed - card played.
     ******************************************************************************/
    public void setBackgroundColor(card cardPlayed) {
        Color bground;
        if (cardPlayed.getType() == WILD)
            bground = ((wildCard) cardPlayed).getWildColor();
        else
            bground = cardPlayed.getColor();

        table.setBackground(bground);
    }

}
