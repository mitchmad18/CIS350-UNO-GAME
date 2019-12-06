package CardModel;

import Interface.unoConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class cardDeckTest {

    cardDeck cardDeck;

    @Before
    public void setup() {
        cardDeck = new cardDeck();
    }

    @Test
    public void startDeck1() {
        Assert.assertEquals(108, cardDeck.getBaseDeck().size());
    }

    @Test
    public void getBaseDeck() {
        Assert.assertNotNull(cardDeck.getBaseDeck());
    }

    @Test
    public void startDeckConstructor() {
        Assert.assertNotNull(cardDeck);
    }

//    @Test
//    public void cardListener() {
//        for (card card: cardDeck.baseDeck) {
//            Assert.assertNotNull(card.getContainerListeners());
//        }
//    }

    @Test
    public void actionCard() {
        new actionCard();
    }

    @Test
    public void wildCard() {
        wildCard wild = new wildCard(unoConstants.WCOLORPICK);
        wild.setWildColor(unoConstants.BLUE);
        Assert.assertSame(unoConstants.BLUE, wild.getWildColor());
    }

    @Test
    public void numCard() {
        new numCard();
    }

}