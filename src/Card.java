public class Card {

  private Colors color;
  private int number;
  private ActionCard action;

  public Card(Colors color, int number) {
    this.color = color;
    this.number = number;
  }

  public Card(Colors color, ActionCard action) {
    this.color = color;
    this.action = action;
  }
}
