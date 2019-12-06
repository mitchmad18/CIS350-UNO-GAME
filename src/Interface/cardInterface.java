package Interface;

import java.awt.*;

/******************************************************************************
 * The cardInterface interface represents the properties of each card.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author Myren Mitchell
 ******************************************************************************/
public interface cardInterface {

    // Card dimension specifics // - Can change for multiplayer for more room //
    int WIDTH = 50;
    int HEIGHT = 75;
    Dimension SMALL = new Dimension(WIDTH, HEIGHT);
    Dimension MEDIUM = new Dimension(WIDTH * 2, HEIGHT * 2);
    Dimension BIG = new Dimension(WIDTH * 3, HEIGHT * 3);

    // Default card size //
    Dimension CSIZE = MEDIUM;

    // Default //
    int OFFSET = 71;

    /******************************************************************************
     * This method gets the color of the card created.
     * @return - card color.
     ******************************************************************************/
    Color getColor();

    /******************************************************************************
     * This method sets the color of the card created.
     * @param newColor - card color.
     ******************************************************************************/
    void setColor(Color newColor);

    /******************************************************************************
     * This method gets the value of the card created.
     * @return - value of the card.
     ******************************************************************************/
    String getValue();

    /******************************************************************************
     * This method sets the value of the card created.
     * @param newValue - card value.
     ******************************************************************************/
    void setValue(String newValue);

    /******************************************************************************
     * This method gets the type of the card created.
     * @return - card type.
     ******************************************************************************/
    int getType();

    /******************************************************************************
     * This method sets the type of the card created.
     * @param newType - card type.
     ******************************************************************************/
    void setType(int newType);
}
