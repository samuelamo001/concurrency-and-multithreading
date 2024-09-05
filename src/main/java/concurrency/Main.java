package concurrency;

import concurrency.concurrentcollections.ConcurrentCollections;
import concurrency.concurrentcollections.CopyOnWriteArrayListConcurrency;
import java.util.Map;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        ConcurrentCollections hashMap = new ConcurrentCollections();
        Map<String, Integer> map = hashMap.useConcurrentHashMap();

        System.out.println("Printing map results");

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }


        CopyOnWriteArrayListConcurrency listConcurrency = new CopyOnWriteArrayListConcurrency();

        ExecutorService executor = Executors.newFixedThreadPool(3);


        Runnable task1 = () -> {
            listConcurrency.addFruit("Mango");
            listConcurrency.printFruits();
        };

        Runnable task2 = () -> {
            listConcurrency.addFruit("Pawpaw");
            listConcurrency.printFruits();
        };

        Runnable task3 = () -> {
            listConcurrency.addFruit("Apple");
            listConcurrency.printFruits();
        };


        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);

        executor.shutdown();


    }


}
