package concurrency.benchmark;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HashMapBenchMark {
    private static final int NUM_THREADS = 10;
    private static final int NUM_ITERATIONS = 100000;

    public static void benchmarkHashMap() throws InterruptedException {
        Map<String, Integer> hashMap = new HashMap<>();
        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    hashMap.put("key" + j, j);
                    hashMap.get("key" + j);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long endTime = System.nanoTime();
        System.out.println("HashMap time taken: " + (endTime - startTime) / 1_000_000 + " ms");
    }

    public static void benchmarkConcurrentHashMap() throws InterruptedException {
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        for (int i = 0; i < NUM_THREADS; i++) {
            executor.submit(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    concurrentMap.put("key" + j, j);
                    concurrentMap.get("key" + j);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long endTime = System.nanoTime();
        System.out.println("ConcurrentHashMap time taken: " + (endTime - startTime) / 1_000_000 + " ms");
    }


}
