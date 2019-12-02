package GameModel;

import CardModel.actionCard;
import CardModel.numCard;
import CardModel.wildCard;
import GameView.card;
import Interface.unoConstants;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class gameModelTests {
    private static AI ai;
    private List<card> fakeHand = new ArrayList<>();

    @Before
    public void setUp() {
        ai = new AI();
    }

    @After
    public void tearDown() {
        fakeHand.clear();
    }


    @Test
    public void AIConstructorTest() {
        Assert.assertNotNull(ai);
        Assert.assertEquals("AI", ai.name);
        Assert.assertNotNull(ai.getPlayerHand());
        Assert.assertEquals(0, ai.getPlayerHandTotal());
    }

    @Test
    public void AITestPlayWildOnTop_Playable() {
        fakeHand.add(new numCard(unoConstants.BLUE, "2"));
        ai.playerHand = fakeHand;

        wildCard topCard = new wildCard(unoConstants.WCOLORPICK);
        topCard.setWildColor(unoConstants.BLUE);

        Assert.assertTrue(ai.play(topCard));
    }

    @Test
    public void AITestPlayWildOnTop_NotPlayable() {
        fakeHand.add(new numCard(unoConstants.RED, "2"));
        ai.playerHand = fakeHand;

        wildCard topCard = new wildCard(unoConstants.WCOLORPICK);
        topCard.setWildColor(unoConstants.BLUE);

        Assert.assertFalse(ai.play(topCard));
    }

    @Test
    public void AITestPlayActionCard_Playable() {
        fakeHand.add(new actionCard(unoConstants.RED, "+2"));
        ai.playerHand = fakeHand;

        numCard topCard = new numCard(unoConstants.RED, "2");

        Assert.assertTrue(ai.play(topCard));
    }

    @Test
    public void AITestPlayActionCard_NotPlayable() {
        fakeHand.add(new actionCard(unoConstants.BLUE, "+2"));
        ai.playerHand = fakeHand;

        numCard topCard = new numCard(unoConstants.RED, "2");

        Assert.assertFalse(ai.play(topCard));
    }

    @Test
    public void AITestPlayWildCard_Playable() {
        fakeHand.add(new wildCard(unoConstants.WCOLORPICK));
        ai.playerHand = fakeHand;
        numCard topCard = new numCard(unoConstants.RED, "2");
        Assert.assertTrue(ai.play(topCard));
    }

    @Test
    public void AIPickColor_RED() {
        fakeHand.add(new numCard(unoConstants.RED, "2"));
        ai.playerHand = fakeHand;

        int wildNumber = ai.pickWildColor();
        Assert.assertEquals(0, wildNumber);
    }

    @Test
    public void AIPickColor_YELLOW() {
        fakeHand.add(new numCard(unoConstants.YELLOW, "2"));
        ai.playerHand = fakeHand;

        int wildNumber = ai.pickWildColor();
        Assert.assertEquals(1, wildNumber);
    }

    @Test
    public void AIPickColor_GREEN() {
        fakeHand.add(new numCard(unoConstants.GREEN, "2"));
        ai.playerHand = fakeHand;

        int wildNumber = ai.pickWildColor();
        Assert.assertEquals(2, wildNumber);
    }

    @Test
    public void AIPickColor_BLUE() {
        fakeHand.add(new numCard(unoConstants.BLUE, "2"));
        ai.playerHand = fakeHand;

        int wildNumber = ai.pickWildColor();
        Assert.assertEquals(3, wildNumber);
    }

}
