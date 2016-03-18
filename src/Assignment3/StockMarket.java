package Assignment3;

/**
 *
 * @author Gabriel
 */
public class StockMarket {
    
    OrderBook orders = new OrderBook();
    
    public static void main(String[] args) {
        System.out.println("sdf");
    }
    
    public void matchingEngine() {
        //iterate through offers
        for(int i = orders.getMidpoint(); i < orders.size(); i++) {
            //iterate through bids
            for(int j = 0; i < orders.getMidpoint(); i++) {
                //if an offer is <= to a bid, match them together and process it
                if(((Order)orders.get(i)).getPrice() <= -((Order)orders.get(j)).getPrice()) {
                    processMatch(i, j);
                    //if a match is made, the OrderBook object has changed and this method needs to be called again
                    matchingEngine();
                    //to avoid going through the rest of the array uselessly
                    return;
                }
            }
        }
        //does nothing if no matches are found
    }
    
    public void processMatch(int offer, int bid) {
        //if offer >= bid remove number of bids from offers and delete bid object
        if(((Order)orders.get(offer)).remove(((Order)orders.get(bid)).getVolume())) {
            //if offer is left at 0 after the removal, delete the offer object aswell
            if(((Order)orders.get(offer)).getVolume() == 0) {
                orders.removeOrder(offer);
            }
            orders.removeOrder(bid);
        //otherwise remove number of offers from bids and delete offer object
        } else {
            ((Order)orders.get(bid)).remove(((Order)orders.get(offer)).getVolume());
            orders.removeOrder(offer);
        }
    }
}