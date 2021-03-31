package com.lessons.lesson2;

import java.util.Arrays;

public class Lesson2 {


    public static void main(String[] args) {

        task1(new int[]{1, 2, 4, 6, 7, 10}, new int[]{1, 3, 4, 5, 7, 9});
        task2(new int[]{1, 2, 4, 6, 7, 10}, new int[]{1, 3, 4, 5, 6, 7, 9});
        System.out.println(task3(new int[]{1, 2, 1, 4, 6, 4, 6}));
        task4();
    }


    private static int[] task1(int[] array1, int[] array2) {
        int[] result = new int[array1.length + array2.length];
        int k = 0, i = 0, j = 0;
        while (k < result.length) {
            if (i == array1.length || (j != array2.length && array1[i] > array2[j])) {
                result[k++] = array2[j];
                j++;
            } else if (j == array1.length || array2[j] > array1[i]) {
                result[k++] = array1[i];
                i++;
            } else {
                result[k++] = array1[i];
                result[k++] = array2[j];
                i++;
                j++;
            }
        }
        System.out.println(Arrays.toString((result)));
        return result;
    }

    private static int[] task2(int[] array1, int[] array2) {
        int[] result = new int[array1.length < array2.length ? array1.length : array2.length];
        int k = 0, i = 0, j = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] == array2[j]) {
                result[k++] = array1[i];
                i++;
                j++;
            } else if (array1[i] > array2[j]) {
                j++;
            } else {
                i++;
            }
        }
        System.out.println(Arrays.toString((result)));
        return result;
    }

    private static int task3(int[] numbers) {
        int result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result ^= numbers[i];
        }
        return result;
    }


    private static void task4() {
        Node node = new Node(3);
        Node node1 = new Node(4);
        Node node2 = new Node(5);
        Node node3 = new Node(6);
        Node node4 = new Node(7);
        node.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        Node newOrderedNode = changeFirstAndTail(node);
    }


    private static Node changeFirstAndTail(Node first) {
        Node middle = first.getNext();
        Node last = middle.getNext();
        while (last.getNext() != null) {
            middle = middle.getNext();
            last = last.getNext();
            if (last.getNext() != null) {
                last = last.getNext();
            }
        }
        Node newFirst = middle.getNext();
        last.setNext(first);
        middle.setNext(null);
        return newFirst;

    }

}
