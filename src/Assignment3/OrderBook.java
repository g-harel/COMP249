package Assignment3;

import java.util.LinkedList;

public class OrderBook {

    private LinkedList<Order> orders;
    private int bestBid;
    private int bestOffer;
    private Order last;

    OrderBook() {
        orders = new LinkedList<>();
        bestBid = 0;
        bestOffer = 0;
    }

    public void add(Order ord) {
        System.out.println("\tAdding " + ord.toString());
        if(orders.size() == 0 || ord.getPrice() > orders.getLast().getPrice()) {
            orders.addLast(ord);
            last = ord;
        } else {
            for (int i = 0; i < orders.size(); i++) {
                if (ord.getPrice() <= (orders.get(i)).getPrice()) {
                    orders.add(i , ord);
                    last = ord;
                    break;
                }
            }
        }
        if(ord.getPrice() < 0) {
            bestOffer++;
        }
        //System.out.println(" " + bestBid + " " + bestOffer + " " + orders.outputOrderBook());
    }

    public void matchingEngine() {
        //while best bid >= best offer and there are both offer types in the list
        while(Math.abs(orders.get(bestBid).getPrice()) >= orders.get(bestOffer).getPrice() && bestBid != bestOffer && bestOffer != orders.size()) {
            System.out.print("\tMatch found : " + orders.get(bestBid) + "\n\t              " + orders.get(bestOffer) + " > ");
            //if amount of bids can be subtracted, do it
            if(orders.get(bestOffer).subtract(orders.get(bestBid).getVolume())) {
                //remove best offer if perfect match
                if(orders.get(bestOffer).getVolume() == 0) {
                    orders.remove(bestOffer);
                }
                //remove best bid and move the best offer pointer
                orders.remove(bestBid);
                this.bestOffer--;
            //otherwise if best bids > best offers
            } else {
                orders.get(bestBid).subtract(orders.get(bestOffer).getVolume());
                orders.remove(bestOffer);
            }
        }
    }

    public void outputBook() {
        String book = "ORDER BOOK :\n";
        book += "======================\n";
        for (int i = orders.size() - 1; i >= this.bestOffer; i--) {
            book += (orders.get(i).toString() + "\n");
        }
        book += "----------------------\n";
        for (int i = 0; i < this.bestOffer; i++) {
            book += (orders.get(i).toString() + "\n");
        }
        book += "----------------------\n";
        System.out.println(book);
    }

    public void outputBBO() {
        System.out.println("Best Bid & Offer :");
        System.out.println(orders.get(bestBid).toString());
        System.out.println(orders.get(bestOffer).toString());
    }

    public String getLast() {
        return last.toString();
    }
}
