package GameView;

import Interface.unoConstants;
import Interface.cardInterface;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

/******************************************************************************
 * The card class is serves as the base card class and is used to create a UNO
 * card object. The object will represent the color, number, and action of
 * each card.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public abstract class card extends JPanel implements cardInterface, unoConstants {

    // The color of the card //
    private Color cColor;
    // The number on the card //
    private String cValue;
    // The type of card //
    private int type = 0;
    // Game border settings //
    private Border defBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.WHITE, Color.gray);
    private Border focBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);

    /******************************************************************************
     * Empty default class constructor.
     ******************************************************************************/
    public card() {
    }

    /******************************************************************************
     * This is one of the two card constructors that represents the cards of the
     * deck.
     * @param color - color of the card.
     * @param type - type of card.
     * @param value - value on the card.
     ******************************************************************************/
    public card(Color color, int type, String value) {
        this.cColor = color;
        this.cValue = value;
        this.type = type;

        this.setPreferredSize(CSIZE);
        this.setBorder(defBorder);
        this.addMouseListener(new MouseHandler());
    }

    /******************************************************************************
     * This method sets up the graphics of each card.
     * @param g - card graphics.
     ******************************************************************************/
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int cardWidth = CSIZE.width;
        int cardHeight = CSIZE.height;

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, cardWidth, cardHeight);

        int margin = 5;
        g2.setColor(cColor);
        g2.fillRect(margin, margin, cardWidth - 2 * margin, cardHeight - 2 * margin);

        g2.setColor(Color.WHITE);
        AffineTransform org = g2.getTransform();
        g2.rotate(45, cardWidth * 3 / 4, cardHeight * 3 / 4);

        g2.fillOval(0, cardHeight * 1 / 4, cardWidth * 3 / 5, cardHeight);
        g2.setTransform(org);

        // Value in center of card
        Font defFont = new Font("Helvetica", Font.BOLD, cardWidth / 2 + 5);
        FontMetrics fm = this.getFontMetrics(defFont);
        int stringWidth = fm.stringWidth(cValue) / 2;
        int fontHeight = defFont.getSize() * 1 / 3;

        g2.setColor(cColor);
        g2.setFont(defFont);
        g2.drawString(cValue, cardWidth / 2 - stringWidth, cardHeight / 2 + fontHeight);

        // Corner card values
        defFont = new Font("Helvetica", Font.ITALIC, cardWidth / 5);
        fm = this.getFontMetrics(defFont);
        stringWidth = fm.stringWidth(cValue) / 2;
        fontHeight = defFont.getSize() * 1 / 3;

        g2.setColor(Color.WHITE);
        g2.setFont(defFont);
        g2.drawString(cValue, 2 * margin, 5 * margin);

    }

    /******************************************************************************
     * This private class serves as the listener for each card created.
     ******************************************************************************/
    class MouseHandler extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            setBorder(focBorder);
        }

        public void mouseExited(MouseEvent e) {
            setBorder(defBorder);
        }
    }


    /******************************************************************************
     * This method sets the color of the card created.
     * @param color - card color.
     ******************************************************************************/
    @Override
    public void setColor(Color color) {
        this.cColor = color;
    }

    /******************************************************************************
     * This method gets the color of the card created.
     * @return - card color.
     ******************************************************************************/
    @Override
    public Color getColor() {
        return this.cColor;
    }

    /******************************************************************************
     * This method sets the value of the card created.
     * @param cValue - card value.
     ******************************************************************************/
    @Override
    public void setValue(String cValue) {
        this.cValue = cValue;
    }

    /******************************************************************************
     * This method gets the value of the card created.
     * @return - value of the card.
     ******************************************************************************/
    @Override
    public String getValue() {
        return this.cValue;
    }

    /******************************************************************************
     * This method sets the type of the card created.
     * @param type - card type.
     ******************************************************************************/
    @Override
    public void setType(int type) {
        this.type = type;
    }

    /******************************************************************************
     * This method gets the type of the card created.
     * @return - card type.
     ******************************************************************************/
    @Override
    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.cColor.toString() + "/" + this.cValue + "/" + this.getType();
    }

}
