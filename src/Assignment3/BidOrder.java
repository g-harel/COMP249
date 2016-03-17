package Assignment3;

/**
 *
 * @author Gabriel
 */
public class BidOrder extends Order{

    public BidOrder(long id, double price, int volume) {
        super(id, -price, volume);
    }

    public BidOrder() {
    }
}
