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
        for(int i =0; i < orders.size() - orders.getMidpoint(); i++) {
            
        }
    }
    
    public boolean processMatch(int offer, int bid) {
        boolean retry;
        if(((Order)orders.get(offer)).remove(((Order)orders.get(bid)).getVolume())) {
            retry = true;
            if(((Order)orders.get(offer)).getVolume() == 0) {
                orders.remove(offer);
                retry = false;
            }
            orders.remove(bid);
        } else {
            ((Order)orders.get(bid)).remove(((Order)orders.get(offer)).getVolume());
            orders.remove(offer);
            retry = true;
        }
        return retry;
    }
}