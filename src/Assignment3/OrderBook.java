package Assignment3;

/**
 * Doubly linked list data structure that holds a sorted list of Orders
 */
public class OrderBook implements Anonymous{

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
        this.head = new Node(null, null, null);
        this.tail = new Node(null, null, null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * adds an Order to the list while preserving the biggest to lowest sorting
     * @param ord
     */
    private Node add(Order ord) {
        //creating a new reference to right after the head
        Node temp = head.next;
        //starts at the best bid if the order is a bid (since we know it is bigger than all offers)
        //this block cuts about 7% of execution time on 40 000 operations and about 30% on 400 000 operations
        //tested on randomly added bids/offers with prices from 0 to 200 and volumes between 0 and 1000 using Math.random
        if(ord instanceof BidOrder && bestBid != null) {
            temp = bestBid;
        }
        //if the list is empty skip this block
        if(!(temp == tail)) {
            //if the new order has the smallest value in the list, move temp to tail
            if (ord.getPrice() < tail.prev.order.getPrice()) {
                temp = tail;
            //in other cases
            } else {
                //iterate through list and save the position where the new order starts being bigger than the previous ones
                while (temp.order != null) {
                    if (ord.getPrice() > temp.order.getPrice() || (ord instanceof BidOrder && ord.getPrice() == bestBid.order.getPrice())) {
                        break;
                    }
                    temp = temp.next;
                }
            }
        }
        //create a new node where temp was
        Node node = new Node(temp.prev, temp, ord);
        //connect the nodes around temp to this new node
        temp.prev.next = node;
        temp.prev = node;
        System.out.println("\t[[ added > " + temp.prev.order.toString());
        //return the new node for use in matching engine
        return temp.prev;
    }

    /**
     * attempts to match the order before adding it to the list
     * @param ord
     */
    public void matchingEngine(Order ord) {
        System.out.println("\t[ adding > " + ord.toString());
        //loops around if the order being added has bigger volume than the one it is matched with to match it to the next onder
        while(true) {
            //setting bestBid and bestOffer to null if they are equal to the tail and head nodes
            //(the only nodes that do not have an order)
            if(bestBid != null && bestBid.order == null) {
                bestBid = null;
            } else if(bestOffer != null && bestOffer.order == null) {
                bestOffer = null;
            }
            //executing different code depending on te type of the order
            if(ord instanceof BidOrder) {
                //if this is the first element to be added > add and set to best
                if(bestBid == null && bestOffer == null) {
                    bestBid = add(ord);
                //if there is at least one of the other type and the order is "compatible" with it
                } else if(bestOffer != null && ord.getPrice() >= bestOffer.order.getPrice()) {
                    //if the two orders cancel each other out perfectly
                    if(ord.getVolume() == bestOffer.order.getVolume()) {
                        bestOffer = bestOffer.prev;
                        remove(bestOffer.next);
                    //if ord is not completely used, but not the best of opposite type
                    } else if(ord.subtract(bestOffer.order)) {
                        //>move bestOffer reference and remove the old best offer
                        bestOffer = bestOffer.prev;
                        remove(bestOffer.next);
                        //loop again
                        continue;
                    //if order is completely used
                    } else {
                        bestOffer.order.subtract(ord);
                    }
                //if the new order is not "compatible" with any order in the book
                } else {
                    //if it is better than the old best, or the first of its type > add and set as best
                    if(bestBid == null || (bestBid != null && ord.getPrice() >= bestBid.order.getPrice())) {
                        bestBid = add(ord);
                    //>add to list
                    } else {
                        add(ord);
                    }
                }
            } else {
                //if this is the first element to be added > add and set to best
                if(bestOffer == null && bestBid == null) {
                    bestOffer = add(ord);
                //if there is at least one of the other type and the order is "compatible" with it
                } else if(bestBid != null && ord.getPrice() <= bestBid.order.getPrice()) {
                    //if the two orders cancel each other out perfectly
                    if(ord.getVolume() == bestBid.order.getVolume()) {
                        bestBid = bestBid.next;
                        remove(bestBid.prev);
                    //if ord is not completely used, but not the best of opposite type
                    } else if(ord.subtract(bestBid.order)) {
                        //>move bestBid reference and remove the old best offer
                        bestBid = bestBid.next;
                        remove(bestBid.prev);
                        //loop again
                        continue;
                    //if order is completely used
                    } else {
                        bestBid.order.subtract(ord);
                    }
                //if the new order is not "compatible" with any order in the book
                } else {
                    //if it is better than the old best, or the first of its type > add and set as best
                    if(bestOffer == null || (bestOffer != null && ord.getPrice() <= bestOffer.order.getPrice())) {
                        bestOffer = add(ord);
                        //>add to list
                    } else {
                        add(ord);
                    }
                }
            }
            //breaking the loop in all cases except when "continue" is used
            break;
        }
        //showing the book after adding the new order
        outputBook(true);
        outputBBO();
    }

    /**
     * outputs the the OrderBook to console with some formatting
     */
    public void outputBook(Boolean bool) {
        Node temp = head.next;
        System.out.println("ORDER BOOK :\n======================");
        //going through the list
        while(temp.next != null) {
            if(bool) {
                System.out.println(temp.order.toString());
            } else {
                temp.order.printFullDetails();
            }
            //adding a divider between order types
            if(temp.order instanceof OfferOrder && temp.next.order instanceof BidOrder) {
                System.out.println(">--------------------<");
            }
            temp = temp.next;
        }
        System.out.println("----------------------");
    }

    /**
     * outputs the best bid and best offer
     */
    public void outputBBO() {
        System.out.print("\tBest Bid & Offer > ");
        //different output if the reference is null
        if(bestBid == null || bestBid.order == null) {
            System.out.print("\tbidnull");
        } else {
            System.out.print("\t" + bestBid.order.toString());
        }
        //different output if the reference is null
        if(bestOffer == null || bestOffer.order == null) {
            System.out.print("\toffnull");
        } else {
            System.out.print("\t" + bestOffer.order.toString());
        }
        System.out.println();
    }

    @Override
    public void printFullDetails() {
        outputBook(false);
    }

    /**
     * inner class node, an element of the doubly linked list that wraps an Order object
     */
    private class Node {
        //pointers to nodes on either side in the list
        public Node prev;
        public Node next;
        //pointer to its order
        public Order order;

        //constructor
        public Node(Node prev, Node next, Order ord) {
            this.prev = prev;
            this.next = next;
            this.order = ord;
        }
    }

    /**
     * removes a node from the list
     * @param temp
     */
    private void remove(Node temp) {
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        System.out.println("\t]] removed > " + temp.order.toString());
    }
}