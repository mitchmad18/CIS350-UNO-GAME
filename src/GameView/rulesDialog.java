package GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/******************************************************************************
 * The rulesDialog class represents the rules to be displayed for players
 * on the welcome screen
 *
 * ** CLASS TO BE FIXED AND IMPLEMENTED **
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author add name
 ******************************************************************************/
public class rulesDialog extends JDialog implements ActionListener {

    private JButton btnStart;
    private JComboBox numCardsToStart;

    private String[] data;

    public rulesDialog(Frame parent) {
        super(parent, "New Game", true);
        Point loc = parent.getLocation();

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel label1 = new JLabel("Welcome to UNO!");
        c.gridx = 0;
        c.gridy = 0;
        panel.add(label1,c);

        JLabel spacer = new JLabel("");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(spacer,c);

        JLabel label2 = new JLabel("Please select the number of cards to deal:");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(label2,c);

        Integer[] cardsToStart = {5,6,7,8,9,10};
        numCardsToStart = new JComboBox<Integer>(cardsToStart);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(numCardsToStart,c);

        btnStart = new JButton();
        c.gridx = 0;
        c.gridy = 4;
        btnStart.addActionListener(this);
        panel.add(btnStart,c);

        getContentPane().add(panel);
        pack();
    }

    public void actionPerformed(ActionEvent a) {
        Object source = a.getSource();
        if (source == btnStart) {
            data[0] = (String)numCardsToStart.getSelectedItem();
        }
        else {
            data[0] = null;
        }
        dispose();
    }

    public String[] run() {
        this.setVisible(true);
        return data;
    }

}
