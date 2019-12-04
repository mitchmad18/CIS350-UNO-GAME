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
    // Player 2 //
    private AIPanel a2;
    // Player 3 //
    private AIPanel a3;
    // Player 4 //
    private AIPanel a4;
    // Uno game table //
    private tablePanel table;
    // Uno game background //
    private BufferedImage img;
    // //
    private Box gameLayout;

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

        gameLayout = Box.createHorizontalBox();
        setPreferredSize(new Dimension(960, 720));
        setLayout(new BorderLayout());
        game = newGame;
        table = new tablePanel(firstCard);
        setPlayers();

        if (game.getGameMode() == 1) {

            p1.setOpaque(false);
            a2.setOpaque(false);
            add(p1, BorderLayout.SOUTH);
            add(table, BorderLayout.CENTER);
            gameLayout.add(a2);
            add(gameLayout, BorderLayout.NORTH);
        } else if (game.getGameMode() == 2) {

            p1.setOpaque(false);
            a2.setOpaque(false);
            a3.setOpaque(false);
            add(p1, BorderLayout.SOUTH);
            add(table, BorderLayout.CENTER);
            gameLayout.add(a2);
            gameLayout.add(a3);
            add(gameLayout, BorderLayout.NORTH);

        } else if (game.getGameMode() == 3) {

            p1.setOpaque(false);
            a2.setOpaque(false);
            a3.setOpaque(false);
            a4.setOpaque(false);
            add(p1, BorderLayout.SOUTH);
            add(table, BorderLayout.CENTER);
            gameLayout.add(a2);
            gameLayout.add(a3);
            gameLayout.add(a4);
            add(gameLayout, BorderLayout.NORTH);
        }
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
        if (game.getGameMode() == 1) {
            p1 = new playerPanel(game.getPlayers()[0]);
            a2 = new AIPanel(game.getPlayers()[1]);
        } else if (game.getGameMode() == 2) {
            p1 = new playerPanel(game.getPlayers()[0]);
            a2 = new AIPanel(game.getPlayers()[1]);
            a3 = new AIPanel(game.getPlayers()[2]);
        } else if (game.getGameMode() == 3) {
            p1 = new playerPanel(game.getPlayers()[0]);
            a2 = new AIPanel(game.getPlayers()[1]);
            a3 = new AIPanel(game.getPlayers()[2]);
            a4 = new AIPanel(game.getPlayers()[3]);
        }
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
            // a2.setUpCards();
        table.revalidate();
        revalidate();
    }
}
