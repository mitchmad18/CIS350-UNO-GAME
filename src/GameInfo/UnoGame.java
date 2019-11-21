import CardInfo.ActionCard;
import CardInfo.Card;
import CardInfo.Colors;
import CardInfo.DeckController;
import GameInfo.Player;

import java.util.List;
import java.util.Scanner;

/******************************************************************************
 * This is where most of the testing for game functionality is found. If it will
 * be used with the GUI is unknown at the moment.
 ******************************************************************************/
public class UnoGame {
  /**
   * The control handler for the draw pile  and discard pile
   **/
  public static DeckController deckController = new DeckController();
  /**
   * The control handler for player rotation
   **/
  public GameDoublyLinkedList turnController = new GameDoublyLinkedList();
  /**
   * The direction the game
   **/
  public Direction direction = Direction.RIGHT;
  /**
   * The current card in play
   **/
  public Card currentCard;
  /**
   * @return the current player
   **/
  public GameDoublyLinkedList.Node getCurrentPlayer() {
    return currentPlayer;
  }
  /**
   * @param currentPlayer set the current player
   **/
  public void setCurrentPlayer(GameDoublyLinkedList.Node currentPlayer) {
    this.currentPlayer = currentPlayer;
  }
  /**The current player's turn **/
  public GameDoublyLinkedList.Node currentPlayer;


  /******************************************************************************
   * @return get the current color in play
   ******************************************************************************/
  public Card getCurrentCard() {
    return currentCard;
  }

  /******************************************************************************
   * @param currentCard set current color in play
   ******************************************************************************/
  public void setCurrentCard(Card currentCard) {
    this.currentCard = currentCard;
  }


  /******************************************************************************
   * @return Current direction of the game
   ******************************************************************************/
  public Direction getDirection() {
    return this.direction;
  }

  /******************************************************************************
   * @param direction direction the game is going
   ******************************************************************************/
  public void setDirection(Direction direction) {
    this.direction = direction;
  }

  public UnoGame(){
  }

  /******************************************************************************
   * The main method is used to test the functionality of the rest of the classes.
   ******************************************************************************/
  public static void main(String[] args) {

    UnoGame uno = new UnoGame();
    DeckController deckController = UnoGame.deckController;
//    /** The deck controller handles the movement between decks and cards**/
    deckController.startDeck();
    deckController.shuffleDeck(deckController.getBaseDeck());
//
//    /** Poorly named, but the decks card is the base deck of UNO**/
    List<Card> decks = deckController.getBaseDeck();
//
//    /** the linked list represents the turn order of the game**/
    GameDoublyLinkedList list = uno.turnController;
//
    /** test player 1**/
    Player player1 = new Player("Bob");
    /** test player 2**/
    Player player2 = new Player("Char");
    /** test player 3**/
    Player player3 = new Player("Pika");
    /** test player 4**/
    Player player4 = new Player("Squir");

    list.add(player1);
    list.add(player2);
    list.add(player3);
    list.add(player4);

    GameDoublyLinkedList.Node currentPlayer = list.head;

    do {
      deckController.dealCard(decks, currentPlayer.player.playerHand, 7);
      currentPlayer = currentPlayer.next;
    }
    while(currentPlayer != list.head);

    uno.setCurrentPlayer(list.head);

    Card card = new Card(Colors.BLUE, ActionCard.SKIP);
    deckController.discard.add(card);
    uno.currentCard = card;
    uno.playerTurn(currentPlayer.player);

  }

  /******************************************************************************
   Checks the current card being played for the type of action it is and then
   executes the action

   @param card The card to check the action of
   @param deck The current draw pile deck
   ******************************************************************************/
  public void checkForActionEvent(Card card, List<Card> deck) {

    if (card.getAction() != null) {

      switch (card.getAction()) {

        case DRAWTWO:
          deckController.dealCard(deck, currentPlayer.next.player.playerHand, 2);
          break;
        case SKIP:
          setCurrentPlayer(skip(currentPlayer, direction));
          System.out.println(currentPlayer.player.name);
          break;
        case REVERSE:
          setCurrentPlayer(currentPlayer.prev);
          setDirection(Direction.LEFT);
          break;
        case WILD:
          currentCard.setColor(wildAction());
          break;
        case WILDFOUR:
          currentCard.setColor(wildAction());
          deckController.dealCard(deck, currentPlayer.next.player.playerHand, 4);
          break;
      }
    }
  }

