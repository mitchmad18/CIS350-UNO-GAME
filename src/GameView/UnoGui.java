package GameView;

import GameInfo.RulesDialog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UnoGui extends JPanel implements ActionListener {

    //Variables for the GUI
    private JFrame f;
    private JMenuBar mb;
    private JMenu file;
    private JMenuItem reset, exit;
    private JLabel discardPileLab;
    private JButton btnDrawCard;
    private JButton btnChooseCard;
//    private JButton btnMoveRight;
//    private JButton btnMoveLeft;
    private JPanel menuPanel;
    private JPanel pilePanel;
    private JPanel playerPanel;
    private JPanel allPanels;

    private RulesDialog rDialog;

    //Variables for the rules dialog box
    private Integer[] optionsNumOfCardsDealt = new Integer[] {5,6,7,8,9,10};
    private SpinnerListModel sLMNumOfCardsDealt = new SpinnerListModel(optionsNumOfCardsDealt);
    private JSpinner numOfCardsDealt = new JSpinner(sLMNumOfCardsDealt);

    private JRadioButton stackDraw2Yes = new JRadioButton("Yes");
    private JButton stackDraw2No = new JButton("No");

    private UnoGui(){
        f=new JFrame("Uno Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setDisplay();
        setMenu();
        setCards();

        //f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
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
//        if(e.getSource()==btnMoveRight)
//            System.out.println("move right");
//        if(e.getSource()==btnMoveLeft)
//            System.out.println("move left");
    }
    public static void main(String[] args) {
        new UnoGui();
    }

    public void setDisplay(){
        // Set screen layout
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       f.setSize(screenSize.width-4, screenSize.height-4);
       f.validate();

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("resources/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(f.getWidth(), f.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        JLabel bground = new JLabel(imageIcon);
        bground.setLayout(new GridBagLayout());
        f.setContentPane(bground);
        f.setUndecorated(true);
    }

    public void setMenu(){
        menuPanel = new JPanel();
        rDialog = new RulesDialog(f);

        menuPanel.setLayout(new GridBagLayout());
        reset=new JMenuItem("New Game");
        exit=new JMenuItem("Exit");
        mb=new JMenuBar();
        file=new JMenu("File");

        reset.addActionListener(this);
        exit.addActionListener(this);

        file.add(reset);
        file.add(exit);
        mb.add(file);

        f.setJMenuBar(mb);
        //f.setSize(1000,1000);
    }

    public void setCards(){
        pilePanel = new JPanel();
        playerPanel = new JPanel();
        allPanels = new JPanel();
        allPanels.setPreferredSize(new Dimension(900, 650));
        allPanels.setLayout(new BorderLayout(0,120));

        // Jpanel background transparent
        allPanels.setOpaque(false);
        pilePanel.setOpaque(false);
        playerPanel.setOpaque(false);

        JComboBox numOfCardsToDeal = new JComboBox(optionsNumOfCardsDealt);
        numOfCardsToDeal.setSelectedIndex(3);
        numOfCardsToDeal.addActionListener(this);

        GridBagConstraints c = new GridBagConstraints();

        // PILE PANEL SET UP //
        //Sets up the draw pile
        btnDrawCard = new JButton();
        c.gridx = 0;
        c.gridy = 0;
        btnDrawCard.addActionListener(this);
        btnDrawCard.setPreferredSize(new Dimension(100,130));
        btnDrawCard.setIcon(cardSizeFix("resources/blankCard.png"));
        btnDrawCard.setOpaque(false);
        pilePanel.add(btnDrawCard,c);

        //Sets up the discard pile
        discardPileLab = new JLabel();
        discardPileLab.setIcon(cardSizeFix("resources/discardPile.png"));
        c.gridx = 2;
        c.gridy = 0;
        discardPileLab.setPreferredSize(new Dimension(100,130));
        pilePanel.add(discardPileLab,c);
        // END OF PILE PANEL SET UP //

//        //Left Arrow Button
//        btnMoveLeft = new JButton("<--");
//        c.gridx = 0;
//        c.gridy = 2;
//        btnMoveLeft.addActionListener(this);
//        btnMoveLeft.setPreferredSize(new Dimension(70,50));
//        playerPanel.add(btnMoveLeft,c);

        // Cards in players hand
        btnChooseCard = new JButton();
        c.gridx = 0;
        c.gridy = 2;
        btnChooseCard.addActionListener(this);
        btnChooseCard.setPreferredSize(new Dimension(100,130));
        btnChooseCard.setIcon(cardSizeFix("resources/blankCard.png")); // For now blank
        playerPanel.add(btnChooseCard,c);
//
//        //Right Arrow Button
//        btnMoveRight = new JButton("-->");
//        c.gridx = 2;
//        c.gridy = 2;
//        btnMoveRight.addActionListener(this);
//        btnMoveRight.setPreferredSize(new Dimension(70,50));
//        playerPanel.add(btnMoveRight,c);

        // Multi-player labels --- ADD MORE W FUNC
        JLabel player2 = new JLabel();
        player2.setIcon(cardSizeFix("resources/blankCard.png"));
        player2.setPreferredSize(new Dimension(100,130));
        player2.setHorizontalAlignment(JLabel.CENTER);


        allPanels.add(player2, BorderLayout.NORTH);
        allPanels.add(pilePanel, BorderLayout.CENTER);
        allPanels.add(playerPanel, BorderLayout.SOUTH);
        f.add(allPanels);

    }

    public ImageIcon cardSizeFix(String source){
        ImageIcon newImage = new ImageIcon(new ImageIcon(source).getImage()
                .getScaledInstance(100, 130, Image.SCALE_SMOOTH));
        return newImage;
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