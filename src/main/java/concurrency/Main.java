package concurrency;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {


        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                counter.incrementAndGet();
            }

        };

        Thread thread = new Thread(task);
        Thread thread2 = new Thread(task);

        thread.start();
        thread2.start();
        thread.join();
        thread2.join();

        System.out.println("Final counter value: " + counter.get());


       /* PrintQueue printQueue = new PrintQueue(3);

        Printer printer = new Printer(printQueue);

        User user1 = new User(printQueue, "Document 1");
        User user2 = new User(printQueue, "Document 2");
        User user3 = new User(printQueue, "Document 3");
        User user4 = new User(printQueue, "Document 4");
        User user5 = new User(printQueue, "Document 5");

        printer.start();
        user1.start();
        user2.start();
        user3.start();
        user4.start();
        user5.start();
*/


    }
}
