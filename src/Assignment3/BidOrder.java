package Assignment3;

public class BidOrder extends Order{

    public BidOrder(long id, double price, int volume) {
        super(id, -price, volume);
    }

    public BidOrder() {
        super();
    }

    public String toString() {
        return "Bid :" + super.toString();
    }
}
