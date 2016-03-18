package Assignment3;

import java.util.LinkedList;

/**
 *
 * @author Gabriel
 */
public class OrderBook extends LinkedList{
    
    private int midpoint/*number of negative*/;
    
    OrderBook () {
        super();
        setMidpoint(0);
    }

    //adds a new order into the list while keeping the sorted nature
    public void newOrder(Order ord) {
        for(int i = 0; i < this.size(); i++) {
            if(ord.getPrice() >= ((Order)this.get(i)).getPrice()) {
                this.add(i+1, ord);
                setMidpoint(-1);
                break;
            }
        }
    }

    //sets the midpoint (first positive value ~> first offer) to the parameter, or finds it if the parameter == -1
    public void setMidpoint(int pos) {
        if(pos == -1) {
            //finds first positive value
            for(int i = 0; i < this.size(); i++) {
                if(((Order)this.get(i)).getPrice() > 0) {
                    this.midpoint = i;
                    break;
                }
            }
        } else {
            this.midpoint = pos;
        }
    }
    
    public int getMidpoint() {
        return this.midpoint;
    }

    //will remove element and set the midpoint to the right place
    public void removeOrder(int pos) {
        super.remove(pos);
        setMidpoint(-1);
    }
}
