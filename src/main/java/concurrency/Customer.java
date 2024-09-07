package concurrency;

class Customer implements Runnable {
    private final BarberShop shop;
    private final String name;

    public Customer(BarberShop shop, String name) {
        this.shop = shop;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            shop.getHaircut(name);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println(name + " was interrupted.");
        }
    }
}
