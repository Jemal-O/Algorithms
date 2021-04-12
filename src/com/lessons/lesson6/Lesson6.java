package com.lessons.lesson6;

import java.util.*;

public class Lesson6 {


    public static void main(String[] args) {
        Map<Integer, List<Integer>> pathesMap = new HashMap<>();
        pathesMap.put(0, Arrays.asList(1, 2, 3));
        pathesMap.put(1, Arrays.asList(0, 2, 3));
        pathesMap.put(2, Arrays.asList(0, 1, 3));
        pathesMap.put(3, Arrays.asList(0, 1, 2));
        pathesMap.put(4, Arrays.asList(5, 6));
        pathesMap.put(5, Arrays.asList(4, 6));
        pathesMap.put(6, Arrays.asList(4, 5));
        pathesMap.put(7, Arrays.asList(8));
        pathesMap.put(8, Arrays.asList(7));

        boolean[] visited = new boolean[pathesMap.size()];
        int count = 0;
        for (int i : pathesMap.keySet()) {
            if (!visited[i]) {
                dfs_stack(pathesMap, visited, i);
                System.out.println("Visited component #" + count++ + "\n");
            }
        }

        //TODO: добавить точку остановки в нужной ячейке
        //TODO: путь по индексам обратно
        //TODO: пофиксить перезапись начальной точки
        int[] arr = new int[24];
        int rowCount = 6;
        findInLabirint(arr, 3, 0, rowCount);
        for (int i = 0; i < arr.length; i++) {
            if (i != 0 && i % rowCount == 0) {
                System.out.println();
            }
            System.out.print(arr[i]);
        }
    }

    public static void findInLabirint(int[] arr, int begin, int counter, int rowCount) {
        arr[begin] = counter;
        int left = begin - 1;
        int right = begin + 1;
        int below = begin + rowCount;
        int above = begin - rowCount;
        if (left >= begin / rowCount * rowCount && arr[left] != -1 && (arr[left] == 0 | arr[left] >= counter + 1)) {
            findInLabirint(arr, left, counter + 1, rowCount);
        }
        if (right < (begin / rowCount + 1) * rowCount && arr[right] != -1 && (arr[right] == 0 | arr[right] >= counter + 1)) {
            findInLabirint(arr, right, counter + 1, rowCount);
        }
        if (below < arr.length && arr[below] != -1 && (arr[below] == 0 | arr[below] >= counter + 1)) {
            findInLabirint(arr, below, counter + 1, rowCount);
        }
        if (above > 0 && arr[above] != -1 && (arr[above] == 0 | arr[above] >= counter + 1)) {
            findInLabirint(arr, above, counter + 1, rowCount);
        }


    }

    public static void dfs_stack(Map<Integer, List<Integer>> pathesMap, boolean[] visited, int begin) {
        Stack<Integer> toVisit = new Stack<>();
        toVisit.push(begin);
        visited[begin] = true;
        while (!toVisit.isEmpty()) {
            int current = toVisit.pop();
            List<Integer> nexts = pathesMap.get(current);
            System.out.println(current);
            for (int i = nexts.size() - 1; i >= 0; i--) {
                int element = nexts.get(i);
                if (!visited[element]) {
                    toVisit.push(element);
                    visited[element] = true;
                }
            }
        }
    }

    public static void dfs(Map<Integer, List<Integer>> pathesMap, boolean[] visited, int begin) {
        visited[begin] = true;
        System.out.println("Visit= " + begin);
        for (int i : pathesMap.get(begin)) {
            if (!visited[i]) {
                dfs(pathesMap, visited, i);
            }
        }
    }

    public static void bfs(Map<Integer, List<Integer>> pathesMap, boolean[] visited, int begin) {
        Queue<Integer> toVisit = new LinkedList<>();
        toVisit.add(begin);
        visited[begin] = true;
        while (!toVisit.isEmpty()) {
            int current = toVisit.poll();
            System.out.println(current);
            for (int i : pathesMap.get(current)) {
                if (!visited[i]) {
                    toVisit.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
