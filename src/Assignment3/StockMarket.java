package Assignment3;

public class StockMarket {

    static OrderBook book = new OrderBook();
    
    public static void main(String[] args) {
        long startTime = System.nanoTime();

//        book.matchingEngine(new BidOrder("l", 1, 3));
//        book.matchingEngine(new BidOrder("l", 1, 2));
//        book.matchingEngine(new BidOrder("l", 1, 1));
//        book.matchingEngine(new OfferOrder("l", 10, 3));
//        book.matchingEngine(new OfferOrder("l", 10, 2));
//        book.matchingEngine(new OfferOrder("l", 10, 1));
//        book.outputBBO();

//        for(int i = 0; i < 50; i++) {
//            System.out.println((int)(i/100.0*100) + "%");
//            boolean bool = (Math.random() > 0.5)?true:false;
//            if(bool) {
//                book.matchingEngine(new OfferOrder("A", Math.random()*100, (int)(Math.random()*200)));
//            } else {
//                book.matchingEngine(new BidOrder("A", Math.random()*100, (int)(Math.random()*200)));
//            }
//        }

//        for(int i = 0; i < 100000; i++) {
//            System.out.println((int)(i/100000.0*100) + "%");
//            boolean bool = (Math.random() > 0.5)?true:false;
//            if(bool) {
//                book.matchingEngine(new OfferOrder("A", Math.random()*200, (int)(Math.random()*1000)));
//            } else {
//                book.matchingEngine(new BidOrder("A", Math.random()*200, (int)(Math.random()*1000)));
//            }
//        }

        book.matchingEngine(new OfferOrder("A", 155, 200));
        book.matchingEngine(new BidOrder("B", 146.6, 100));
        book.matchingEngine(new BidOrder("C", 146.5, 50));
        book.matchingEngine(new OfferOrder("D", 152.5, 120));
        book.matchingEngine(new BidOrder("E", 148, 75));
        book.matchingEngine(new OfferOrder("F", 152, 100));
        book.matchingEngine(new BidOrder("G", 147, 200));
        book.matchingEngine(new OfferOrder("H", 1, 3000));
        book.matchingEngine(new BidOrder("I",11111,11111111));

        long endTime = System.nanoTime();
        System.out.println("execution took : " + (endTime - startTime)/1000000 + "milis");
        //OrderGenerator window = new OrderGenerator(book);
    }
}