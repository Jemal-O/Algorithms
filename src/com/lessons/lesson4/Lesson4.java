package com.lessons.lesson4;

import java.util.Arrays;

public class Lesson4 {


    public static void main(String... args) {
        int array[] = {1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
        heapifyMax(array, array.length);
        System.out.println("Task 1 - Heapify using siftDown: " + Arrays.toString((array)));

        //менее эффективный, реализовала просто чтобы попробовать написать siftUp
        int array2[] = {1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17};
        heapifyMax_2(array2);
        System.out.println("Task 1 - Heapify using siftUp: " + Arrays.toString((array2)));


        task2(new int[]{1, 3, 5, 4, 6, 13, 10, 2, 8, 15, 17});

        task3(3, new int[]{1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17});
    }

    private static void task2(int[] array) {
        int lastNonLeafIndex = array.length / 2 - 1;
        int roofElement = array[lastNonLeafIndex];
        int leftElement = array[getNextLeftIndex(lastNonLeafIndex)];
        int rightElement = array[getNextRightIndex(lastNonLeafIndex)];
        boolean isBinaryHeap = false;
        if (roofElement > leftElement && roofElement > rightElement) {
            isBinaryHeap = isBinaryHeap(array, lastNonLeafIndex - 1, true);
        } else if (roofElement < leftElement && roofElement < rightElement) {
            isBinaryHeap = isBinaryHeap(array, lastNonLeafIndex - 1, false);
        }
        System.out.println("Task 2 - Is binary heap?: " + isBinaryHeap);
    }

    private static boolean isBinaryHeap(int[] array, int lastNonLeafIndex, boolean isMaxBinaryHeap) {
        int rootElement;
        int leftElement;
        int rightElement;
        for (int i = lastNonLeafIndex; i >= 0; i--) {
            rootElement = array[i];
            leftElement = array[getNextLeftIndex(i)];
            rightElement = array[getNextRightIndex(i)];
            if (isMaxBinaryHeap ? isRootMin(rootElement, leftElement, rightElement)
                    : isRootMax(rootElement, leftElement, rightElement)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isRootMin(int roofElement, int leftElement, int rightElement) {
        return roofElement < leftElement || roofElement < rightElement;
    }

    private static boolean isRootMax(int roofElement, int leftElement, int rightElement) {
        return roofElement > leftElement || roofElement > rightElement;
    }


    public static void task3(int numberOfMinElements, int[] array) {
        heapifyMax(array, numberOfMinElements);
        for (int i = numberOfMinElements; i < array.length; i++) {
            if (array[0] > array[i]) {
                int swap = array[0];
                array[0] = array[i];
                array[i] = swap;
                heapifyMax(array, numberOfMinElements);
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numberOfMinElements; i++) {
            result.append(array[i]);
            if (i != numberOfMinElements - 1) {
                result.append(", ");
            }
        }
        System.out.println("Task 3 - Min elements: " + result.toString() + "\n");
    }

    private static void heapifyMax(int array[], int n) {
        int lastNonLeafIndex = (n / 2) - 1;
        for (int i = lastNonLeafIndex; i >= 0; i--) {
            siftDown(array, n, i);
        }
    }

    private static void siftDown(int array[], int n, int i) {
        int largest = i;
        int left = getNextLeftIndex(i);
        int right = getNextRightIndex(i);

        if (left < n && array[left] > array[largest])
            largest = left;

        if (right < n && array[right] > array[largest])
            largest = right;

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            siftDown(array, n, largest);
        }
    }


    private static int getNextLeftIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private static int getNextRightIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }


    private static void heapifyMax_2(int array[]) {
        for (int i = 0; i < array.length; i++) {
            siftUp(array, i);
        }
    }

    public static int[] siftUp(int array[], int i) {
        if (i == 0) {
            return array;
        }
        int parent = getParentIndex(i);
        if (array[i] > array[parent]) {
            int swap = array[parent];
            array[parent] = array[i];
            array[i] = swap;
            siftUp(array, parent);
        }
        return array;
    }

    public static int getParentIndex(int currentIndex) {
        return (currentIndex - 1) / 2;
    }


}
