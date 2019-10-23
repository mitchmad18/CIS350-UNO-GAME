import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UnoGui implements ActionListener {
    private JFrame f;
    private JMenuBar mb;
    private JMenu file;
    private JMenuItem reset, exit;
    private JButton btnDrawCard;
    private JPanel panel;

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

        GridBagConstraints c = new GridBagConstraints();

        btnDrawCard = new JButton();
        c.gridx = 0;
        c.gridy = 0;
        btnDrawCard.addActionListener(this);
        btnDrawCard.setPreferredSize(new Dimension(70,100));
        panel.add(btnDrawCard,c);

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
        JOptionPane.showMessageDialog(f,
                "Welcome to UNO!\nPlease select the rules you would like to use: ",
                "New Game",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void drawCard() {

    }
}