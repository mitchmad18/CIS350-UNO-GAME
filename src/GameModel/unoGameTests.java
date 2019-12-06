package GameModel;

import CardModel.numCard;
import CardModel.wildCard;
import GameView.card;
import Interface.unoConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

public class unoGameTests {
    private unoGame UnoGame;
    private Player[] players;

    @Before
    public void setUp() {
        players = UnoGame.getPlayers();
    }

    @Test
    public void Constructor1() {

        Assert.assertNotNull(UnoGame.getPlayers());
        Assert.assertEquals(2, UnoGame.getPlayers().length);
        Assert.assertFalse(UnoGame.gameOver());
        Assert.assertNotNull(UnoGame.cardStack);
    }

    @Test
    public void Constructor2() {

        Assert.assertNotNull(UnoGame.getPlayers());
        Assert.assertEquals(2, UnoGame.getPlayers().length);
        Assert.assertFalse(UnoGame.gameOver());
        Assert.assertNotNull(UnoGame.cardStack);
    }

    @Test
    public void RecreateDealer() {
        cardDealer original = UnoGame.dealer;
        UnoGame.recreateDealer();
        Assert.assertNotEquals(original, UnoGame.dealer);
    }

    @Test
    public void GetPlayers() {
        Assert.assertNotNull(UnoGame.getPlayers());
    }

    @Test
    public void GetCard() {
        Assert.assertNotNull(UnoGame.getCard());
    }

    @Test
    public void RemoveCardPlayed_EmptyHand() {
        players[0].playerHand = new ArrayList<>();
        numCard cardPlayed = new numCard(Color.RED, "2");
        players[0].playerHand.add(cardPlayed);

        UnoGame.removeCardPlayed(cardPlayed);
        Assert.assertEquals(0, players[0].getPlayerHandTotal());
    }

    @Test
    public void RemoveCardPlayed_NoSayUno() {
        players[0].playerHand = new ArrayList<>();
        numCard cardPlayed = new numCard(Color.RED, "2");
        numCard cardPlayed2 = new numCard(Color.BLUE, "2");
        players[0].playerHand.add(cardPlayed2);
        players[0].playerHand.add(cardPlayed);
        players[0].setSayUNO(false);

        UnoGame.removeCardPlayed(cardPlayed);
        Assert.assertEquals(3, players[0].getPlayerHandTotal());
    }

    @Test
    public void RemoveCardPlayed() {
        players[0].playerHand = new ArrayList<>();
        numCard cardPlayed = new numCard(Color.RED, "2");
        numCard cardPlayed2 = new numCard(Color.BLUE, "2");
        numCard cardPlayed3 = new numCard(Color.BLUE, "2");
        players[0].playerHand.add(cardPlayed2);
        players[0].playerHand.add(cardPlayed);
        players[0].playerHand.add(cardPlayed3);

        UnoGame.removeCardPlayed(cardPlayed);
        Assert.assertEquals(2, players[0].getPlayerHandTotal());
        Assert.assertFalse(players[0].playerSaysUNO);
        Assert.assertEquals(1, players[0].cardsPlayedTotal());
    }

    @Test
    public void DrawCard_CanPlay() {
        card topCard = UnoGame.cardStack.get(UnoGame.cardStack.size() - 1);
        UnoGame.drawCard(topCard);
        Assert.assertEquals(8, players[1].getPlayerHandTotal());
    }

    @Test
    public void DrawCard_CantPlay() {
        card topCard = UnoGame.cardStack.get(UnoGame.cardStack.size() - 2);
        if (topCard == UnoGame.cardStack.get(UnoGame.cardStack.size() - 1)) {
            topCard = UnoGame.cardStack.get(UnoGame.cardStack.size() - 3);
        }
        UnoGame.drawCard(topCard);
        Assert.assertEquals(8, players[1].getPlayerHandTotal());
    }

    @Test
    public void DrawPlus() {
        UnoGame.drawPlus(2);
        Assert.assertEquals(9, players[0].getPlayerHandTotal());
    }

