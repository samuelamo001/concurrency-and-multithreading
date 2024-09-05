package concurrency.deadlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockArrayList {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    private final List<Integer> list = new ArrayList<>();

    public void threadOneAddToList() throws InterruptedException {
        try {
            if (lock1.tryLock()){
                System.out.println("Thread 1 acquired lock 1");
                Thread.sleep(1000);
                if (lock2.tryLock()){
                    try {
                        System.out.println("Thread 1 acquired lock 2");
                        list.add(1);
                        System.out.println("Thread 1 added value to list");
                    } finally {
                        lock2.unlock();
                    }
                } else {
                    System.out.println("Thread 1 failed to acquire lock 2");
                }
            } else {
                System.out.println("Thread 1 failed to acquire lock 1");
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock1.unlock();
        }
    }

    public void threadTwoAddToList() throws InterruptedException {
        try {
            if (lock2.tryLock()){
                System.out.println("Thread 2 acquired lock 2");
                Thread.sleep(1000);
                if (lock1.tryLock()){
                    try {
                        System.out.println("Thread 2 acquired lock 1");
                        list.add(2);
                        System.out.println("Thread 2 added value to list");
                    } finally {
                        lock1.unlock();
                    }
                } else {
                    System.out.println("Thread 2 failed to acquire lock 1");
                }
            } else {
                System.out.println("Thread 2 failed to acquire lock 2");
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            lock2.unlock();
        }
    }
}
