import javafx.scene.control.RadioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesDialog extends JDialog implements ActionListener {

    private JButton btnStart;
    private JComboBox numCardsToStart;

    public RulesDialog(Frame parent) {
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

        JRadioButton radYes = new JRadioButton();
        JRadioButton radNo = new JRadioButton();


    }

    public void actionPerformed(ActionEvent a) {

    }

}
