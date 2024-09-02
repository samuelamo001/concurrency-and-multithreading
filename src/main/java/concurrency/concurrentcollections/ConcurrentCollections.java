package concurrency.concurrentcollections;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCollections {

    public Map<String, Integer> useConcurrentHashMap() throws InterruptedException {
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                concurrentMap.put("key" + i, i);
                concurrentMap.get("key" + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                concurrentMap.put("key" + i, i);
                concurrentMap.get("key" + i);
            }
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        return concurrentMap;
    }


}




