package concurrency;

class Barber implements Runnable {
    private final BarberShop shop;

    public Barber(BarberShop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        try {
            shop.cutHair();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Barber was interrupted.");
        }
    }
}