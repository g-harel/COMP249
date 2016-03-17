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
        this.midpoint = 0;
    }
    
    public void newOrder(Order ord) {
        for(int i = 0; i < this.size(); i++) {
            if(ord.getPrice() >= ((Order)this.get(i)).getPrice()) {
                this.add(i+1, ord);
                setMidpoint(-1);
                break;
            }
        }
    }
    
    public void setMidpoint(int pos) {
        if(pos == -1) {
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
}
