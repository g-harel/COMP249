package Assignment3;

/**
 *
 * @author Gabriel
 */
public class StockMarket {

    static OrderBook book = new OrderBook();
    
    public static void main(String[] args) {
        System.out.println("starting");
        book.add(new OfferOrder(123, 20.2, 33));
        book.add(new OfferOrder(125, 21.0, 21));
        book.add(new BidOrder(122, 21.0, 21));
        book.add(new BidOrder(132, 10.0, 21));
        System.out.println(book.toString());
        book.matchingEngine();
        System.out.println(book.toString());
        System.out.println("done");
    }
}