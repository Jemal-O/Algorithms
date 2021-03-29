package com.lessons;

import java.util.Arrays;

public class Lesson1 {

    public static void main(String[] args) {
        System.out.println("Task1: " + task1(3.001, 2.001, 5.000) + "\n");
        System.out.println("Task2: " + task2_1(new int[]{1, 23, 4, 1, 5, 1}, 1) + "\n");
       task2_2(new int[]{1, 23, 4, 1, 5, 1}, 1);
        System.out.println("Task3.1: " + task3(12345, 0) + "\n");
        System.out.println("Task3.2: " + task3(12345) + "\n");
        System.out.println("Task4: " + task4(new int[]{3, 1, 8, 1, 9, 3, 5, 8, 9}) + "\n");
        System.out.println("Task5: " + task5(123) + "\n");
        System.out.println("Task6: " + task6(123456, 2) + "\n");
    }


    private static double task1(double a, double b, double c) {
        if (a - b < 0.001 && Math.abs(a - b) > 0.001 && a - c < 0.001 && Math.abs(a - c) > 0.001) {
            if (b - c < 0.001 && Math.abs(b - c) > 0.001)
                return b;
            else
                return c;
        }
        if (a - b > 0.01 && Math.abs(a - b) > 0.01 && a - c > 0.01 && Math.abs(a - c) > 0.01) {
            if (b - c < 0.001 && Math.abs(b - c) > 0.001)
                return c;
            else
                return b;
        }
        return a;
    }

    private static int task2_1(int[] numbers, int deleted) {
        int countDeleted = 0;
        for (int i = 0, j = numbers.length / 2; i < numbers.length / 2 || j < numbers.length; i++, j++) {
            if (i != numbers.length / 2 && numbers[i] == deleted) {
                countDeleted += 1;
            }
            if (j != numbers.length && numbers[j] == deleted) {
                countDeleted += 1;
            }
        }
        return countDeleted;
    }


    private static void task2_2(int[] numbers, int deleted) {
        int k = 0;
        for (int number : numbers) {
            if (deleted != number) {
                numbers[k] = number;
            }
        }
        System.out.println(Arrays.toString(numbers));
    }

    private static int task3(int number, int reversed) {
        if (number == 0) {
            return reversed;
        }
        return task3(number / 10, reversed * 10 + number % 10);
    }


    private static int task3(int number) {
        int reversed = 0;
        for (; number > 0; number /= 10) {
            reversed = reversed * 10 + number % 10;
        }
        return reversed;
    }

    private static int task4(int[] numbers) {
        int min = numbers[0], max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (max < numbers[i]) {
                max = numbers[i];
            }
            if (min > numbers[i]) {
                min = numbers[i];
            }
        }

        int average = (max + min) / 2;
        int result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (Math.abs(average - numbers[i]) < Math.abs(average - result)) {
                result = numbers[i];
            }
        }
        return result;
    }


    private static int task5(int number) {
        int count = 0;
        for (; number != 0; number >>= 1) {
            count += number & 1;
        }
        return count;
    }

    private static int task6(int number, int k) {
        int numberSize = 0, tempNumber = number;

        for (; tempNumber > 0; tempNumber /= 10) {
            numberSize += 1;
        }

        if (k >= numberSize) {
            k = k % numberSize;
        }
        int delimeter = (int) Math.pow(10, numberSize - k);
        return number % delimeter * (int) Math.pow(10, k) + number / delimeter;
    }

}
