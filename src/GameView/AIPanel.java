package GameView;

import GameModel.Player;
import Interface.gameConstants;
import gameControl.buttonListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class AIPanel extends JPanel implements gameConstants {
    // Uno game player //
    private Player player;
    // Player name //
    private String pName;
    // Panel layout //
//    private Box gameLayout;
//    // Button layout //
//    private Box controlPane;
//    // Card holding layout //
//    private JLayeredPane cardHolderPane;
//    // Draw button //
//    private JButton draw;
//    // Say Uno button //
//    private JButton sayUNO;
    // Player label //
    private JLabel nameLabel;
    private JLabel iconLabel;
    // Button listener //
    private buttonListener handler;
    private BufferedImage img;


    public AIPanel(Player newPlayer) {

        try {
            img = ImageIO.read(new File("src/resources/AIIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(120, 140));
        setPlayer(newPlayer);
        nameLabel = new JLabel(pName);
        nameLabel.setForeground(Color.WHITE);
        iconLabel = new JLabel();
        iconLabel.setSize(100, 100);
        Image dimg = img.getScaledInstance(iconLabel.getWidth(), iconLabel.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        iconLabel.setIcon(imageIcon);


        panel.add(iconLabel);
        panel.add(nameLabel);
        panel.setOpaque(false);
        add(panel);
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


}
