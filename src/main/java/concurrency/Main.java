package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        BankAccount bankAccount = new BankAccount(1000);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Future<Integer>> futures = new ArrayList<>();

        futures.add(executorService.submit(new BankAccountOperations(bankAccount, 200)));
        futures.add(executorService.submit(new BankAccountOperations(bankAccount, 50)));
        futures.add(executorService.submit(new BankAccountOperations(bankAccount, 300)));
        futures.add(executorService.submit(new BankAccountOperations(bankAccount, 1000)));
        futures.add(executorService.submit(new BankAccountOperations(bankAccount, 1000)));
        futures.add(executorService.submit(new BankAccountOperations(bankAccount, 8000)));
        futures.add(executorService.submit(new BankAccountOperations(bankAccount, 600)));
        futures.add(executorService.submit(new BankAccountOperations(bankAccount, 900)));

        executorService.shutdown();

        int totalWithdrawn = 0;
        for (Future<Integer> future : futures) {
            try {
                totalWithdrawn += future.get();

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total withdrawn " + totalWithdrawn + " from bank account");
        System.out.println("Total balance " + bankAccount.getBalance());


    }
}
