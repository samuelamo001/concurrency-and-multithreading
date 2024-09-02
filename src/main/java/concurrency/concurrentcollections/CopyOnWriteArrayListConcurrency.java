package concurrency.concurrentcollections;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOnWriteArrayListConcurrency {
    private CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();


    public void addFruit(String element) {
        System.out.println(Thread.currentThread().getName() + " adding: " + element);
        list.add(element);
        System.out.println(Thread.currentThread().getName() + " added: " + element);
    }


    public void printFruits() {
        System.out.println(Thread.currentThread().getName() + " printing elements: " + list);
    }

    public static void main(String[] args) {
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
