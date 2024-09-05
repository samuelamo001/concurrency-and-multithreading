package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

    private final Queue<String> queue = new LinkedList<>();
    private final int capacity;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public PrintQueue(int capacity){
        this.capacity = capacity;
    }

    public void submitJob(String job) throws InterruptedException{
        lock.lock();
        try {
            while (queue.size() == capacity){
                System.out.println("User waiting..printer queue is full");
                notFull.await();
            }
            queue.add(job);
            System.out.println("User Submitted job: " + job);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public String processJob() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                System.out.println("Printer waiting... No jobs in the queue.");
                notEmpty.await();
            }
            String job = queue.poll();
            System.out.println("Printer processing job: " + job);

            notFull.signal();
            return job;
        } finally {
            lock.unlock();
        }
    }
}
