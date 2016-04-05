package Assignment3;

public class BidOrder extends Order{

    /**
     * constructor
     * @param id
     * @param price
     * @param volume
     */
    public BidOrder(String id, double price, int volume) {
        super(id, price, volume);
    }

    /**
     * default constructor
     */
    public BidOrder() {
        super();
    }

    /**
     * toString that adds the type
     * @return
     */
    public String toString() {
        return "Bid :" + super.toString();
    }
}
