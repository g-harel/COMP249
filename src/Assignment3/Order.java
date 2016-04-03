package Assignment3;

public class Order implements Anonymous{

    private long id;
    private double price;
    private int volume;

    Order next;
    Order prev;

    public Order(long id, double price, int volume) {
        this.id = id;
        this.price = price;
        this.volume = volume;
    }

    public Order() {
        this(0,0,0);
    }

    public boolean subtract(Order ord) {
        if(this.volume > ord.getVolume()) {
            System.out.println("removing " + ord.toString() + " from " + this.toString());
            this.volume -= ord.getVolume();
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return (" $" + Math.abs(this.price) + " x" + this.volume);
    }

    public void printFullDetails() {
        System.out.println(" id" + id + this.toString());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
