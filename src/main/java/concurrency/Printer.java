package concurrency;

public class Printer extends Thread{

    private PrintQueue printQueue;


    public Printer(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    public void run() {
        try{
            while (true){
                printQueue.processJob();
                Thread.sleep(100);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }
}
