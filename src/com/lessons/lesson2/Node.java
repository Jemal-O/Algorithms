package com.lessons.lesson2;

public class Node {

    public Node(int value) {
        this.value = value;
    }

    private int value;
    private Node next;

    public int getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
