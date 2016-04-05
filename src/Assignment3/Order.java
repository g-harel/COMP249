package Assignment3;

public class Order implements Anonymous{

    private String id;
    private double price;
    private int volume;

    /**
     * constructor
     * @param id
     * @param price
     * @param volume
     */
    public Order(String id, double price, int volume) {
        this.id = id;
        this.price = price;
        this.volume = volume;
    }

    /**
     * default constructor
     */
    public Order() {
        this("",0,0);
    }

    /**
     * returns true and subtracts the volume of the parameter to this object's volume if it is bigger
     * @param ord
     * @return
     */
    public boolean subtract(Order ord) {
        if(this.volume > ord.getVolume()) {
            System.out.println("\t] subtracting " + ord.toString() + " from " + this.toString());
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
        System.out.println(" id: " + id + " > " + this.toString());
    }

    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
