import java.util.ArrayList;
import java.util.List;

public class Player {
  public String name;
  public List<Card> hand = new ArrayList();

  public Player(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Card playCard(Card card) {
    this.hand.remove(card);
    return card;
  }
}
