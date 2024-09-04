package concurrency.threadInteruption;

public class ArrayPrinterTask implements Runnable{

    private final int[] numbers;

    public ArrayPrinterTask(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < numbers.length; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Task interrupted at index: " + i);
                    throw new InterruptedException();
                }
                System.out.println("Element: " + numbers[i]);
                Thread.sleep(500);
            }
            System.out.println("Task completed successfully");
        } catch (InterruptedException e) {
            System.out.println("Task was interrupted. Task was not completed successfully");
        }

    }
}
