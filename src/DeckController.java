//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class DeckController {
  private List<Card> baseDeck = new ArrayList();
  List<Card> discard = new ArrayList();
  public Direction direction;

  public DeckController() {
    this.direction = Direction.RIGHT;
  }

  public Direction getDirection() {
    return this.direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public List<Card> getBaseDeck() {
    return this.baseDeck;
  }

  private boolean isEmpty() {
    return this.baseDeck.size() == 0;
  }

  private void addToDeck(Card card) {
    if (this.isEmpty()) {
      this.baseDeck.add(card);
    } else {
      this.baseDeck.add(card);
    }

  }

  public void startDeck() {
    EnumSet.range(Colors.RED, Colors.YELLOW).forEach((color) -> {
      this.addToDeck(new Card(color, 0));

      for(int i = 1; i < 10; ++i) {
        this.addToDeck(new Card(color, i));
        this.addToDeck(new Card(color, i));
      }

      EnumSet.range(ActionCard.SKIP, ActionCard.DRAWTWO).forEach((actionCard) -> {
        this.addToDeck(new Card(color, actionCard));
        this.addToDeck(new Card(color, actionCard));
      });
    });

    for(int i = 0; i < 4; ++i) {
      this.addToDeck(new Card(Colors.NONE, ActionCard.WILD));
      this.addToDeck(new Card(Colors.NONE, ActionCard.WILDFOUR));
    }

  }

  public void shuffleDeck(List<Card> cardsToShuffle) {
    Random rnd = new Random();

    for(int i = cardsToShuffle.size() - 1; i > 0; --i) {
      int randomPosition = rnd.nextInt(i + 1);
      Card cardToSwitch = (Card)cardsToShuffle.get(randomPosition);
      cardsToShuffle.set(randomPosition, cardsToShuffle.get(i));
      cardsToShuffle.set(i, cardToSwitch);
    }

  }

  public void dealCard(List<Card> deck, List<Card> hand) {
    try {
      if (deck.size() == 0) {
        this.shuffleDeck(deck);
      }

      hand.add(deck.get(deck.size() - 1));
      deck.remove(deck.size() - 1);
    } catch (Exception var4) {
      System.out.println("NO cards to shuffle. All cards are gone.");
    }

  }
}
