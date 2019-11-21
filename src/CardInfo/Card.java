package CardInfo;
/******************************************************************************
 * Card class is used to create a UNO card object. The object will represent the
 * color, number, and action of each card.
 ******************************************************************************/
public class Card {

  /**
   * The color of the card
   **/
  private Colors color;
  /**
   * the number on the card
   **/
  private int number;
  /**
   * the action the card represents
   **/
  private ActionCard action;

  /******************************************************************************
   * This is one of the two card constructors that make up most of the cards in a
   * deck.
   * @param color the color of the card
   * @param number the number on the card
   ******************************************************************************/
  public Card(Colors color, int number) {
    this.color = color;
    this.number = number;
  }

  /******************************************************************************
   * This is two of two card constructors that make up the specialty cards in a
   * deck.
   * @param color the color of the card
   * @param action the action the card represents
   ******************************************************************************/
  public Card(Colors color, ActionCard action) {
    this.color = color;
    this.action = action;
  }

  /******************************************************************************
   * @param color color of the card, the reason color is the only one that needs
   * a setter is for WILD cards
   ******************************************************************************/
  public void setColor(Colors color) {
    this.color = color;
  }

  /******************************************************************************
   * @return the color of the card
   ******************************************************************************/
  public Colors getColor() {
    return this.color;
  }

  /******************************************************************************
   * @return the number on the card, if it has one
   ******************************************************************************/
  public int getNumber() {
    return this.number;
  }

  /******************************************************************************
   * @return the action the card represents, if it has one
   ******************************************************************************/
  public ActionCard getAction() {
    return this.action;
  }

  @Override
  public String toString() {
    if (this.action != null ) {
      return this.color + " "  + this.action;
    }
    else
      return this.color + " " + this.number;


  }
}
