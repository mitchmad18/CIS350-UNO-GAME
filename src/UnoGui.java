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
    private JButton btnChooseCard;
    private JButton btnMoveRight;
    private JButton btnMoveLeft;
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
        c.gridx = 2;
        c.gridy = 0;
        blankUnoCardLabel.setPreferredSize(new Dimension(70,100));
        panel.add(blankUnoCardLabel,c);

        //Cards in the player's hand
        btnChooseCard = new JButton();
        c.gridx = 1;
        c.gridy = 2;
        btnChooseCard.addActionListener(this);
        btnChooseCard.setPreferredSize(new Dimension(70,100));
        btnChooseCard.setIcon(blankUnoCard);
        panel.add(btnChooseCard,c);

        //Left Arrow Button
        btnMoveLeft = new JButton("<--");
        c.gridx = 0;
        c.gridy = 2;
        btnMoveLeft.addActionListener(this);
        btnMoveLeft.setPreferredSize(new Dimension(70,50));
        panel.add(btnMoveLeft,c);

        //Right Arrow Button
        btnMoveRight = new JButton("-->");
        c.gridx = 2;
        c.gridy = 2;
        btnMoveRight.addActionListener(this);
        btnMoveRight.setPreferredSize(new Dimension(70,50));
        panel.add(btnMoveRight,c);

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
        if(e.getSource()==btnChooseCard)
            System.out.println("You picked a card from your hand");
        if(e.getSource()==btnMoveRight)
            System.out.println("move right");
        if(e.getSource()==btnMoveLeft)
            System.out.println("move left");
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
}