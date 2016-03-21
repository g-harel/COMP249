package Assignment3;

public class Order {
    
    private long id;
    private double price;
    private int volume;

    public Order(long id, double price, int volume) {
        this.id = id;
        this.price = price;
        this.volume = volume;
    }
    
    public Order() {
        this(0,0,0);
    }
    
    public boolean subtract(int num) {
        if(this.volume >= num) {
            System.out.println("removing " + num + " from " + this.toString());
            this.volume -= num;
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return (" $" + Math.abs(this.price) + " x" + this.volume);
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
