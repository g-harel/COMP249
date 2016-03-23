package Assignment3;

public class OrderBookList {

    private int bestBid;
    private int bestOffer;
    private Order lastAdded;
    private Node first;
    private Node last;

    OrderBookList() {
        bestBid = 0;
        bestOffer = 0;
        this.first = new Node(null, null, new Order(1,999,999));
        this.last = new Node(null, null, new Order(2,999,999));
        this.first.setNext(this.last);
        this.last.setPrev(this.first);
    }

    public void add(Order ord) {
        int size = size();
        System.out.println("\tAdding " + ord.toString());
        if(size == 0 || ord.getPrice() > get(size - 1).getPrice()) {
            add(size, ord);
            lastAdded = ord;
        } else {
            for (int i = 0; i < size; i++) {
                if (ord.getPrice() <= (get(i)).getPrice()) {
                    add(i , ord);
                    lastAdded = ord;
                    break;
                }
            }
        }
        if(ord instanceof BidOrder) {
            bestOffer++;
        }
        //System.out.println(" " + bestBid + " " + bestOffer + " " + outputOrderBook());
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
        return lastAdded.toString();
    }

    //////////////////////////////////////////////////////////////

    public class Node {
        //
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
        //
        if(index < 0 || index > size()) {
            System.out.println("Error, index out of bounds (add)" + index);
        } else {
            Node next = realGet(index + 1, first);
            Node prev = next.prev;
            Node node = new Node(prev, next, ord);
            prev.setNext(node);
            next.setPrev(node);
        }
    }

    public void remove(int index) {
        //
        if(index < 0 || index > size()) {
            System.out.println("Error, index out of bounds (remove)" + index);
        } else {
            Node temp = new Node(realGet(index + 1, first));
            temp.getPrev().setNext(temp.getNext());
            temp.getNext().setPrev(temp.getPrev());
        }
    }

    public int size() {
        //
        return realSize(first) - 2;
    }

    public int realSize(Node start) {
        //
        if(start == last) {
            return 1;
        } else {
            return 1 + realSize(start.getNext());
        }
    }

    public Order get(int index) {
        //
        if(index < 0 || index > size()) {
            System.out.println("Error, index out of bounds (get)" + index);
            return null;
        } else {
            return realGet(index + 1, first).getOrder();
        }
    }

    public Node realGet(int num, Node start) {
        //
        if(num == 0) {
            return start;
        } else {
            return realGet(num - 1, start.getNext());
        }
    }

    public void print() {
        //
        String tt = "output > ";
        Node position = first;
        while(position != null) {
            tt += "{" + position.getOrder().toString() + "}";
            position = position.next;
        }
        System.out.println(tt);
    }
}