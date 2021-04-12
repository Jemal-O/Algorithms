package com.lessons.lesson5;

import static com.sun.xml.internal.fastinfoset.util.ValueArray.MAXIMUM_CAPACITY;

public class HashMap<K, V> {

    private int capacity = 16;
    private float loadFactor = 1;
    private int threshold = (int) (loadFactor * capacity);
    private int size;

    private Node<K, V>[] array;

    public HashMap() {
        array = new Node[capacity];
    }

    public HashMap(Integer capacity, Float loadFactor) {
        if (loadFactor != null && loadFactor != 0) {
            this.loadFactor = loadFactor;
        }
        if (capacity != null) {
            this.capacity = findSize(capacity);
        }
        array = new Node[capacity];
    }



    public void put(K key, V value) {
        if (size == threshold) {
            capacity <<= 1;
            threshold = (int) (capacity * loadFactor);
            Node<K, V>[] newArray = new Node[capacity];
            for (Node<K, V> node : array) {
                transferNodes(node, newArray);
            }
            array = newArray;
        }
        int position = findIndexFor(key, array.length);
        Node<K, V> current = array[position];
        if (current == null) {
            array[position] = new Node<>(key, value);
        } else {
            setValue(key, value, current);
        }
        size++;
    }

    private void transferNodes(Node<K, V> node, Node<K, V>[] newArray) {
        if (node != null) {
            int position = findIndexFor(node.getKey(), newArray.length);
            Node<K, V> current = newArray[position];
            if (current == null) {
                array[position] = node;
            } else {
                resetNode(current, node);
            }
            size++;
            if (node.hasNext()) {
                transferNodes(node.getNext(), newArray);
                node.setNext(null);
            }
        }

    }

    private void resetNode(Node<K, V> current, Node<K, V> nodeToSet) {
        if (current.hasNext()) {
            resetNode(current.getNext(), nodeToSet);
        } else {
            current.setNext(nodeToSet);
        }
    }

    private void setValue(K key, V value, Node<K, V> current) {
        if (current.getKey().hashCode() == key.hashCode() && current.getKey().equals(key)) {
            current.setValue(value);
            return;
        }
        if (current.hasNext()) {
            setValue(key, value, current.getNext());
        } else {
            current.setNext(new Node<>(key, value));
        }
    }

    private int findIndexFor(K key, int length) {
        return key.hashCode() & (length - 1);
    }


    private int findSize(int capacity) {
        capacity |= capacity >>> 16;
        return (capacity < 0) ? 1 : (capacity >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : capacity + 1;
    }




    private class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }
    }
}
