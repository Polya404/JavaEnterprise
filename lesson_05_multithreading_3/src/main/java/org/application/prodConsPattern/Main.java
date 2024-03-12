package org.application.prodConsPattern;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

        List<Thread> producers = IntStream.range(0, 10).mapToObj(i -> new Thread(() -> IntStream.range(0, 10).forEach(n -> queue.add(randomValue())))).toList();
        List<Thread> consumers = IntStream.range(0, 10).mapToObj(i -> new Thread(() -> IntStream.range(0, 10).forEach(n -> System.out.println(queue.poll())))).toList();

        producers.forEach(Thread::start);
        consumers.forEach(Thread::start);
    }

    private static Integer randomValue(){
        int min = 0;
        int max = 10000;
        return ThreadLocalRandom.current().nextInt(min, max-1);
    }
}
