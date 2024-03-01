package org.application;

import java.util.*;
import java.util.stream.IntStream;


public class ValueCalculator {
    private final Float[] array = new Float[10000000];
    private final int ARRAY_SIZE = array.length;
    private int numOfThreads = 1;


    public void setNumOfThreads(int numOfThreads) {
        this.numOfThreads = numOfThreads;
    }

    public void operationsWithMultipleThreads() {
        long start = System.currentTimeMillis();
        Arrays.fill(array, 1f);
        int segmentSize = ARRAY_SIZE / numOfThreads;
        for (int i = 0; i < numOfThreads; i++) {
            int startIndex = i * segmentSize;
            int endIndex = (i == numOfThreads - 1) ? ARRAY_SIZE : (i + 1) * segmentSize;
            Float[] subArray = Arrays.copyOfRange(array, startIndex, endIndex);
            new Thread(() -> task(subArray, startIndex)).start();
        }
        long end = System.currentTimeMillis();
        System.out.println("With " + numOfThreads + " threads: " + (end - start));
    }

    private void task(Float[] subArray, int startIndex) {
        changeValue(subArray);
        Float[] shuffled = shuffleArray(subArray);
        Float maxValue = findMaxValue(shuffled);
        System.out.println(Thread.currentThread().getName() + ": Max value: " + maxValue);
        System.arraycopy(subArray, 0, array, startIndex, subArray.length);
    }

    private Float[] shuffleArray(Float[] subArray) {
        int index;
        Float temp;
        Random random = new Random();
        for (int i = subArray.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = subArray[index];
            subArray[index] = subArray[i];
            subArray[i] = temp;
        }
        return subArray;
    }

    private Float findMaxValue(Float[] subArray) {
        return Collections.max(List.of(subArray));
    }

    private void changeValue(Float[] arr) {
        IntStream.range(0, arr.length).parallel().forEach(i ->
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2))
        );
    }
}

