package Assignment3;

import java.util.LinkedList;

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
        if(orders.size() == 0 || ord.getPrice() > orders.getLast().getPrice()) {
            orders.addLast(ord);
        } else {
            for (int i = 0; i < orders.size(); i++) {
                if (ord.getPrice() < (orders.get(i)).getPrice()) {
                    orders.add(i , ord);
                    break;
                }
            }
        }
        if(ord.getPrice() < 0) {
            bestOffer++;
        }
        bestBid = Math.max(bestOffer - 1, 0);
        System.out.println(bestBid + " " + bestOffer + orders.toString());
    }

    public void matchingEngine() {
        System.out.println("starting matching process");
        for(int i = this.bestOffer; i < orders.size(); i++) {
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
        if(orders.get(offer).remove(orders.get(bid).getVolume())) {
            if(orders.get(offer).getVolume() == 0) {
                orders.remove(offer);
            }
            orders.remove(bid);
        } else {
            orders.get(bid).remove(orders.get(offer).getVolume());
            orders.remove(offer);
        }
    }

    public String toString() {
        return orders.toString();
    }
}
