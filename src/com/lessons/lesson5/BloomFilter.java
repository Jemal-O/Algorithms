package com.lessons.lesson5;

import static com.sun.xml.internal.fastinfoset.util.ValueArray.MAXIMUM_CAPACITY;

public class BloomFilter<T> {

    int[] filter;
    int size;
    int binarySize;

    public BloomFilter(int buckets) {
        this.size = sizeFor(buckets);
        filter = new int[this.size];
        binarySize = this.size << 5;
    }

    private int sizeFor(int capacity) {
        int n = capacity - 1;
        n |= n >> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public void addToFilter(T object) {
        int hashCode = hashCode(object);
        int position = hashCode & (binarySize - 1);
        int bucket = position >> 5;
        int postionInBucket = position - (bucket << 5);
        int value = filter[bucket] | (int) (Math.pow(10, postionInBucket));
        filter[bucket] = value;
    }

    private int hashCode(T object) {
        return object.hashCode();
    }

    public boolean contains(T object) {
        int hashCode = hashCode(object);
        int position = hashCode & (binarySize - 1);
        int bucket = position >> 5;
        int postionInBucket = position - (bucket << 5);
        int value = filter[bucket] | (int) (Math.pow(10, postionInBucket));
        return filter[bucket] == value;
    }
}
