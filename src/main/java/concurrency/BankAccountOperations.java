package concurrency;

import java.util.concurrent.Callable;

public class BankAccountOperations implements Callable<Integer> {
    private final BankAccount account;
    private final int amount;


    public BankAccountOperations(BankAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public Integer call() throws Exception {

            return account.withdraw(amount);
        }

    }

