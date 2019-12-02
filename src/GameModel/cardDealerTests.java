package GameModel;


import CardModel.cardDeck;
import GameView.card;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cardDealerTests {
    private static cardDealer CardDealer;

    @Before
    public void setUp() {
        CardDealer = new cardDealer();
    }

    @Test
    public void CardDealerConstructor() {
        Assert.assertNotNull(CardDealer.deck);
    }

    //in this test I created an if statement, because I wanted to test two sets of cards in the decks
    //to check that they are not equal, in case the first card is put in the first spot
    @Test
    public void ShuffleTest() {
        Assert.assertEquals(108, CardDealer.deck.getBaseDeck().size());

        cardDeck cd = new cardDeck();
        List<card> baseDeck = cd.getBaseDeck();
        List<card> tempDeck = CardDealer.shuffle();
        boolean notEquals = false;
        Random r = new Random();
        int rInt = (Math.abs(r.nextInt())) % 108;

        if (baseDeck.get(0) != tempDeck.get(0) && baseDeck.get(rInt) != tempDeck.get(rInt)) {
            notEquals = true;
        }

        Assert.assertTrue(notEquals);
        Assert.assertEquals(108, tempDeck.size());
    }

    @Test
    public void DealCards() {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player player4 = new Player();
        Player[] players = new Player[]{player1, player2, player3, player4};

        CardDealer.shuffle();
        CardDealer.dealCards(players);
        Assert.assertEquals(7, player1.getPlayerHandTotal());
        Assert.assertEquals(7, player2.getPlayerHandTotal());
        Assert.assertEquals(7, player3.getPlayerHandTotal());
        Assert.assertEquals(7, player4.getPlayerHandTotal());

    }

    @Test(expected = Test.None.class)
    public void DealCards_Exception() {
        Player[] players = new Player[]{new Player()};
        CardDealer.dealCards(players);
    }

    @Test
    public void DealCards_EmptyStack() {
        CardDealer.cardStack = new ArrayList<>();
        Player[] players = new Player[]{new Player()};
        CardDealer.dealCards(players);
    }

    @Test
    public void DrawCard() {
        CardDealer.shuffle();
        Assert.assertNotNull(CardDealer.drawCard());
    }


}
