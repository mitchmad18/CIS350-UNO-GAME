//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.util.LinkedList;

public class GameDoublyLinkedList extends LinkedList {
  GameDoublyLinkedList.Node head;
  GameDoublyLinkedList.Node tail;

  public GameDoublyLinkedList() {
  }

  public void add(Player player) {
    GameDoublyLinkedList.Node newNode = new GameDoublyLinkedList.Node(player);
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

  public void display() {
    GameDoublyLinkedList.Node current = this.head;
    if (this.head == null) {
      System.out.println("List is empty");
    } else {
      System.out.println("Nodes of the circular linked list: ");

      do {
        System.out.print(current.player.name + " ");
        current = current.next;
      } while(current != this.head);

      System.out.println();
    }

    System.out.println(current.player.name);
  }

  public class Node {
    Player player;
    GameDoublyLinkedList.Node next;
    GameDoublyLinkedList.Node prev;

    public Node(Player player) {
      this.player = player;
    }
  }
}
