package GameView;

import GameModel.unoGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/******************************************************************************
 * The gameSession class controls a session of the game created.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public class gameSession extends JPanel {

    // Uno game instance //
    private unoGame game;
    // Player 1 //
    private playerPanel p1;
    // Player 2 // - Add more with multi player
    private playerPanel p2;
    // Uno game table //
    private tablePanel table;
    // Uno game background //
    private BufferedImage img;

    /******************************************************************************
     * Default class constructor creating game session.
     * @param newGame - game instance.
     * @param firstCard - first card on discard pile.
     ******************************************************************************/
    public gameSession(unoGame newGame, card firstCard) {

        try {
            img = ImageIO.read(new File("src/resources/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(960, 720));
        setLayout(new BorderLayout());
        game = newGame;
        table = new tablePanel(firstCard);
        setPlayers();
        p1.setOpaque(false);
        p2.setOpaque(false);

        add(p1, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

    }

    /******************************************************************************
     * Method used to set background image of game.
     * @param g - graphics.
     ******************************************************************************/
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

    }

    /******************************************************************************
     * This method sets the players of a game session
     * ** TO BE CORRECTED FOR MULTI-PLAYER IMPLEMENTATION **
     ******************************************************************************/
    private void setPlayers() {
        p1 = new playerPanel(game.getPlayers()[0]);
        p2 = new playerPanel(game.getPlayers()[1]);
    }

    /******************************************************************************
     * This method displays game updates for user.
     * @param cardPlayed - card played
     ******************************************************************************/
    public void updatePanel(card cardPlayed) {
        table.setCardPlayed(cardPlayed);
        refreshPanel();
    }

    /******************************************************************************
     * This method is used to refresh panels with updates.
     ******************************************************************************/
    public void refreshPanel() {
        p1.setUpCards();
        p2.setUpCards();
        table.revalidate();
        revalidate();
    }

}
