package GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/******************************************************************************
 * The rulesDialog class represents the rules to be displayed for players
 * on the welcome screen and in the game menu bar.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author Myren Mitchell
 ******************************************************************************/
public class rulesDialog extends JDialog implements ActionListener {

    private JButton closeBtn;
    private JLabel rulesLabel;

    public rulesDialog(Frame parent) {
        super(parent, true);
        setPreferredSize(new Dimension(750, 750));
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(30, 30, 30, 30);

        rulesLabel = new JLabel();
        final String myString = "                    UNO RULES                             " +
                "<BR>" +
                "GAME OVERVIEW:<BR>" +
                "UNO is a fast paced card game for two to ten players  ages seven  and up. " +
                "The idea of the game is to get to your last card, shout �UNO!� before another " +
                "player realizes you didn�t, and then attempt to play your last card on your turn " +
                "to win the round. The player to use up his or her last card wins the round and is " +
                "scored based on the cards left in their opponent�s hand." +
                "<BR>" +
                "<BR>" +
                "SETUP:<BR>" +
                "Each player is dealt seven cards, with the remaining cards placed face down to form " +
                "a draw pile. Take the top card from the draw pile and place it face up to begin a " +
                "discard pile." +
                "<BR>" +
                "<BR>" +
                "GAME PLAY:<BR>" +
                "The player on the left of the dealer starts the round by matching a card in his hand " +
                "by color or number  with the top card on the discard pile.  If he cannot, then he must " +
                "draw a card.  He can play the newly drawn card if able,  but the turn ends whether he plays " +
                "the card or not.<BR>" +
                "Matching is defined as placing a card of the same color or number on top of the top card of " +
                "the discard pile. For example,  a blue two may be placed upon a yellow two. Likewise a yellow " +
                "draw two and a blue draw two may be played on top of each other. A green three placed upon a " +
                "green five is also a legal play. The only cards that are not required to match to be played are" +
                " Wild and Wild Draw 4 cards.<BR>" +
                "A player may choose not to play a card and to draw a card instead. They may play the card they " +
                "drew, but may not play a card from their hand after drawing a card in this way. Play continues " +
                "until one player runs out of cards thus ending the round. If the draw pile ever runs out of cards " +
                "then the discard pile, minus the top card, is shuffled and turned back over to replenish the draw " +
                "pile." +
                "<BR>" +
                "<BR>" +
                "CONTENTS:<BR>" +
                "There are 108 cards in total, 4 colors and 19 cards in each color (0 to 9), 24 action cards, and " +
                "8 wild cards." +
                "<BR>" +
                "<BR>" +
                "(ACTION CARDS):<BR>" +
                "-DRAW TWO:<BR>" +
                "This card forces the next player to draw 2 cards. If you are playing heads up (two players) then" +
                " this card also skips your opponent. If turned up as the first card in play, then the first " +
                "person to play also draws 2 cards. If playing heads up, this card also skips your opponent in " +
                "addition to its normal effect.<BR>" +
                "-REVERSE:<BR>" +
                "This will reverse the direction of play originating from the person who played the card. If play " +
                "was moving right, then now it is moving left. If this card is placed face up at the beginning of " +
                "play, then the play is reversed and the dealer goes first. If playing heads up then this card " +
                "simply skips your opponent.<BR>" +
                "-SKIP:<BR>" +
                "Skips the next player to play. If placed face up at the beginning of play, this card skips the first " +
                "player to the left of the dealer." +
                "<BR>" +
                "-WILD CARD:<BR>" +
                "This card can be played on any color at any time. Once played, the person who played the card calls a " +
                "color. The discard pile is now that color and play continues. If this card is the first card played " +
                "then the person to the left of the dealer calls the color.<BR>" +
                "-WILD DRAW 4:<BR>" +
                "This card may only be played legally if you have no other card that matches the COLOR of the discard " +
                "pile. If you choose to play this card while not meeting that specific requirement, then your opponents " +
                "have the option to call you on it. Please see special rules below for penalties. Once the card is laid " +
                "down, a color is called by the issuer, and the next person to play draws 4 cards. If this card is " +
                "turned up at the beginning of play, it is shuffled into the deck and another card is drawn to replace " +
                "it. Wild Draw 4�s cannot start the game." +
                "<BR>" +
                "<BR>" +
                "PENALTY BOX:<BR>" +
                "Players must call out �UNO!� when their second to last card is played. Failure to do so can result " +
                "in 2 additional cards being drawn if your opponents realize your omission.  You may say it at any time " +
                "after this point, but if your opponents catch you before you do then you are penalized just the same.";

        final String html = "<html><body style='width: %1spx'>%1s";

        rulesLabel.setText(String.format(html, 500, myString));
        rulesLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(rulesLabel, gbc);
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(getWidth(), getHeight()));

        closeBtn = new JButton("CLOSE");
        closeBtn.addActionListener(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(closeBtn, gbc);

        // Set up //
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 7));
        panel.setVisible(true);
        add(scrollPane);
        setUndecorated(true);
        pack();
    }


    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == closeBtn) {
            hide();
        }
    }

    public void run() {
        this.setVisible(true);
    }

}
