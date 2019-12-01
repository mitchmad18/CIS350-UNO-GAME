package GameView;

import Interface.gameConstants;
import gameControl.gameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/******************************************************************************
 * The gameFrame class represents the main frame of the game its panels
 * to be set up and displayed.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author add name
 ******************************************************************************/
public class gameFrame extends JFrame implements gameConstants, ActionListener {

    // Menu bar //
    private static JMenuBar mb;
    // Menus displayed in the menu bar //
    protected static JMenu mainMenu;
    // Options listed under the menu //
    private static JMenuItem quitItem, newGameItem;
    // Uno game session instance //
    private gameSession gamePanel;
    // Uno game controller instance //
    private gameController gameController;

    /******************************************************************************
     * Empty default class constructor.
     ******************************************************************************/
    public gameFrame() {
        newGame();
    }

    public void newGame(){
        gameController = new gameController();
        CARDLISTEN.setController(gameController);
        BUTTONLISTEN.setController(gameController);

        gamePanel = gameController.getSession();
        setMenu();
        setJMenuBar(mb);
        add(gamePanel);
    }

    public void setMenu(){
        mb = new JMenuBar();
        mainMenu = new JMenu("Menu");

        quitItem = new JMenuItem("Quit Game");
        newGameItem = new JMenuItem("New Game");

        quitItem.addActionListener(this);
        newGameItem.addActionListener(this);

        mainMenu.add(quitItem);
        mainMenu.add(newGameItem);
        mb.add(mainMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == quitItem){
            System.exit(0);
        }
        else if(e.getSource() == newGameItem){
            System.out.println("New Game");
            this.dispose();
            JFrame frame = new gameFrame();
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocation(200, 100);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
