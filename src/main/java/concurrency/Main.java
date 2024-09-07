package concurrency;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final int NUM_CUSTOMERS = 10;
    private static final int NUM_CHAIRS = 5;

    public static void main(String[] args) {

        BarberShop shop = new BarberShop(NUM_CHAIRS);

        long startTime = System.nanoTime();


        Thread barberThread = new Thread(new Barber(shop));
        barberThread.start();


        List<Thread> customerThreads = new ArrayList<>();
        for (int i = 1; i <= NUM_CUSTOMERS; i++) {
            Thread customerThread = new Thread(new Customer(shop, "Customer " + i));
            customerThreads.add(customerThread);
            customerThread.start();
        }


        for (Thread thread : customerThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        barberThread.interrupt();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;

        System.out.println("Benchmark completed in " + duration + " ms");
        System.out.println("Average time per customer: " + (double) duration / NUM_CUSTOMERS + " ms");

    }

}
