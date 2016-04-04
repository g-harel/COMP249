package Assignment3;

/**
 * Doubly linked list data structure that holds a sorted list of Orders
 */
public class OrderBook {

    //best bid/offer reference
    private Node[] best = new Node[2];
    //references to the head and tail elements of the doubly linked list
    private Node head;
    private Node tail;

    /**
     * default constructor, connects the head and tail
     */
    public OrderBook() {
        best[0] = null;
        best[1] = null;
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
            if(best[0] != null && best[0].order == null) {
                best[0] = null;
            } else if(best[1] != null && best[1].order == null) {
                best[1] = null;
            }
            if(ord instanceof BidOrder) {
                if(best[0] == null && best[1] == null) {
                    best[0] = add(ord);
                } else if(best[1] != null && ord.getPrice() >= best[1].order.getPrice()) {
                    if(ord.getVolume() == best[1].order.getVolume()) {
                        best[1] = best[1].closest(1);
                        remove(best[1].closest(0));
                    } else if(ord.subtract(best[1].order)) {
                        best[1] = best[1].closest(1);
                        remove(best[1].closest(0));
                        continue;
                    } else {
                        best[1].order.subtract(ord);
                    }
                } else {
                    if(best[0] == null || (best[0] != null && ord.getPrice() > best[0].order.getPrice())) {
                        best[0] = add(ord);
                    } else {
                        add(ord);
                    }
                }
            } else {
                if(best[1] == null && best[0] == null) {
                    best[1] = add(ord);
                } else if(best[0] != null && ord.getPrice() <= best[0].order.getPrice()) {
                    if(ord.getVolume() == best[0].order.getVolume()) {
                        best[0] = best[0].closest(0);
                        remove(best[0].closest(1));
                    } else if(ord.subtract(best[0].order)) {
                        best[0] = best[0].closest(0);
                        remove(best[0].closest(1));
                        continue;
                    } else {
                        best[0].order.subtract(ord);
                    }
                } else {
                    if(best[1] == null || (best[1] != null && ord.getPrice() < best[1].order.getPrice())) {
                        best[1] = add(ord);
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
        if(best[0] == null) {
            System.out.print("\tbidnull");
        } else {
            System.out.print("\t" + best[0].order.toString());
        }
        if(best[1] == null) {
            System.out.print("\toffnull");
        } else {
            System.out.print("\t" + best[1].order.toString());
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

        public Node closest(int i) {
            return (i == 0)?next:prev;
        }
    }

    private void remove(Node temp) {
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
    }
}