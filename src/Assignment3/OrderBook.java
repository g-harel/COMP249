package Assignment3;

public class OrderBook {

    private int bestBid;
    private int bestOffer;
    private String lastAdded;
    private Node first;
    private Node last;

    OrderBook() {
        bestBid = 0;
        bestOffer = 0;
        this.first = new Node();
        this.last = new Node();
        this.first.setNext(this.last);
        this.last.setPrev(this.first);
    }

    public void add(Order ord) {
        int size = size();
        System.out.println("\tAdding " + ord.toString());
        if(size == 0 || ord.getPrice() > get(size - 1).getPrice()) {
            add(size, ord);
            lastAdded = ord.toString();
        } else {
            for (int i = 0; i < size; i++) {
                if (ord.getPrice() <= (get(i)).getPrice()) {
                    add(i , ord);
                    lastAdded = ord.toString();
                    break;
                }
            }
        }
        if(ord instanceof BidOrder) {
            bestOffer++;
        }
    }

    public void matchingEngine() {
        while(Math.abs(get(bestBid).getPrice()) >= get(bestOffer).getPrice() && bestBid != bestOffer && bestOffer != size()) {
            System.out.print("\tMatch found : " + get(bestBid) + "\n\t              " + get(bestOffer) + " > ");
            if(get(bestOffer).subtract(get(bestBid).getVolume())) {
                if(get(bestOffer).getVolume() == 0) {
                    remove(bestOffer);
                }
                remove(bestBid);
                this.bestOffer--;
            } else {
                get(bestBid).subtract(get(bestOffer).getVolume());
                remove(bestOffer);
            }
        }
    }

    public void outputBook() {
        String book = "ORDER BOOK :\n";
        book += "======================\n";
        for (int i = size() - 1; i >= this.bestOffer; i--) {
            book += (get(i).toString() + "\n");
        }
        book += "----------------------\n";
        for (int i = 0; i < this.bestOffer; i++) {
            book += (get(i).toString() + "\n");
        }
        book += "----------------------\n";
        System.out.println(book);
    }

    public void outputBBO() {
        System.out.println("Best Bid & Offer :");
        System.out.println(get(bestBid).toString());
        System.out.println(get(bestOffer).toString());
    }

    public String getLastAdded() {
        return lastAdded;
    }

    //////////////////////////////////////////////////////////////

    public class Node {
        Node prev;
        Node next;
        Order order;

        public Node(Node prev, Node next, Order ord) {
            this.prev = prev;
            this.next = next;
            this.order = ord;
        }

        public Node(Node node) {
            this.prev = node.prev;
            this.next = node.next;
            this.order = node.order;
        }

        public Node() {
            this(null, null, new Order(-1,-1,-1));
        }

        public Order getOrder() {
            return this.order;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setNext(Node node) {
            this.next = node;
        }

        public void setPrev(Node node) {
            this.prev = node;
        }
    }

    public void add(int index, Order ord) {
        if(index < 0 || index > size()) {
            System.out.println("Error, index out of bounds (add)" + index);
        } else {
            Node next = getNode(index + 1);
            Node prev = next.getPrev();
            Node node = new Node(prev, next, ord);
            prev.setNext(node);
            next.setPrev(node);
        }
    }

    public void remove(int index) {
        if(index < 0 || index > size()) {
            System.out.println("Error, index out of bounds (remove)" + index);
        } else {
            Node temp = new Node(getNode(index + 1));
            temp.getPrev().setNext(temp.getNext());
            temp.getNext().setPrev(temp.getPrev());
        }
    }

    public int size() {
        Node temp = first;
        int counter = -1;
        while(temp != last) {
            counter++;
            temp = temp.getNext();
        }
        return counter;
    }

    public Order get(int index) {
        return getNode(index + 1).getOrder();
    }

    public Node getNode(int index) {
        index--;
        if(index < 0 || index > size()) {
            System.out.println("Error, index out of bounds (get)");
            return null;
        } else {
            Node temp = first.getNext();
            for(int i = 1; i <= index; i++) {
                temp = temp.getNext();
            }
            return temp;
        }
    }
}