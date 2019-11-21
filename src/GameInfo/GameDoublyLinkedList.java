import GameInfo.Player;

import java.util.LinkedList;

/******************************************************************************
 * A class that is created to represent the flow of the players. How to determine
 * where the action card is being directed to.
 ******************************************************************************/
public class GameDoublyLinkedList extends LinkedList {

  /**
   * The first node in the linked list
   **/
  Node head;

  /**
   * the last node in the linked list
   **/
  Node tail;

  /******************************************************************************
   * This class is used to create a node that will be used in a doubly linked list
   ******************************************************************************/
  public class Node {

    Player player;
    Node next;
    Node prev;

    /******************************************************************************
     * The default constructor of a node. The data of a node will be a player
     * @param player a player of a game
     ******************************************************************************/
    public Node(Player player) {
      this.player = player;
    }
  }

  /******************************************************************************
   * default constructor of GameDoublyLinkedList
   ******************************************************************************/
  public GameDoublyLinkedList() {
  }

  /******************************************************************************
   * Used to add a player to the end of a doubly linked list and connect it to the
   * head of the list.
   * @param player the player to be added to the game
   ******************************************************************************/
  public void add(Player player) {
    Node newNode = new Node(player);

    if (this.head == null) {
      this.head = newNode;
      this.tail = newNode;
      newNode.next = this.head;
    } else {
      this.tail.next = newNode;
      newNode.prev = this.tail;
      this.tail = newNode;
      this.tail.next = this.head;
    }

  }

//  /******************************************************************************
//   * Show all the players of a doubly linked list. Prints out the tail's next
//   * to show that it does in fact go in a circle
//   ******************************************************************************/
//  public void display() {
//    Node current = this.head;
//    if (this.head == null) {
//      System.out.println("List is empty");
//    } else {
//      System.out.println("Nodes of the circular linked list: ");
//
//      do {
//        System.out.print(current.player.name + " ");
//        current = current.next;
//      } while (current != this.head);
//
//      System.out.println();
//    }
//
//    System.out.println(current.player.name);
//  }
}
