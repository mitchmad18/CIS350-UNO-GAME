package GameView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/******************************************************************************
 * The menuBar class creates the different parts of the top menu bar
 *
 * @author Myren Mitchell
 * @author add name
 * @author add name
 ******************************************************************************/
public class menuBar extends JFrame implements ActionListener {

    // Menu barf
    private static JMenuBar mb;
    // Menus displayed in the menu bar
    protected static JMenu mainMenu;
    // Options listed under the menu
    private static JMenuItem quitItem, newGameItem;
    // Action performed key number
    private int item;

    /******************************************************************************
     * Method used to set up the top menu bar
     ******************************************************************************/
    static JMenuBar setUpMenu() {
        mb = new JMenuBar();

        mainMenu = new JMenu("Menu");

        quitItem = new JMenuItem("Quit Game");
        newGameItem = new JMenuItem("New Game");

        quitItem.addActionListener(new menuBar(1));
        newGameItem.addActionListener(new menuBar(2));

        mainMenu.add(quitItem);
        mainMenu.add(newGameItem);

        mb.add(mainMenu);

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

            //start a new frame
            JFrame frame = new gameFrame();
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocation(200, 100);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
