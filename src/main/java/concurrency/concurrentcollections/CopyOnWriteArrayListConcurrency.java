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


}
