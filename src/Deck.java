import java.util.EnumSet;
import java.util.Random;

public class Deck {

  private static final int MAXCARDS = 108;
  private int rear = 0;

  public Card[] getDeck() {
    return deck;
  }

  Card[] deck = new Card[MAXCARDS];


  private boolean isEmpty() {
    return rear == 0;
  }

  private void addToDeck(Card card) {
    if(isEmpty()) {
      deck[0] = card;
    }
    else {
      deck[rear] = card;
    }
  }

  public void startDeck() {
    EnumSet.range(Colors.RED, Colors.YELLOW).forEach(color -> {
      addToDeck(new Card(color, 0));
      rear++;
      for (int i = 1; i < 10; i++) {
        addToDeck(new Card(color, i));
        rear++;
        addToDeck(new Card(color, i));
        rear++;
      }

      EnumSet.range(ActionCard.SKIP, ActionCard.DRAWTWO).forEach(actionCard -> {
        addToDeck(new Card(color, actionCard));
        rear++;
        addToDeck(new Card(color, actionCard));
        rear++;
      });
    });

    for (int i = 0; i < 4; i++) {
      addToDeck(new Card(Colors.NONE, ActionCard.WILD));
      rear++;
      addToDeck(new Card(Colors.NONE, ActionCard.WILDFOUR));
      rear++;
    }
  }

    public void shuffleDeck() {
      Random rnd = new Random();
      for(int i = deck.length - 1; i > 0; i--) {

        int randomPosition = rnd.nextInt(i + 1);
        Card cardToSwitch = deck[randomPosition];
        deck[randomPosition] = deck[i];
        deck[i] = cardToSwitch;
      }
    }

    public void dealCards() {

    }


  public static void main(String[] args) {
    Deck deck = new Deck();
    deck.startDeck();
    deck.shuffleDeck();

    Card[] decks = deck.getDeck();

    for (Card card : decks) {
      if(card.getColor() != null && card.getAction() == null) {
        System.out.println(card.getColor() + " " + card.getNumber());
      }
      else {
        System.out.println(card.getColor() + " " + card.getAction());
      }
    }
  }

}
