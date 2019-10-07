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

  public Colors getColor() {
    return color;
  }

  public void setColor(Colors color) {
    this.color = color;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public ActionCard getAction() {
    return action;
  }

  public void setAction(ActionCard action) {
    this.action = action;
  }
}
