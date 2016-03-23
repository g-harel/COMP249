package Assignment3;

public class StockMarket {

    static OrderBook book = new OrderBook();
    
    public static void main(String[] args) {
        /*book.print();
        System.out.println(book.size());
        //System.out.println(book.get(0).getOrder().toString());
        book.add(0, new Order(1,9,1));
        book.print();
        book.add(1, new Order(1,8,1));
        book.print();
        book.add(0, new Order(1,7,1));
        book.print();
        book.remove(1);
        book.print();
        //book.add(1, new Order(1,3,4));
        //book.print();
       // System.out.println(book.get(1).getOrder().toString());
        System.out.println(book.size());*/
        book.add(new BidOrder(1, 148, 75));
        book.add(new BidOrder(1, 147, 200));
        book.add(new BidOrder(1, 146.6, 100));
        book.add(new BidOrder(1, 146.5, 50));
        book.add(new OfferOrder(1, 155, 200));
        book.add(new OfferOrder(1, 152.5, 120));
        book.add(new OfferOrder(1, 152, 100));
        book.add(new OfferOrder(1, 1, 300));
        book.outputBBO();
        book.matchingEngine();
        book.outputBook();
        OrderGenerator window = new OrderGenerator(book.getLastAdded().toString());
    }
}