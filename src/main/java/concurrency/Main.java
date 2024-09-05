package concurrency;

import concurrency.deadlock.DeadLockArrayList;
import concurrency.forkjoin.MaxValueTask;
import concurrency.threadInteruption.ArrayPrinterTask;

import java.util.Arrays;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // testing fork join pool

        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }

        ForkJoinPool pool = new ForkJoinPool();
        MaxValueTask task = new MaxValueTask(arr, 0, arr.length);
        int result = pool.invoke(task);
        System.out.println("Maximum Value: " + result);

        // testing thread deadlock

        DeadLockArrayList deadLockArrayList = new DeadLockArrayList();

        Thread threadOne = new Thread(() -> {
            try {
                deadLockArrayList.threadOneAddToList();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        Thread threadTwo = new Thread(() -> {
            try {
                deadLockArrayList.threadTwoAddToList();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        System.out.println("Task complete");

        // testing thread interruption
        int[] numbers = new int[300];
        Arrays.setAll(numbers, i -> i * 10);

        Thread taskThread = new Thread(new ArrayPrinterTask(numbers));
        taskThread.start();

        Thread.sleep(5000);

        taskThread.interrupt();

        taskThread.join();

        System.out.println("Thread completed successfully");




    }
}
