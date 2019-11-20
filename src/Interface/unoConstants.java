package Interface;

import java.awt.*;

/******************************************************************************
 * The unoConstants represents the specifics of each card type.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public interface unoConstants {

    // Color of cards //
    public static Color RED = new Color(204, 0, 0);
    public static Color GREEN = new Color(0, 125, 0);
    public static Color YELLOW = new Color(255, 200, 0);
    public static Color BLUE = new Color(0, 0, 204);
    public static Color BLACK = new Color(0, 0, 0);

    // Types of cards //
    public static int NUMBERS = 1;
    public static int ACTION = 2;
    public static int WILD = 3;

    // Action card characters //
    Character charRev = (char) 8364; //**TO BE CORRECTED**
    Character charSkip = (char) Integer.parseInt("2718", 16);

    // Action card functions //
    String REVERSE = charRev.toString();
    String SKIP = charSkip.toString();
    String DRAW2 = "+2";
    String WCOLORPICK = "W";
    String WDRAW4 = "+4";

}
