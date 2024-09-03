package concurrency;

public class BankAccount {

    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public synchronized int withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew " + amount + " new balance " + balance);
            return amount;
        }else {
            System.out.println(Thread.currentThread().getName() + " attempted to withdraw " + amount + " but only " + balance + " available");
            return 0;
        }
    }

    public synchronized void deposit(int amount) {
        System.out.println(Thread.currentThread().getName() + " is depositing " + amount);

        balance += amount;
        System.out.println(Thread.currentThread().getName() + " completed deposit. New balance " + balance);
    }

    public int getBalance() {
        return balance;
    }
}
