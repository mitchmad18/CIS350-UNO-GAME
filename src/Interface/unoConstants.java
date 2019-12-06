package Interface;

import java.awt.*;

/******************************************************************************
 * The unoConstants represents the specifics of each card type.
 *
 * @author TonyChanelle
 * @author Pratty Hongsyvilay
 * @author Myren Mitchell
 ******************************************************************************/
public interface unoConstants {

    // Color of cards //
    Color RED = new Color(204, 0, 0);
    Color GREEN = new Color(0, 125, 0);
    Color YELLOW = new Color(255, 200, 0);
    Color BLUE = new Color(0, 0, 204);
    Color BLACK = new Color(0, 0, 0);

    // Types of cards //
    int NUMBERS = 1;
    int ACTION = 2;
    int WILD = 3;

    // Action card characters //
    Character charRev = (char) 8634;
    Character charSkip = (char) Integer.parseInt("2718", 16);

    // Action card functions //
    String REVERSE = charRev.toString();
    String SKIP = charSkip.toString();
    String DRAW2 = "+2";
    String WCOLORPICK = "W";
    String WDRAW4 = "+4";

}
