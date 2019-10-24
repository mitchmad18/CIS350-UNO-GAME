import java.util.Iterator;
import java.util.List;

public class UnoGame {

  public UnoGame() {
  }

  public static void main(String[] args) {
    DeckController deckController = new DeckController();
    deckController.startDeck();
    deckController.shuffleDeck(deckController.getBaseDeck());
    List<Card> decks = deckController.getBaseDeck();
    GameDoublyLinkedList list = new GameDoublyLinkedList();
    Player player1 = new Player("Bob");
    Player player2 = new Player("Char");
    Player player3 = new Player("Pika");
    Player player4 = new Player("Squir");
    list.add(player1);
    list.add(player2);
    list.add(player3);
    list.add(player4);

    for (int i = 0; i < 7; ++i) {
      deckController.dealCard(decks, player1.hand);
      deckController.dealCard(decks, player2.hand);
      deckController.dealCard(decks, player3.hand);
      deckController.dealCard(decks, player4.hand);
    }

    Card playedCard = player1.playCard((Card) player4.hand.get(1));
    deckController.discard.add(playedCard);
    GameDoublyLinkedList.Node currentPlayer = list.head.next.next.next;
    Card currentTopCard = (Card) deckController.discard.get(deckController.discard.size() - 1);
    if (currentTopCard.getAction() != null) {
      switch (currentTopCard.getAction()) {
        case DRAWTWO:
          deckController.dealCard(decks, currentPlayer.next.player.hand);
          deckController.dealCard(decks, currentPlayer.next.player.hand);
          break;
        case SKIP:
          currentPlayer = currentPlayer.next.next;
          break;
        case REVERSE:
          currentPlayer = currentPlayer.prev;
          break;
        case WILD:
          playedCard.setColor(Colors.RED);
          break;
        case WILDFOUR:
          playedCard.setColor(Colors.BLUE);

          for (int i = 0; i < 4; ++i) {
            deckController.dealCard(decks, currentPlayer.next.player.hand);
          }
      }
    }
  }

  private static void printPlayers(DeckController deckController, List<Card> player) {
    try {
      Iterator var2 = player.iterator();

      while (true) {
        while (var2.hasNext()) {
          Card card = (Card) var2.next();
          if (card.getColor() != null && card.getAction() == null) {
            System.out.println(card.getColor() + " " + card.getNumber());
          } else {
            System.out.println(card.getColor() + " " + card.getAction());
          }
        }

        player.removeAll(deckController.discard);
        System.out.println(player.size());
        break;
      }
    } catch (Exception var4) {
      System.out.println(var4.toString());
    }

  }
}