    @Test
    public void DrawPlus_IsPlayersTurn() {
        players[0].isPlayerTurn = true;
        players[1].isPlayerTurn = false;
        UnoGame.drawPlus(2);
        Assert.assertEquals(7, players[0].getPlayerHandTotal());
    }

    @Test
    public void IsAITurn() {
        Assert.assertFalse(UnoGame.isAITurn());
    }

    @Test
    public void CardsRemaining() {
        Assert.assertTrue(UnoGame.cardsRemaining() > 0);
    }

    @Test
    public void GameOver() {
        UnoGame.cardStack.clear();
        Assert.assertTrue(UnoGame.gameOver());
    }

    @Test
    public void GameOver_Not() {
        Assert.assertFalse(UnoGame.gameOver());
    }

    @Test
    public void GameOver_ByPlayer() {
        players[0].getPlayerHand().clear();
        Assert.assertTrue(UnoGame.gameOver());
    }

    @Test
    public void CanPlay_True() {
        card topCard = new numCard(unoConstants.RED, "2");
        card cardPlayed = new numCard(unoConstants.RED, "3");

        boolean canPlay = UnoGame.canPlay(topCard, cardPlayed);
        Assert.assertTrue(canPlay);
    }

    @Test
    public void CanPlay_False() {
        card topCard = new numCard(unoConstants.BLUE, "2");
        card cardPlayed = new numCard(unoConstants.RED, "3");

        boolean canPlay = UnoGame.canPlay(topCard, cardPlayed);
        Assert.assertFalse(canPlay);
    }

    @Test
    public void CanPlay_TopWildTrue() {
        card topCard = new wildCard(unoConstants.WCOLORPICK);
        topCard.setColor(unoConstants.BLUE);
        card cardPlayed = new numCard(unoConstants.BLUE, "2");

        boolean canPlay = UnoGame.canPlay(topCard, cardPlayed);
        Assert.assertTrue(canPlay);
    }

    @Test
    public void CanPlay_TopWildFalse() {
        wildCard topCard = new wildCard(unoConstants.WCOLORPICK);
        topCard.setWildColor(unoConstants.BLUE);
        card cardPlayed = new numCard(unoConstants.RED, "2");

        boolean canPlay = UnoGame.canPlay(topCard, cardPlayed);
        Assert.assertFalse(canPlay);
    }

    @Test
    public void CanPlay_WildPlayed() {
        numCard topCard = new numCard(unoConstants.RED, "2");
        wildCard cardPlayed = new wildCard(unoConstants.WCOLORPICK);

        boolean canPlay = UnoGame.canPlay(topCard, cardPlayed);
        Assert.assertTrue(canPlay);
    }

    @Test
    public void SetSaidUno_True() {
        players[1].playerHand.clear();
        players[1].playerHand.add(new numCard(unoConstants.RED, "2"));
        players[1].playerHand.add(new numCard(unoConstants.RED, "2"));
        UnoGame.setSaidUNO();
        Assert.assertTrue(players[1].playerSaidUNO());

    }

    @Test
    public void SetSaidUno_False() {
        players[1].playerHand.clear();
        players[1].playerHand.add(new numCard(unoConstants.RED, "2"));
        players[1].playerHand.add(new numCard(unoConstants.RED, "2"));
        UnoGame.setSaidUNO();
        Assert.assertFalse(players[0].playerSaidUNO());
    }

    @Test
    public void CheckSaidUno_True() {
        players[1].playerHand.clear();
        players[1].playerHand.add(new numCard(unoConstants.RED, "2"));
        players[1].playerHand.add(new numCard(unoConstants.RED, "3"));
        UnoGame.setSaidUNO();
        UnoGame.checkSaidUNO();
        Assert.assertEquals(2, players[1].getPlayerHandTotal());
    }

    @Test
    public void CheckSaidUno_False() {
        players[1].playerHand.clear();
        players[1].playerHand.add(new numCard(unoConstants.RED, "2"));
        UnoGame.checkSaidUNO();
        Assert.assertEquals(3, players[1].getPlayerHandTotal());
    }
}
