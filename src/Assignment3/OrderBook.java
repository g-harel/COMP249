package Assignment3;

/**
 * Doubly linked list data structure that holds a sorted list of Orders
 */
public class OrderBook {

    //best bid/offer reference
    private Node[] best;
    private Node bestBid;
    private Node bestOffer;
    //references to the head and tail elements of the doubly linked list
    private Node head;
    private Node tail;

    /**
     * default constructor, connects the head and tail
     */
    public OrderBook() {
        bestBid = null;
        bestOffer = null;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * adds an Order to the list while preserving the biggest to lowest sorting
     * @param ord
     */
    public Node add(Order ord) {
        Node temp = head.next;
        if(!(temp == tail || ord.getPrice() > temp.order.getPrice())) {
            if (ord.getPrice() < tail.prev.order.getPrice()) {
                temp = tail;
            } else {
                while (temp.order != null) {
                    if (ord.getPrice() > temp.order.getPrice()) {
                        break;
                    }
                    temp = temp.next;
                }
            }
        }
        Node node = new Node(temp.prev, temp, ord);
        temp.prev.next = node;
        temp.prev = node;
        return temp.prev;
    }

    public void matchingEngine(Order ord) {
        outputBBO();
        System.out.println("\t[ adding > " + ord.toString());
        while(true) {
            if(bestBid != null && bestBid.order == null) {
                bestBid = null;
            } else if(bestOffer != null && bestOffer.order == null) {
                bestOffer = null;
            }
            if(ord instanceof BidOrder) {
                if(bestBid == null && bestOffer == null) {
                    bestBid = add(ord);
                } else if(bestOffer != null && ord.getPrice() >= bestOffer.order.getPrice()) {
                    if(ord.getVolume() == bestOffer.order.getVolume()) {
                        bestOffer = bestOffer.prev;
                        remove(bestOffer.next);
                    } else if(ord.subtract(bestOffer.order)) {
                        bestOffer = bestOffer.prev;
                        remove(bestOffer.next);
                        continue;
                    } else {
                        bestOffer.order.subtract(ord);
                    }
                } else {
                    if(bestBid == null || (bestBid != null && ord.getPrice() > bestBid.order.getPrice())) {
                        bestBid = add(ord);
                    } else {
                        add(ord);
                    }
                }
            } else {
                if(bestOffer == null && bestBid == null) {
                    bestOffer = add(ord);
                } else if(bestBid != null && ord.getPrice() <= bestBid.order.getPrice()) {
                    if(ord.getVolume() == bestBid.order.getVolume()) {
                        bestBid = bestBid.next;
                        remove(bestBid.prev);
                    } else if(ord.subtract(bestBid.order)) {
                        bestBid = bestBid.next;
                        remove(bestBid.prev);
                        continue;
                    } else {
                        bestBid.order.subtract(ord);
                    }
                } else {
                    if(bestOffer == null || (bestOffer != null && ord.getPrice() < bestOffer.order.getPrice())) {
                        bestOffer = add(ord);
                    } else {
                        add(ord);
                    }
                }
            }
            break;
        }
        outputBook();
    }

    public void outputBook() {
        Node temp = head.next;
        System.out.println("ORDER BOOK :\n======================");
        while(temp.next != null) {
            System.out.println(temp.order.toString());
            if(temp.order instanceof OfferOrder && temp.next.order instanceof BidOrder) {
                System.out.println(">--------------------<");
            }
            temp = temp.next;
        }
        System.out.println("----------------------");
    }

    public void outputBBO() {
        System.out.print("\tBest Bid & Offer > ");
        if(bestBid == null) {
            System.out.print("\tbidnull");
        } else {
            System.out.print("\t" + bestBid.order.toString());
        }
        if(bestOffer == null) {
            System.out.print("\toffnull");
        } else {
            System.out.print("\t" + bestOffer.order.toString());
        }
        System.out.println();
    }

    private class Node {
        public Node prev;
        public Node next;
        public Order order;

        public Node(Node prev, Node next, Order ord) {
            this.prev = prev;
            this.next = next;
            this.order = ord;
        }

        public Node() {
            this(null, null, null);
        }
    }

    private void remove(Node temp) {
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
    }
}