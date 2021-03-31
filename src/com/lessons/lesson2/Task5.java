package com.lessons.lesson2;

import java.util.Arrays;

public class Task5 {

    public void task5(int[] array) {
        Arrays.sort(array);
        int middle = array.length / 2;
        Node<Integer> root = new Node(array[middle]);
        for (int i = 0, j = middle + 1; i < middle || j < array.length; i++, j++) {
            if (i != middle) {
                setNode(array[i], root);
            }
            if (j != array.length) {
                setNode(array[j], root);
            }
        }
    }


    public void task5_1(int[] array) {
        Node<Integer> root = new Node(array[0]);
        for (int i = 1; i < array.length; i++) {
            setNode(array[i], root);
        }
    }

    private void setNode(int value, Node<Integer> node) {
        if (node.getValue() > value) {
            if (node.getLeft() == null) {
                node.setLeft(new Node<>(value));
                return;
            }
            setNode(value, node.getLeft());
        } else {
            if (node.getRight() == null) {
                node.setRight(new Node<>(value));
                return;
            }
            setNode(value, node.getRight());
        }
    }

    private class Node<T extends Object> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
