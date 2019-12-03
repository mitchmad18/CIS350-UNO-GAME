import CardModel.cardDeck;
import GameView.card;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UnoTest {
    @Test
    public void drawCard_decreasesDeck() {
        cardDeck controller = new cardDeck();
        List<card> hand = new ArrayList<card>();
        controller.startDeck();
//    controller.dealCard(controller.getBaseDeck(), hand);

        assertEquals(107, controller.getBaseDeck().size());
    }
}
