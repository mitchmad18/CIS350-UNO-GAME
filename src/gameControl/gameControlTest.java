package gameControl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class gameControlTest {
    private static buttonListener ButtonListener;
    private static gameController GameController;
    private static cardListener CardListener;

    @BeforeClass
    public static void setUp() {
        GameController = new gameController();
        ButtonListener = new buttonListener();
        CardListener = new cardListener();

        CardListener.setController(GameController);
        ButtonListener.setController(GameController);
    }

    @Test
    public void drawCardTest() {
        ButtonListener.drawCard();
        Assert.assertTrue(GameController.canPlay());
        Assert.assertNotNull(GameController.peekTopCard());
        
    }

    @Test
    public void buttonListenerSayUNOTest() {
        ButtonListener.sayUNO();

    }

    //Questionable test
    @Test
    public void buttonListenerActionEventTest() {
        ButtonListener.actionPerformed(null);
    }

    @Test
    public void cardListenerMousePressedTest() {

    }
}