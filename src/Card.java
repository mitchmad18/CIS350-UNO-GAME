//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Card {


  private Colors color;
  private int number;
  private ActionCard action;

  public void setColor(Colors color) {
    this.color = color;
  }

  public Card(Colors color, int number) {
    this.color = color;
    this.number = number;
  }

  public Card(Colors color, ActionCard action) {
    this.color = color;
    this.action = action;
  }

  public Colors getColor() {
    return this.color;
  }

  public int getNumber() {
    return this.number;
  }

  public ActionCard getAction() {
    return this.action;
  }
}
