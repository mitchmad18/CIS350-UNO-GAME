import GameView.gameFrame;
import javax.swing.*;

public class RunUNO {
    public static void main(String[] args) {

        //Create Frame and invoke it.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new gameFrame();
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setLocation(200, 100);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
