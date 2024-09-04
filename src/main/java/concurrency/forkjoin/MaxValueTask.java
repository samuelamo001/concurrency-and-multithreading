package concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

public class MaxValueTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 10;
    private final int[] numbers;
    private final int start;
    private final int end;

    public MaxValueTask(int[] numbers, int start, int mid) {
        this.numbers = numbers;
        this.start = start;
        this.end = mid;
    }

    @Override
    protected Integer compute() {
        if (end - start < THRESHOLD) {
            int max = numbers[start];
            for (int i = start + 1; i < end; i++) {
                if (numbers[i] > max) {
                    max = numbers[i];
                }
            }
            return max;
        } else {
            int mid = (start + end) / 2;
            MaxValueTask leftTask = new MaxValueTask(numbers, start, mid);
            MaxValueTask rightTask = new MaxValueTask(numbers, mid, end);
            leftTask.fork();
            int rightResult = rightTask.compute();
            int leftResult = leftTask.join();
            return Math.max(rightResult, leftResult);
        }
    }
}
