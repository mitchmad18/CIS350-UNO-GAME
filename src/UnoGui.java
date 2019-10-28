<<<<<<< HEAD
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

/******************************************************************************
 * The UnoGui is where all the visuals of the game will be created and create
 * the game, the settings, and the windows
 ******************************************************************************/
public class UnoGui implements ActionListener {

  /**
   * Main frame of the game
   **/
  private JFrame f;
  /**
   * The main menu bar
   **/
  private JMenuBar mb;
  /**
   * The main menu
   **/
  private JMenu file;
  /**
   * The main menu items
   **/
  private JMenuItem reset, exit;
  /**
   * The button to confirm a card
   **/
  private JButton btnDrawCard;
  /**
   * A JPanel
   **/
  private JPanel panel;

  //Variables for the rules dialog box
  /**
   * The range of cards that can be dealt between 5-10
   **/
  private Integer[] optionsNumOfCardsDealt = new Integer[]{5, 6, 7, 8, 9, 10};
  /**
   * The option box that will let the player choose how many cards to start with
   **/
  private SpinnerListModel sLMNumOfCardsDealt = new SpinnerListModel(optionsNumOfCardsDealt);
  /**
   * The number of cards to be dealt
   **/
  private JSpinner numOfCardsDealt = new JSpinner(sLMNumOfCardsDealt);

  /******************************************************************************
   * The UnoGUI constructor that will create the layout of the game, the
   * actionlisteners, and images
   ******************************************************************************/
  private UnoGui() {
    f = new JFrame("Uno Game");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new JPanel();
    panel.setLayout(new GridBagLayout());

    reset = new JMenuItem("New Game");
    exit = new JMenuItem("Exit");

    reset.addActionListener(this);
    exit.addActionListener(this);

    mb = new JMenuBar();
    file = new JMenu("File");

    file.add(reset);
    file.add(exit);
    mb.add(file);

    f.add(mb);
    f.setJMenuBar(mb);
    f.setSize(800, 600);
    f.setVisible(true);

    GridBagConstraints c = new GridBagConstraints();

    ImageIcon blankUnoCard = new ImageIcon(
        "C:/Users/madie/Documents/GitHub/CIS350-UNO-GAME/src/resources/blankUnoCardSmall.png");

    //Sets up the draw pile
    btnDrawCard = new JButton();
    c.gridx = 0;
    c.gridy = 0;
    btnDrawCard.addActionListener(this);
    btnDrawCard.setPreferredSize(new Dimension(70, 100));
    btnDrawCard.setIcon(blankUnoCard);
    panel.add(btnDrawCard, c);

    //Sets up the discard pile
    JLabel blankUnoCardLabel = new JLabel();
    blankUnoCardLabel.setIcon(blankUnoCard);
    blankUnoCardLabel.setPreferredSize(new Dimension(70, 100));
    panel.add(blankUnoCardLabel);

    f.getContentPane().add(panel);
    f.setVisible(true);

    resetGame();
  }

  /******************************************************************************
   * The actionlistener will do things depending on what is being clicked.
   * @param e The action that is being done
   ******************************************************************************/
  public void actionPerformed(ActionEvent e) {
      if (e.getSource() == reset) {
          resetGame();
      }
      if (e.getSource() == exit) {
          f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
      }
      if (e.getSource() == btnDrawCard) {
          System.out.println("You drew a card.");
      }
  }

  /******************************************************************************
   * A main method that initiates the UnoGui() class
   ******************************************************************************/
  public static void main(String[] args) {
    new UnoGui();
  }

  /******************************************************************************
   * A method that will reset the game back to the base
   ******************************************************************************/
  //Resets the gameboard and prompts the rules popup menu
  private void resetGame() {
    JOptionPane.showConfirmDialog(null,
        dialogOptions, "My custom dialog", JOptionPane.PLAIN_MESSAGE);

  }

  private void drawCard() {

  }

