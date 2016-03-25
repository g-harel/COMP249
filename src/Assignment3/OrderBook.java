package Assignment3;

/**
 * Doubly linked list data structure that holds a sorted list of Orders
 */
public class OrderBook {

    //best bid/offer index
    private int bestBid;
    private int bestOffer;
    //stores the toString of the tail Order added
    private String lastAdded;
    //references to the head and tail elements of the doubly linked list
    private Node head;
    private Node tail;

    /**
     * default constructor, sets the best Orders to index 0 and connects both pointers
     */
    public OrderBook() {
        bestBid = 0;
        bestOffer = 0;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * adds an Order to the list while preserving the sorting
     * @param ord
     */
    public void add(Order ord) {
        //storing the size at the start since it is used often in this method
        int size = size();
        System.out.println("\tAdding " + ord.toString());
        //if it is the first element ro if the new Order's value is bigger than the last one, add it to the end
        if(size == 0 || ord.getPrice() > get(size - 1).order.getPrice()) {
            add(size, ord);
            lastAdded = ord.toString();
        } else {
            //find the first instance in the list of an Order's value being higher than the new one
            for(int i = 0; i < size; i++) {
                if (ord.getPrice() <= get(i).order.getPrice()) {
                    //add it at that index (moving the rest of the values backwards) and break out of the loop
                    add(i , ord);
                    lastAdded = ord.toString();
                    break;
                }
            }
        }
        //if the new Order is of type Bid, increment the bestOffer pointer
        if(ord instanceof BidOrder) {
            bestOffer++;
        }
    }

    /**
     * finds matches between the bids and offers, and "merges" them
     */
    public void matchingEngine() {
        //if bid >= offer and the list has both at least one bid and/or one offer
        while(Math.abs(get(bestBid).order.getPrice()) >= get(bestOffer).order.getPrice() && bestBid != bestOffer && bestOffer != size()) {
            System.out.print("\tMatch found : " + get(bestBid) + "\n\t              " + get(bestOffer) + " > ");
            //if the value of the offer >= value of the bid, subtract one from the other
            if(get(bestOffer).order.subtract(get(bestBid).order.getVolume())) {
                //if the offer is left at having 0 volume left, remove it
                if(get(bestOffer).order.getVolume() == 0) {
                    remove(bestOffer);
                }
                //remove bid and decrement the best bid index
                remove(bestBid);
                this.bestOffer--;
            } else {
            //otherwise, subtract the offer from the bid and remove the offer
                get(bestBid).order.subtract(get(bestOffer).order.getVolume());
                remove(bestOffer);
            }
        }
    }

    /**
     * outputs a formatted version of this doubly linked list
     */
    public void outputBook() {
        String book = "ORDER BOOK :\n";
        book += "======================\n";
        //offers
        for (int i = size() - 1; i >= this.bestOffer; i--) {
            book += (get(i).order.toString() + "\n");
        }
        book += "----------------------\n";
        //bids
        for (int i = 0; i < this.bestOffer; i++) {
            book += (get(i).order.toString() + "\n");
        }
        book += "----------------------\n";
        System.out.println(book);
    }

    /**
     * outputs the best bid/offer
     */
    public void outputBBO() {
        System.out.println("Best Bid & Offer :");
        System.out.println(get(bestBid).order.toString());
        System.out.println(get(bestOffer).order.toString());
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

        //copy constructor
        public Node(Node node) {
            this.prev = node.prev;
            this.next = node.next;
            this.order = node.order;
        }

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
    private void add(int index, Order ord) {
        //doesn't run if the index will cause a conflict
        if(index < 0 || index > size()) {
            System.out.println("Error, index out of bounds (add)" + index);
        } else {
            //creates a new node that references forwards to the node previously at that index and backwards at the node at index - 1
            Node next = get(index);
            Node prev = next.prev;
            Node node = new Node(prev, next, ord);
            //sets the relevant references of the nodes on either side of the new node to point to it
            prev.next = node;
            next.prev = node;
        }
    }

    /**
     * removes the node at the specified index
     * @param index
     */
    private void remove(int index) {
        //doesn't run if the index will cause a conflict
        if(index < 0 || index > size()) {
            System.out.println("Error, index out of bounds (remove)" + index);
        } else {
            //gets the node at the specified index
            Node temp = new Node(get(index));
            //removes the references to from the nodes on either side
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }

    /**
     * finds the current size of the list (while accounting for first and last null nodes)
     * @return
     */
    private int size() {
        //creates a node that will travel the list and a counter integer
        Node temp = head;
        int counter = -1;
        //while the temp node is not yet at the end, increment the count and move to the next node
        while(temp != tail) {
            counter++;
            temp = temp.next;
        }
        return counter;
    }

    /**
     * returns the node at the specific index in the list
     * @param index
     * @return
     */
    private Node get(int index) {
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
    }
}