package Assignment3;

/**
 * Doubly linked list data structure that holds a sorted list of Orders
 */
public class OrderBook {

    //best bid/offer reference
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
        //System.out.println("\tAdding " + ord.toString());
        Node temp = head.next;
        if(temp == tail || ord.getPrice() > temp.order.getPrice()) {
            //do nothing
        } else if(ord.getPrice() < tail.prev.order.getPrice()) {
            temp = tail;
        } else {
            while (temp.order != null) {
                if (ord.getPrice() > temp.order.getPrice()) {
                    break;
                }
                temp = temp.next;
            }
        }
        Node node = new Node(temp.prev, temp, ord);
        temp.prev.next = node;
        temp.prev = node;
        return temp.prev;
    }

    public void matchingEngine(Order ord) {
        System.out.println("adding>" + ord.toString());
        while(true) {
            if(bestBid != null && bestBid.order == null) {
                bestBid = null;
            } else if(bestOffer != null && bestOffer.order == null) {
                bestOffer = null;
            }
            if(ord instanceof BidOrder) {
                if(bestBid == null) {
                    bestBid = add(ord);
                } else if(bestOffer == null) {
                    if(ord.getPrice() > bestBid.order.getPrice()) {
                        bestOffer = add(ord);
                    } else {
                        add(ord);
                    }
                } else if(ord.getPrice() >= bestOffer.order.getPrice()) {
                    if(ord.getVolume() == bestOffer.order.getVolume()) {
                        bestOffer = bestOffer.prev;
                        remove(bestOffer.next);
                    } else if(ord.subtract(bestOffer.order.getVolume())) {
                        bestOffer = bestOffer.prev;
                        remove(bestOffer.next);
                        continue;
                    } else {
                        bestOffer.order.subtract(ord.getVolume());
                    }
                } else {
                    add(ord);
                }
            } else {
                if(bestOffer == null) {
                    bestOffer = add(ord);
                } else if(bestBid == null) {
                    if(ord.getPrice() < bestOffer.order.getPrice()) {
                        bestBid = add(ord);
                    } else {
                        add(ord);
                    }
                } else if(ord.getPrice() <= bestBid.order.getPrice()) {
                    if(ord.getVolume() == bestBid.order.getVolume()) {
                        bestBid = bestBid.prev;
                        remove(bestBid.next);
                    } else if(ord.subtract(bestBid.order.getVolume())) {
                        bestBid = bestBid.prev;
                        remove(bestBid.next);
                        continue;
                    } else {
                        bestBid.order.subtract(ord.getVolume());
                    }
                } else {
                    add(ord);
                }
            }
            break;
        }

        /*if(ord instanceof BidOrder) {
            if(bestOffer != null && ord.getPrice() >= bestOffer.order.getPrice()) {
                if(bestOffer.order.subtract(ord.getVolume())) {
                    if(bestOffer.order.getVolume() == 0) {
                        bestOffer = bestOffer.prev;
                        remove(bestOffer.next);
                    }
                } else {
                    ord.subtract(bestOffer.order.getVolume());
                    bestOffer = bestOffer.prev;
                    remove(bestOffer.next);
                    if(bestOffer.order != null && bestBid.order != null) {
                        matchingEngine(ord);
                    } else {
                        add(ord);
                    }
                }
            } else if(bestBid == null) {
                System.out.println("pp");
                bestBid = add(ord);
            } else {
                if(ord.getPrice() > bestBid.order.getPrice()) {
                    bestBid = add(ord);
                } else {
                    add(ord);
                }
            }
        } else {
            if(bestOffer == null && !(head.next.order instanceof OfferOrder)) {
                bestOffer = add(ord);
            } else if(bestBid != null && ord.getPrice() <= bestBid.order.getPrice()) {
                if(bestBid.order.subtract(ord.getVolume())) {
                    if(bestBid.order.getVolume() == 0) {
                        bestBid = bestBid.next;
                        remove(bestBid.prev);
                    }
                } else {
                    ord.subtract(bestBid.order.getVolume());
                    bestBid = bestBid.next;
                    remove(bestBid.prev);
                    if(bestBid.order != null && bestOffer.order != null) {
                        matchingEngine(ord);
                    } else {
                        add(ord);
                    }
                }
            } else {
                if(ord.getPrice() < bestOffer.order.getPrice()) {
                    bestOffer = add(ord);
                } else {
                    add(ord);
                }
            }
        }*/
    }

    public void outputBook() {
        Node temp = head.next;
        System.out.println("ORDER BOOK :\n======================");
        while(temp.next != null) {
            System.out.println(temp.order.toString());
            if(temp.order instanceof OfferOrder && temp.next.order instanceof BidOrder) {
                System.out.println("----------------------");
            }
            temp = temp.next;
        }
        System.out.println("----------------------");
    }

    public void outputBBO() {
        if(bestBid == null || bestOffer == null) {
            return;
        }
        System.out.println("\tBest Bid & Offer :");
        System.out.println("\t" + bestOffer.order.toString());
        System.out.println("\t" + bestBid.order.toString());
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