  /******************************************************************************
   * Skips the next player
   * @param currentPlayer the current player who played the last card
   * @param direction the current direction the game is going
   ******************************************************************************/
  public GameDoublyLinkedList.Node skip(GameDoublyLinkedList.Node currentPlayer, Direction direction) {
    return direction.equals(Direction.RIGHT) ? currentPlayer.next.next : currentPlayer.prev.prev;
  }

  public Colors wildAction() {
    Scanner input = new Scanner(System.in);
    String s = input.nextLine();

    switch (s.toUpperCase()) {
      case "BLUE":
        return Colors.BLUE;

      case "RED":
        return Colors.RED;

      case "GREEN":
        return Colors.GREEN;

      case "YELLOW":
        return Colors.YELLOW;

    }

    return Colors.NONE;
  }

  /******************************************************************************
   * The player's actions when it is their turn
   * @param player
   ******************************************************************************/
  public void playerTurn(Player player) {
    int count = 0;
    boolean played = true;
    System.out.println("The top card is: " + currentCard.toString());

    System.out.println("The cards in your hand are:");

    for(int i = count; i < player.playerHand.size(); i++) {
      System.out.println((i +1) + ". " + player.playerHand.get(i).toString());
      count++;
    }
    count++;
    System.out.println(count + ". " + "Draw a card");


    while(played) {
      System.out.println("Please pick a card to play or draw a card.");
      Scanner in = new Scanner(System.in);
      int selection = in.nextInt();

      if (selection == count){
        deckController.dealCard(deckController.getBaseDeck(), player.playerHand, 1);
        played = false;

      } else if (checkPlay(player.playerHand.get(selection - 1))) {

        Card playedCard = player.playerHand.get(selection - 1);
        deckController.discard.add(playedCard);
        player.playerHand.remove(selection - 1);
        this.setCurrentCard(playedCard);

        if (playedCard.getAction() != null) {
          checkForActionEvent(playedCard, deckController.getBaseDeck());
        }
        System.out.println("Your new hand is: ");

        for (int i = 0; i < player.playerHand.size(); i++) {
          System.out.println((i + 1) + ". " + player.playerHand.get(i).toString());
        }

        played = false;
      } else
        System.out.println("Not playable");
    }
  }
  /******************************************************************************
   Check to see if the last card played was a valid play
   @param playedCard the card that was last played
   ******************************************************************************/
  public boolean checkPlay(Card playedCard) {

    if (playedCard.getAction() == null) {
      if (playedCard.getColor() == currentCard.getColor())
        return true;
      else return playedCard.getNumber() == currentCard.getNumber();
    }
    else {
      if (playedCard.getColor() == currentCard.getColor())
        return true;
      else return playedCard.getAction() == currentCard.getAction();
    }
  }

  public void computerTurn() {

  }

//
//  /******************************************************************************
//   * Prints out all the players. This method is temporary
//   * @param player
//   * @param deckController
//   ******************************************************************************/
//  private static void printPlayers(CardInfo.DeckController deckController, List<CardInfo.Card> player) {
//    try {
//      Iterator var2 = player.iterator();
//
//      while (true) {
//        while (var2.hasNext()) {
//          CardInfo.Card card = (CardInfo.Card) var2.next();
//          if (card.getColor() != null && card.getAction() == null) {
//            System.out.println(card.getColor() + " " + card.getNumber());
//          } else {
//            System.out.println(card.getColor() + " " + card.getAction());
//          }
//        }
//
//        player.removeAll(deckController.discard);
//        System.out.println(player.size());
//        break;
//      }
//    } catch (Exception var4) {
//      System.out.println(var4.toString());
//    }
//
//  }
}
