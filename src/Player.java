import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 * The player class will keep track of the player's name and hand which is
 * represented in an array
 ******************************************************************************/
public class Player {

  /**
   * The player's name
   **/
  public String name;

  /**
   * the player's hand
   **/
  public List<Card> hand = new ArrayList();

  /******************************************************************************
   * The default constructor of a player. Everyone needs a name, even robots.
   * @param name the name of the player or computer
   ******************************************************************************/
  public Player(String name) {
    this.name = name;
  }

  /******************************************************************************
   * @return the name of the player
   ******************************************************************************/
  public String getName() {
    return this.name;
  }

  /******************************************************************************
   * @param name the name of the player to set
   ******************************************************************************/
  public void setName(String name) {
    this.name = name;
  }

  /******************************************************************************
   * The action of playing a card from the player's hand.
   * @param card the card that the player will be playing and discarding
   * @return the same card that is passed in
   ******************************************************************************/
  public Card playCard(Card card) {
    this.hand.remove(card);
    return card;
  }
}
