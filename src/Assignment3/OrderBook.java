package Assignment3;

import java.util.LinkedList;

/**
 * Created by Gabriel on 2016-03-18.
 */
public class OrderBook {

    private LinkedList<Order> orders;
    private int bestBid;
    private int bestOffer;

    OrderBook() {
        orders = new LinkedList<>();
        bestBid = 0;
        bestOffer = 0;
    }

    public void add(Order ord) {
        System.out.println("adding  > " + ord.toString());
        if(orders.size() == 0) {
            orders.add(ord);
        }
        for(int i = 0; i < orders.size(); i++) {
            if(ord.getPrice() >= (orders.get(i)).getPrice()) {
                orders.add(i+1, ord);
                setBest();
                break;
            }
        }
    }

    public void matchingEngine() {
        System.out.println("starting matching process");
        for(int i = this.bestOffer; i < orders.size(); i++) {
            System.out.print("o");
            for(int j = 0; i < this.bestBid; i++) {
                System.out.print("b" + i + " " + j);
                if(orders.get(i).getPrice() <= orders.get(j).getPrice()) {
                    System.out.println("match found");
                    smash(j, i);
                    matchingEngine();
                    return;
                }
            }
        }
    }

    public void smash(int bid, int offer) {
        System.out.println("match processing");
        //if offer >= bid smash number of bids from offers and delete bid object
        if(orders.get(offer).remove(orders.get(bid).getVolume())) {
            //if offer is left at 0 after the removal, delete the offer object first (it is after the offer's index and therefore deleting won't change it)
            if(orders.get(offer).getVolume() == 0) {
                orders.remove(offer);
            }
            orders.remove(bid);
            //otherwise smash number of offers from bids and delete offer object
        } else {
            orders.get(bid).remove(orders.get(offer).getVolume());
            orders.remove(offer);
        }
    }

    public void setBest() {
        for(int i = 0; i < orders.size(); i++) {
            if(orders.get(i).getPrice() > 0) {
                this.bestOffer = i;
                this.bestBid = i - 1;
                break;
            }
        }
    }

    public String toString() {
        return orders.toString();
    }
}
