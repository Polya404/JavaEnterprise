package org.application;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;


public class ValueCalculator {
    private static final Float[] array = new Float[10000000];
    private static final int ARRAY_SIZE = array.length;
    private int numOfThreads = 1;

    public void setNumOfThreads(int numOfThreads) {
        this.numOfThreads = numOfThreads;
    }

    public void operationsWithMultipleThreads() throws InterruptedException {
        long start = System.currentTimeMillis();
        Arrays.fill(array, 1f);
        CountDownLatch latch = new CountDownLatch(numOfThreads);
        int segmentSize = ARRAY_SIZE / numOfThreads;
        for (int i = 0; i < numOfThreads; i++) {
            int startIndex = i * segmentSize;
            int endIndex = (i == numOfThreads - 1) ? ARRAY_SIZE : (i + 1) * segmentSize;
            Float[] subArray = Arrays.copyOfRange(array, startIndex, endIndex);
            Runnable task = () -> {
                changeValue(subArray);
                System.arraycopy(subArray, 0, array, startIndex, subArray.length);
                latch.countDown();
            };
            new Thread(task).start();
        }
        latch.await();
        shuffleArray();
        findMaxValue();
        long end = System.currentTimeMillis();
        System.out.println("With " + numOfThreads + " threads: " + (end - start));
    }

    private void shuffleArray() {
        List<Float> list = List.of(array);
        Collections.shuffle(List.of(list));
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
    }

    private void findMaxValue() {
        Float max = Collections.max(List.of(array));
        System.out.println("Max value: " + max);
    }

    private void changeValue(Float[] arr) {
        IntStream.range(0, arr.length).parallel().forEach(i ->
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2))
        );
    }
}

