package concurrency;

import java.util.concurrent.Semaphore;

public class BarberShop {
    private final Semaphore waitingSeats;
    private final Semaphore customers;
    private final Semaphore barberReady;

    public BarberShop(int numberOfChairs) {
        this.waitingSeats = new Semaphore(numberOfChairs);
        this.customers = new Semaphore(0);
        this.barberReady = new Semaphore(1);
    }

    public void getHaircut(String customer) throws InterruptedException {
        if (waitingSeats.tryAcquire()) {
            System.out.println(customer + " sits in the waiting area.");
            customers.release();
            barberReady.acquire();
            System.out.println(customer + " is getting a haircut.");
            waitingSeats.release();
        } else {
            System.out.println(customer + " leaves because no chair is available.");
        }
    }

    public void cutHair() throws InterruptedException {
        while (true) {
            customers.acquire();
            System.out.println("Barber is ready for the next customer.");
            barberReady.release();
            Thread.sleep(200);
            System.out.println("Barber finished cutting hair.");
        }
    }
}
