package com.lessons.lesson5;

public class Lesson5 {


    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = new BloomFilter<>(4);
        bloomFilter.addToFilter("filter");
        System.out.println(bloomFilter.contains("filter"));
    }
}
