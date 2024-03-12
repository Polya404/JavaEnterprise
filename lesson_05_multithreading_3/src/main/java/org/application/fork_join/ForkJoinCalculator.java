package org.application.fork_join;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculator extends RecursiveTask<Integer> {
    private final List<Integer> listToSum;

    ForkJoinCalculator(List<Integer> listToSum) {
        this.listToSum = listToSum;
    }

    @Override
    protected Integer compute() {
        if (listToSum.isEmpty()) {
            System.out.println("Found empty list, sum is 0");
            return 0;
        }

        int middleIndex = listToSum.size() / 2;
        System.out.printf("List %s, middle Index: %s%n", listToSum, middleIndex);

        List<Integer> leftSublist = listToSum.subList(0, middleIndex);
        List<Integer> rightSublist = listToSum.subList(middleIndex + 1, listToSum.size());

        ForkJoinCalculator leftSummer = new ForkJoinCalculator(leftSublist);
        ForkJoinCalculator rightSummer = new ForkJoinCalculator(rightSublist);

        leftSummer.fork();
        rightSummer.fork();

        Integer leftSum = leftSummer.join();
        Integer rightSum = rightSummer.join();
        int total = leftSum + listToSum.get(middleIndex) + rightSum;
        System.out.printf("Left sum is %s, right sum is %s, total is %s%n", leftSum, rightSum, total);

        return total;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (long count = 100000; count > 0; count--) {
            int i = 1;
            list.add(i);
        }
        ForkJoinCalculator forkJoinCalculator = new ForkJoinCalculator(list);
        forkJoinCalculator.compute();
    }
}
