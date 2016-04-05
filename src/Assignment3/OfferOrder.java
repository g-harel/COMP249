package Assignment3;

public class OfferOrder extends Order{

    /**
     * constructor
     * @param id
     * @param price
     * @param volume
     */
    public OfferOrder(String id, double price, int volume) {
        super(id, price, volume);
    }

    /**
     * default constructor
     */
    public OfferOrder() {
        super();
    }

    /**
     * toString that adds the type
     * @return
     */
    public String toString() {
        return "Off :" + super.toString();
    }
}
