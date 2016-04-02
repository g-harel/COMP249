package Assignment3;

/**
 * Doubly linked list data structure that holds a sorted list of Orders
 */
public class OrderBook {

    //best bid/offer reference
    private Node bestBid;
    private Node bestOffer;
    //stores the toString of the order that was last added
    private String lastAdded;
    //references to the head and tail elements of the doubly linked list
    private Node head;
    private Node tail;

    /**
     * default constructor, sets the best Orders to index 0 and connects both pointers
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
     * adds an Order to the list while preserving the sorting
     * @param ord
     */
    public Node add(Order ord) {
        System.out.println("\tAdding " + ord.toString());
        Node temp = head;
        while (temp.next != null) {
            System.out.println("tempnext!=null");
            if(ord.getPrice() > temp.order.getPrice()) {
                System.out.println("adding");
                Node node = new Node(temp, temp.next, ord);
                temp.next = node;
                temp.next.prev = node;
                lastAdded = ord.toString();
                break;
            }
            temp = temp.next;
        }
        outputBook();
        return temp.prev;
    }

    /**
     * finds matches between the bids and offers, and "merges" them
     */
    public void matchingEngine(Order ord) {
        add(ord);
        return;
        /*if(ord instanceof BidOrder) {
            if(bestBid == null) {
                System.out.println("bestbidnulll");
                bestBid = add(ord);
            } else if (ord.getPrice() <= bestOffer.order.getPrice()) {
                if(bestOffer.order.subtract(ord.getVolume())) {
                    if(bestOffer.order.getVolume() == 0) {
                        bestOffer = bestOffer.prev;
                        remove(bestOffer.next.order);
                    }
                } else {
                    ord.subtract(bestOffer.order.getVolume());
                    bestOffer = bestOffer.prev;
                    remove(bestOffer.next.order);
                    matchingEngine(ord);
                }
            } else {
                add(ord);
            }
        } else {
            if(bestOffer == null) {
                System.out.println("bestoffernull");
                bestOffer = add(ord);
            } else if (ord.getPrice() >= bestBid.order.getPrice()) {
                if(bestBid.order.subtract(ord.getVolume())) {
                    if(bestBid.order.getVolume() == 0) {
                        bestBid = bestBid.next;
                        remove(bestBid.prev.order);
                    }
                } else {
                    ord.subtract(bestBid.order.getVolume());
                    bestBid = bestBid.next;
                    remove(bestBid.prev.order);
                    matchingEngine(ord);
                }
            } else {
                add(ord);
            }
        }
        outputBBO();
        outputBook();*/
    }

    /**
     * outputs a formatted version of this doubly linked list
     */
    public void outputBook() {
        //
        Node temp = head.next;
        //System.out.println("ORDER BOOK :\n======================");
        while(temp.next != null) {
            System.out.println(temp.order.toString());
            if(temp.order instanceof OfferOrder && temp.next.order instanceof BidOrder) {
                //System.out.println("----------------------");
            }
            temp = temp.next;
        }
    }

    /**
     * outputs the best bid/offer
     */
    public void outputBBO() {
        if(bestBid == null || bestOffer == null) {
            return;
        }
        System.out.println("Best Bid & Offer :");
        System.out.println(bestBid.order.toString());
        System.out.println(bestOffer.order.toString());
    }

    /**
     * returns the last added String
     * @return
     */
    public String getLastAdded() {
        return lastAdded;
    }

    /////////// LINKED LIST METHODS BELOW THIS POINT /////////////
    //////////////////////////////////////////////////////////////

    //subclass of type node
    private class Node {
        //stores a reference to previous/next nodes
        public Node prev;
        public Node next;
        //and a reference to the order it represents
        public Order order;

        //constructor when all the fields are provided
        public Node(Node prev, Node next, Order ord) {
            this.prev = prev;
            this.next = next;
            this.order = ord;
        }

        /*//copy constructor
        public Node(Node node) {
            this.prev = node.prev;
            this.next = node.next;
            this.order = node.order;
        }*/

        //default constructor sets everything to null
        public Node() {
            this(null, null, null);
        }
    }


    /**
     * adds a new Order (by using a node) to the list at the index specified as parameter
     * @param index
     * @param ord
     */
    /*private void add(int index, Order ord) {
        Node temp = head;
        while(temp.next != null && index >= 0) {
            if(index == 0) {
                Node prev = temp.prev;
                Node node = new Node(prev, temp, ord);
                prev.next = node;
                temp.prev = node;
                break;
            } else {
                temp = temp.next;
            }
            index--;
        }
    }*/

    /**
     * removes the node containing the specific order
     * @param ord
     */
    private void remove(Order ord) {
        Node temp = head;
        while(temp.next != null) {
            if(temp.order == ord) {
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                break;
            } else {
                temp = temp.next;
            }
        }
    }

    /**
     * finds the current size of the list (while accounting for first and last null nodes)
     * @return
     */
    /*private int size() {
        //creates a node that will travel the list and a counter integer
        Node temp = head;
        int counter = -1;
        //while the temp node is not yet at the end, increment the count and move to the next node
        while(temp != tail) {
            counter++;
            temp = temp.next;
        }
        return counter;
    }*/

    /**
     * returns the node at the specific index in the list
     * @param index
     * @return
     */
    /*private Node get(int index) {
        //doesn't run if the index will cause a conflict
        if(index < 0 || index > size()) {
            System.out.println("Error, index out of bounds (get)");
            return null;
        } else {
            //creates a node to travel the list
            Node temp = head.next;
            //moves the temp node through the list "index" times
            for(int i = 1; i <= index; i++) {
                temp = temp.next;
            }
            return temp;
        }
    }*/
}