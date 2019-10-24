//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.EnumSet;
import java.util.Random;

public class Deck {
  private static final int MAXCARDS = 108;
  private int rear = 0;
  Card[] deck = new Card[108];

  public Deck() {
  }

  public Card[] getDeck() {
    return this.deck;
  }

  private boolean isEmpty() {
    return this.rear == 0;
  }

  private void addToDeck(Card card) {
    if (this.isEmpty()) {
      this.deck[0] = card;
    } else {
      this.deck[this.rear] = card;
    }

  }

  public void startDeck() {
    EnumSet.range(Colors.RED, Colors.YELLOW).forEach((color) -> {
      this.addToDeck(new Card(color, 0));
      ++this.rear;

      for(int i = 1; i < 10; ++i) {
        this.addToDeck(new Card(color, i));
        ++this.rear;
        this.addToDeck(new Card(color, i));
        ++this.rear;
      }

      EnumSet.range(ActionCard.SKIP, ActionCard.DRAWTWO).forEach((actionCard) -> {
        this.addToDeck(new Card(color, actionCard));
        ++this.rear;
        this.addToDeck(new Card(color, actionCard));
        ++this.rear;
      });
    });

    for(int i = 0; i < 4; ++i) {
      this.addToDeck(new Card(Colors.NONE, ActionCard.WILD));
      ++this.rear;
      this.addToDeck(new Card(Colors.NONE, ActionCard.WILDFOUR));
      ++this.rear;
    }

  }

  public void shuffleDeck() {
    Random rnd = new Random();

    for(int i = this.deck.length - 1; i > 0; --i) {
      int randomPosition = rnd.nextInt(i + 1);
      Card cardToSwitch = this.deck[randomPosition];
      this.deck[randomPosition] = this.deck[i];
      this.deck[i] = cardToSwitch;
    }

  }

  public void dealCards() {
  }

  public static void main(String[] args) {
    Deck deck = new Deck();
    deck.startDeck();
    deck.shuffleDeck();
    Card[] decks = deck.getDeck();
    Card[] var3 = decks;
    int var4 = decks.length;

    for(int var5 = 0; var5 < var4; ++var5) {
      Card card = var3[var5];
      if (card.getColor() != null && card.getAction() == null) {
        System.out.println(card.getColor() + " " + card.getNumber());
      } else {
        System.out.println(card.getColor() + " " + card.getAction());
      }
    }

  }
}
