package GameView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/******************************************************************************
 * The welcomeDialog class represents the game set up to be displayed for
 * players on the welcome screen to select from.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author Myren Mitchell
 ******************************************************************************/
public class welcomeDialog extends JDialog implements ActionListener {
    private String[] data;
    private JTextField nameField;
    private JComboBox<String> modeSetting;
    private JComboBox<String> cardsDealtSetting;
    private JComboBox<String> drawTwoSetting;
    private JButton playBtn;
    private JButton exitBtn;
    private JButton rulesBtn;
    private rulesDialog rules;
    private BufferedImage img;

    public welcomeDialog(JFrame parent) {
        super(parent, true);

        try {
            img = ImageIO.read(new File("src/resources/wBackground.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        setPreferredSize(new Dimension(800, 422));
        data = new String[4];
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(100, 30, 0, 0);


        // Player name //
        JLabel nameLabel = new JLabel("Player name:");
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);
        nameField = new JTextField(10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(nameField, gbc);

        // Mode Dialog //
        JLabel modeLabel = new JLabel("Game Mode (You vs. # of A.I.):");
        modeLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        gbc.insets = new Insets(0, 30, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(modeLabel, gbc);
        String[] modeStrings = {"1", "2", "3"};
        modeSetting = new JComboBox<String>(modeStrings);
        modeSetting.setPreferredSize(new Dimension(110, 20));
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(modeSetting, gbc);

        // Cards Dealt Dialog //
        JLabel cardDLabel = new JLabel("Choose # of cards dealt:");
        cardDLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(cardDLabel, gbc);
        String[] cardDStrings = {"4", "5", "6", "7", "8", "9", "10"};
        cardsDealtSetting = new JComboBox<>(cardDStrings);
        cardsDealtSetting.setPreferredSize(new Dimension(110, 20));
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(cardsDealtSetting, gbc);

        // Special Feature Draw //
        JLabel specialDrawLabel = new JLabel("Stack Draw-2:");
        specialDrawLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(specialDrawLabel, gbc);
        String[] optionString = {"Yes", "No"};
        drawTwoSetting = new JComboBox<String>(optionString);
        drawTwoSetting.setPreferredSize(new Dimension(110, 20));
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(drawTwoSetting, gbc);

        // Buttons //
        rulesBtn = new JButton("UNO Rules");
        rulesBtn.addActionListener(this);
        gbc.insets = new Insets(0, 30, 0, 100);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.5;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(rulesBtn, gbc);

        playBtn = new JButton("Play");
        playBtn.addActionListener(this);
        gbc.insets = new Insets(0, 0, 0, 100);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(playBtn, gbc);

        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(this);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(exitBtn, gbc);

        try {
            JLabel b = new JLabel(new ImageIcon(ImageIO.read(new File("src/resources/wIcon.jpg"))));
            //gbc.fill = GridBagConstraints.LINE_END;
            gbc.insets = new Insets(0, 0, 0, 0);
            gbc.weightx = 0.5;
            gbc.gridx = 6;
            gbc.gridy = 0;
            panel.add(b, gbc);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up //
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 7));
        panel.setVisible(true);
        add(panel);
        setUndecorated(true);
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playBtn) {
            data[0] = (String) modeSetting.getSelectedItem();
            data[1] = (String) cardsDealtSetting.getSelectedItem();
            data[2] = (String) drawTwoSetting.getSelectedItem();
            data[3] = nameField.getText();
            dispose();

        } else if (e.getSource() == rulesBtn) {
            rules = new rulesDialog(null);
            rules.run();
        } else {
            System.exit(0); // EXIT GAME //
        }

    }

    public String[] run() {
        this.setVisible(true);
        return data;
    }

}
