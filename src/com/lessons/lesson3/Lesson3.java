package com.lessons.lesson3;

import java.util.Arrays;

public class Lesson3 {


    public static void main(String... args) {
        bubbleSort(new int[]{54, 96, 14, 77, 31});
        selectionSort(new int[]{54, 96, 14, 77, 31});
        insertionSort(new int[]{54, 96, 14, 77, 31});

        int[] arr = new int[]{54, 96, 12, 23, 65, 14, 77, 31};
        System.out.println("Quick sort: " + Arrays.toString((quickSort(arr, 0, arr.length - 1))) + "\n");

        countingSort(new int[]{51, 52, 53, 51, 53, 52, 54});
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
        System.out.println("Bubble sort: " + Arrays.toString((array)) + "\n");
    }


    private static void selectionSort(int[] array) {
        int min;
        int minI;
        for (int i = 0; i < array.length - 1; i++) {
            min = array[i];
            minI = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minI = j;
                }
            }
            int temp = array[i];
            array[i] = array[minI];
            array[minI] = temp;
        }
        System.out.println("Selection sort: " + Arrays.toString((array)) + "\n");
    }

    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i - 1;
            int exchageI = i;
            while (j >= 0 && array[exchageI] < array[j]) {
                int temp = array[j];
                array[j] = array[exchageI];
                array[exchageI] = temp;
                exchageI = j;
                j--;
            }
        }
        System.out.println("Insertion sort: " + Arrays.toString((array)) + "\n");
    }


    private static int[] quickSort(int[] array, int left, int right) {

        if (right - left <= 1) {
            return array;
        }

        int possiblePivot = array[left], possiblePivot2 = array[right / 2], possiblePivot3 = array[right];
        int pivotI = left, pivot;

        if (possiblePivot > possiblePivot2 && possiblePivot > possiblePivot3) {
            pivotI = possiblePivot2 > possiblePivot3 ? right / 2 : right;
        } else if (possiblePivot < possiblePivot2 && possiblePivot < possiblePivot3) {
            pivotI = possiblePivot2 < possiblePivot3 ? right : right / 2;
        }

        pivot = array[pivotI];

        int k = left;
        for (int i = left; i <= right; i++) {
            if (array[i] < pivot) {
                if (k == pivotI) {
                    pivotI = i;
                }
                int temp = array[k];
                array[k] = array[i];
                array[i] = temp;
                k++;
            }
        }
        array[pivotI] = array[k];
        array[k] = pivot;

        quickSort(array, left, k - 1);
        quickSort(array, k + 1, right);

        return array;
    }

    private static void countingSort(int[] array) {
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        int[] countingArray = new int[max - min + 1];
        for (int i : array) {
            countingArray[i - min] += 1;
        }
        int k = 0;
        for (int i = 0; i < countingArray.length; i++) {
            for (int j = 0; j < countingArray[i]; j++) {
                array[k] = i + min;
                k++;
            }
        }
        System.out.println("Counting sort: " + Arrays.toString((array)) + "\n");
    }

}