  /**
   * A radio button that says No
   **/
  JRadioButton noStack = new JRadioButton("No");
  /**
   * The setting that will allow draw two's to be stacked
   */
  ButtonGroup stackDraw2Options = new ButtonGroup();

  //Sets the options for the rules dialog box
  /******************************************************************************
   * A dialog box that will appear when first opening up the GUI. It has the menu
   * items to set up the game.
   ******************************************************************************/
  private final JComponent[] dialogOptions = new JComponent[]{
      new JLabel("Welcome to UNO!"),
      new JLabel("Please select the rules you would like to use:"),
      new JLabel("\nHow many cards should be dealt?"),
      numOfCardsDealt,
      new JLabel("Would you like to allow Draw 2 cards to be stacked?")
  };
=======
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UnoGui extends JPanel implements ActionListener {

    //Variables for the GUI
    private JFrame f;
    private JMenuBar mb;
    private JMenu file;
    private JMenuItem reset, exit;
    private JButton btnDrawCard;
    private JPanel panel;

    //Variables for the rules dialog box
    private Integer[] optionsNumOfCardsDealt = new Integer[] {5,6,7,8,9,10};
    private SpinnerListModel sLMNumOfCardsDealt = new SpinnerListModel(optionsNumOfCardsDealt);
    private JSpinner numOfCardsDealt = new JSpinner(sLMNumOfCardsDealt);

    private JRadioButton stackDraw2Yes = new JRadioButton("Yes");
    private JButton stackDraw2No = new JButton("No");

    private UnoGui(){
        f=new JFrame("Uno Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        reset=new JMenuItem("New Game");
        exit=new JMenuItem("Exit");

        reset.addActionListener(this);
        exit.addActionListener(this);

        mb=new JMenuBar();
        file=new JMenu("File");

        file.add(reset);
        file.add(exit);
        mb.add(file);

        f.add(mb);
        f.setJMenuBar(mb);
        f.setSize(800,600);
        f.setVisible(true);

        JComboBox numOfCardsToDeal = new JComboBox(optionsNumOfCardsDealt);
        numOfCardsToDeal.setSelectedIndex(3);
        numOfCardsToDeal.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();

        ImageIcon blankUnoCard = new ImageIcon("C:/Users/madie/Documents/GitHub/CIS350-UNO-GAME/src/resources/blankUnoCardSmall.png");

        //Sets up the draw pile
        btnDrawCard = new JButton();
        c.gridx = 0;
        c.gridy = 0;
        btnDrawCard.addActionListener(this);
        btnDrawCard.setPreferredSize(new Dimension(70,100));
        btnDrawCard.setIcon(blankUnoCard);
        panel.add(btnDrawCard,c);

        //Sets up the discard pile
        JLabel blankUnoCardLabel = new JLabel();
        blankUnoCardLabel.setIcon(blankUnoCard);
        blankUnoCardLabel.setPreferredSize(new Dimension(70,100));
        panel.add(blankUnoCardLabel);

        f.getContentPane().add(panel);
        f.setVisible(true);

        resetGame();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==reset)
            resetGame();
        if(e.getSource()==exit)
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
        if(e.getSource()==btnDrawCard)
            System.out.println("You drew a card.");
    }
    public static void main(String[] args) {
        new UnoGui();
    }

    //Resets the gameboard and prompts the rules popup menu
    private void resetGame() {
        JOptionPane.showConfirmDialog(null,
                dialogOptions, "New Game", JOptionPane.DEFAULT_OPTION);
    }

    private void drawCard() {

    }

    //Sets the options for the rules dialog box
    private final JComponent[] dialogOptions = new JComponent[]{
            new JLabel("Welcome to UNO! Please select the rules you would like to use:"),
            new JLabel("\nHow many cards should be dealt?"),
                numOfCardsDealt,
            new JLabel("Would you like to allow Draw 2 cards to be stacked?")

    };
>>>>>>> 70c60de16aca4aec88b1f66a4691e449e6a4b674
}