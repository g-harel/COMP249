package Assignment3;

public class StockMarket {

    static OrderBook book = new OrderBook();
    
    public static void main(String[] args) {
        System.out.println("starting");
        book.add(new BidOrder(1, 148, 75));
        book.add(new BidOrder(1, 147, 200));
        book.add(new BidOrder(1, 146.6, 100));
        book.add(new BidOrder(1, 146.5, 50));
        book.add(new OfferOrder(1, 155, 200));
        book.add(new OfferOrder(1, 152.5, 120));
        book.add(new OfferOrder(1, 152, 100));
        System.out.println(book.toString());
        System.out.println("done");
    }
}