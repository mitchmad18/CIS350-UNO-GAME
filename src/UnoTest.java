import CardInfo.Card;
import CardInfo.DeckController;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class UnoTest {
  @Test
  public void drawCard_decreasesDeck() {
    DeckController controller = new DeckController();
    List<Card> hand = new ArrayList<Card>();
    controller.startDeck();
//    controller.dealCard(controller.getBaseDeck(), hand);

    //assertEquals(107, controller.getDrawPile().size());
  }
}
