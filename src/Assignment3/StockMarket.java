package Assignment3;

public class StockMarket {

    static OrderBook book = new OrderBook();
    
    public static void main(String[] args) {
        book.matchingEngine(new OfferOrder(1, 155, 200));
        book.matchingEngine(new BidOrder(1, 146.6, 100));
        book.matchingEngine(new BidOrder(1, 146.5, 50));
        book.matchingEngine(new OfferOrder(1, 152.5, 120));
        book.matchingEngine(new BidOrder(1, 148, 75));
        book.matchingEngine(new OfferOrder(1, 152, 100));
        book.matchingEngine(new BidOrder(1, 147, 200));
        book.matchingEngine(new OfferOrder(1, 1, 3000));
        book.matchingEngine(new BidOrder(1,11111,11111111));
        //book.outputBook();
        //OrderGenerator window = new OrderGenerator(book);
    }
}