package CardInfo;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

/******************************************************************************
 * This class is a controller for what we call a Deck. It is handling the base
 * deck array, discard deck array, and right now has the direction handling as
 * well, although that may change in the future.
 *
 ******************************************************************************/


public class DeckController {

  // An empty array that will represent the base starting deck of Uno
  private List<Card> baseDeck;
  // An empty array that will represent the discard deck of the game
  public List<Card> discard;

  /******************************************************************************
   * Default constructor that will already have the discard and base deck, then it
   * will set the direction to the right.
   ******************************************************************************/
  public DeckController() {
    baseDeck = new ArrayList<Card>();
    discard = new ArrayList<Card>();
    startDeck();

  }

  /******************************************************************************
   * @return The base deck of UNO
   ******************************************************************************/
  public List<Card> getBaseDeck() {
    return this.baseDeck;
  }

  /******************************************************************************
   * @return checks whether the base deck is empty (this may change in the future)
   ******************************************************************************/
  private boolean isEmpty() {
    return this.baseDeck.size() == 0;
  }

  /******************************************************************************
   * This method is used to help create the base deck
   * @param card the card that will be inserted into the base deck
   ******************************************************************************/
  private void addToDeck(Card card) {
    if (this.isEmpty()) {
      this.baseDeck.add(card);
    } else {
      this.baseDeck.add(card);
    }

  }

  /******************************************************************************
   * Starts the base deck with all the colors, actions, and wild cards. This
   * should all add up to 108 cards.
   ******************************************************************************/
  public void startDeck() {
    EnumSet.range(Colors.RED, Colors.YELLOW).forEach((color) -> {
      this.addToDeck(new Card(color, 0));

      for (int i = 1; i < 10; ++i) {
        this.addToDeck(new Card(color, i));
        this.addToDeck(new Card(color, i));
      }

      EnumSet.range(ActionCard.SKIP, ActionCard.DRAWTWO).forEach((actionCard) -> {
        this.addToDeck(new Card(color, actionCard));
        this.addToDeck(new Card(color, actionCard));
      });
    });

    for (int i = 0; i < 4; ++i) {
      this.addToDeck(new Card(Colors.NONE, ActionCard.WILD));
      this.addToDeck(new Card(Colors.NONE, ActionCard.WILDFOUR));
    }

  }

  /******************************************************************************
   * Takes a pile of cards and shuffles them randomly, the method used is the
   * Fischer-Yates shuffle.
   * @param cardsToShuffle the pile of cards that will be shuffled randomly
   ******************************************************************************/
  public void shuffleDeck(List<Card> cardsToShuffle) {
    Random rnd = new Random();

    for (int i = cardsToShuffle.size() - 1; i > 0; --i) {
      int randomPosition = rnd.nextInt(i + 1);
      Card cardToSwitch = cardsToShuffle.get(randomPosition);
      cardsToShuffle.set(randomPosition, cardsToShuffle.get(i));
      cardsToShuffle.set(i, cardToSwitch);
    }

  }

  /******************************************************************************
   * Take a card from the deck that was given and adds it to a player's hand.
   * This will decrement the card from the deck.
   * @param deck the pile of cards that are still faced down
   * @param hand the player's hand
   ******************************************************************************/
  public void dealCard(List<Card> deck, List<Card> hand, int numberToDeal) {
    try {
      if (deck.size() == 0) {
        this.shuffleDeck(deck);
      }

      for(int i = 0; i < numberToDeal; i++) {
        hand.add(deck.get(deck.size() - 1));
        deck.remove(deck.size() - 1);

      }
    } catch (Exception var4) {
      System.out.println("NO cards to shuffle. All cards are gone.");
    }

  }


}
