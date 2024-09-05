package concurrency;

public class User extends Thread{

    private PrintQueue printQueue;
    private String job;

    public User(PrintQueue printQueue, String job){
        this.printQueue = printQueue;
        this.job = job;
    }

    public void run(){
        try{
            printQueue.submitJob(job);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
