package GameView;

import GameModel.Player;
import Interface.gameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/******************************************************************************
 * The playerPanel class represents each players game panel on the game
 * screen.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author Myren Mitchell
 ******************************************************************************/
public class playerPanel extends JPanel implements gameConstants {

    // Uno game player //
    private Player player;
    // Player name //
    private String pName;
    // Panel layout //
    private Box gameLayout;
    // Button layout //
    private Box controlPane;
    // Card holding layout //
    private JLayeredPane cardHolderPane;
    // Draw button //
    private JButton draw;
    // Say Uno button //
    private JButton sayUNO;
    // Skip turn button //
    private JButton skip;
    // Player label //
    private JLabel nameLabel;
    // Button listener //
    private buttonListener handler;

    /******************************************************************************
     * Default class constructor creating panel for each player.
     * @param newPlayer - player added.
     ******************************************************************************/
    public playerPanel(Player newPlayer) {
        setPlayer(newPlayer);
        gameLayout = Box.createHorizontalBox();
        cardHolderPane = new JLayeredPane();
        cardHolderPane.setPreferredSize(new Dimension(600, 175));

        // card set up //
        setUpCards();
        setControlPane();

        gameLayout.add(cardHolderPane);
        gameLayout.add(Box.createHorizontalStrut(40));
        gameLayout.add(controlPane);
        add(gameLayout);

        // Register Listeners //
        handler = new buttonListener();
        draw.addActionListener(BUTTONLISTEN);
        draw.addActionListener(handler);

        sayUNO.addActionListener(BUTTONLISTEN);
        sayUNO.addActionListener(handler);

        skip.addActionListener(BUTTONLISTEN);
        skip.addActionListener(handler);
    }

    /******************************************************************************
     * This method is used to set up cards for each players hand.
     ******************************************************************************/
    public void setUpCards() {
        cardHolderPane.removeAll();

        // Origin point in center
        Point origin = getPoint(cardHolderPane.getWidth(), player.getPlayerHandTotal());
        int offset = calcOffset(cardHolderPane.getWidth(), player.getPlayerHandTotal());

        int i = 0;
        for (GameView.card card : player.getPlayerHand()) {
            card.setBounds(origin.x, origin.y, card.CSIZE.width, card.CSIZE.height);
            cardHolderPane.add(card, i++);
            cardHolderPane.moveToFront(card);
            origin.x += offset;
        }
        repaint();
    }

    /******************************************************************************
     * This method is used to get the players instance.
     * @return - this player.
     ******************************************************************************/
    public Player getPlayer() {
        return player;
    }

    /******************************************************************************
     * This method is used to set this players instance.
     * @param player - this player.
     ******************************************************************************/
    public void setPlayer(Player player) {
        this.player = player;
        setPlayerName(player.getName());
    }

    /******************************************************************************
     * This method is used to set this players name.
     * @param pName - this players name.
     ******************************************************************************/
    public void setPlayerName(String pName) {
        this.pName = pName;
    }

    /******************************************************************************
     * This method is used to hold/control the buttons within the players
     * card panel.
     ******************************************************************************/
    public void setControlPane() {
        draw = new JButton("Draw");
        sayUNO = new JButton("Say UNO!");
        skip = new JButton("Skip Turn");
        nameLabel = new JLabel(pName);

        // Style
        draw.setBackground(new Color(8, 70, 189));
        draw.setFont(new Font("Arial", Font.BOLD, 20));
        draw.setFocusable(false);

        sayUNO.setBackground(new Color(240, 0, 0));
        sayUNO.setFont(new Font("Arial", Font.BOLD, 20));
        sayUNO.setFocusable(false);

        skip.setBackground(new Color(200, 200, 19));
        skip.setFont(new Font("Arial", Font.BOLD, 20));
        skip.setFocusable(false);

        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));

        controlPane = Box.createVerticalBox();
        controlPane.add(nameLabel);
        controlPane.add(draw);
        controlPane.add(Box.createVerticalStrut(15));
        controlPane.add(skip);
        controlPane.add(Box.createVerticalStrut(15));
        controlPane.add(sayUNO);

    }

    /******************************************************************************
     * This method is used to keep the players cards in place and from overflowing
     * the panel.
     * @param width - player panel width.
     * @param cardTotal - card hand total.
     ******************************************************************************/
    private int calcOffset(int width, int cardTotal) {
        int offset = 71;
        if (cardTotal <= 7) {
            return offset;
        } else {
            double off = (width - 100) / (cardTotal - 1);
            return (int) off;
        }
    }

    /******************************************************************************
     * This method is used to get the point of the mouse.
     * @param width - player panel width.
     * @param cardTotal - player hand total.
     * @return - point.
     ******************************************************************************/
    private Point getPoint(int width, int cardTotal) {
        Point p = new Point(0, 20);
        if (cardTotal >= 7) {
            return p;
        } else {
            p.x = (width - calcOffset(width, cardTotal) * cardTotal) / 2;
            return p;
        }
    }

    /******************************************************************************
     * This class represents the button listeners of the player panel.
     ******************************************************************************/
    class buttonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (player.isPlayerTurn()) {
                if (actionEvent.getSource() == draw)
                    BUTTONLISTEN.drawCard();
                else if (actionEvent.getSource() == sayUNO)
                    BUTTONLISTEN.sayUNO();
                else if (actionEvent.getSource() == skip)
                    BUTTONLISTEN.skip();
            }
        }
    }
}

