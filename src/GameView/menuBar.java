package GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/******************************************************************************
 * The menuBar class creates the different parts of the top menu bar
 *
 * @author Myren Mitchell
 * @author add name
 * @author add name
 ******************************************************************************/
public class menuBar extends JFrame implements ActionListener {

    // Menu bar
    private static JMenuBar mb;
    // Menus displayed in the menu bar
    protected static JMenu mainMenu, settingsMenu;
    // Options listed under the menu
    private static JMenuItem quitItem, newGameItem, restartItem;
    // Action performed key number
    private int item;

    /******************************************************************************
     * Method used to set up the top menu bar
     ******************************************************************************/
    static JMenuBar setUpMenu() {
        mb = new JMenuBar();

        mainMenu = new JMenu("Menu");
        settingsMenu = new JMenu("Settings");

        quitItem = new JMenuItem("Quit Game");
        newGameItem = new JMenuItem("New Game");
        restartItem = new JMenuItem("Restart");

        quitItem.addActionListener(new menuBar(1));
        newGameItem.addActionListener(new menuBar(2));
        restartItem.addActionListener(new menuBar(3));

        mainMenu.add(quitItem);
        mainMenu.add(newGameItem);
        mainMenu.add(restartItem);

        mb.add(mainMenu);
        mb.add(settingsMenu);

        return mb;
    }

    /******************************************************************************
     * Method used to add action listeners to the menu items
     * @param item - number assigned to each menu item for action events
     ******************************************************************************/
    private menuBar(int item){
        this.item = item;
    }

    /******************************************************************************
     * Method used to manage the actions of selected menu items
     * @param actionEvent - the menu item that has been clicked
     ******************************************************************************/
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(this.item==1){
            System.exit(0);
        }
        else if(this.item==2){
            System.out.println("New Game");
        }
        else if(this.item==3){
            System.out.println("restart");
        }
    }
}
