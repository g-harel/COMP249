package Assignment1;

// -------------------------------------------------------
// Assignment 1
// Written by: Gabriel Harel - 40006459
// For COMP 249-UJ-X – Winter 2016
// --------------------------------------------------------
/**
 * This class is intended to be used as an object of currency. It also offers some specific features
 * like the ability to convert, add, subtract and compare two different values of currency
 * 
 * @author Gabriel
 */
public class OldSwedishCurrency{
    
    private int riksdaler;
    private int skilling;
    private int runstycken;
    
    //contructor whan values are suplied, will set all the values to 0 if a negative number is submitted
    OldSwedishCurrency(int a, int b, int c) {
        if(a < 0 || b < 0 || c < 0){
            a = 0; b = 0; c = 0;
        }
        int[] newValues = normalize(a, b, c);
        this.riksdaler = newValues[0];
        this.skilling = newValues[1];
        this.runstycken = newValues[2];
    }
    //default constructor will initialize all values to 0
    OldSwedishCurrency() {
        this(0,0,0);
    }
    //copy constructor used to duplicate an existing object of this type
    OldSwedishCurrency(OldSwedishCurrency otherValue) {
        this(otherValue.riksdaler, otherValue.skilling, otherValue.runstycken);
    }

    /**
     * compares the parameter object and the calling object to evaluate if they have the same value
     * @param otherValue an OldSwedishCurrency object that will be compared to the calling object
     * @return the value of the comparison
     */
    public boolean equals(OldSwedishCurrency otherValue) {
        return (this.compareTo(otherValue) == 0);
    }

    /**
     * compares the parameter object and the calling object to evaluate which is bigger or if they are equal
     * @param otherValue an OldSwedishCurrency object that will be compared to the calling object
     * @return -1, 0 or 1 which is the difference between the calling and parameter objects
     */
    public int compareTo(OldSwedishCurrency otherValue) {
        int difference = (this.convertToRunstycken() - otherValue.convertToRunstycken());
        if(difference == 0){
            return difference;
        }
        else{
            return difference/Math.abs(difference);
        }
    }
    
    /**
     * returns a formated string of the values in this object
     * @return a formated string of the values in this object
     */
    @Override
    public String toString() {
        return (this.riksdaler + " riksdaler, " + this.skilling + " skilling, " + this.runstycken + " runstycken");
    }
    
    //calculates the values of the currency when it is rounded up to the highest denomination possible
    private static int[] normalize(int riksdaler, int skilling, int runstycken) {
        skilling = skilling+(runstycken/16);
        riksdaler = riksdaler + skilling/48;
        skilling = skilling%48;
        runstycken = runstycken%16;
        return new int[]{riksdaler, skilling, runstycken};
    }
    
    //sets the values of the calling object to the properly distributed denominations using the normalize(int,int,int) method
    private void normalize(){
        int[] newValues = normalize(this.riksdaler, this.skilling, this.runstycken);
        this.riksdaler = newValues[0];
        this.skilling = newValues[1];
        this.runstycken = newValues[2];
    }
    
    /**
     * adds the value of the parameter to the calling object's value
     * @param otherValue a value to be added
     * @return the result of the addition as a new OldSwedishCurrencyObject
     */
    public OldSwedishCurrency add(OldSwedishCurrency otherValue) {
        return new OldSwedishCurrency(0, 0, this.convertToRunstycken() + otherValue.convertToRunstycken());
    }
    
    /**
     * subtracts the value of the parameter to the calling object's value
     * @param otherValue the value to be subtracted
     * @return the result of the subtraction as a new OldSwedishCurrencyObject
     */
    public OldSwedishCurrency substract(OldSwedishCurrency otherValue) {
        return new OldSwedishCurrency(this.riksdaler - otherValue.getRiksdaler(), this.skilling - otherValue.getSkilling(), 
                                        this.runstycken - otherValue.getRunstycken());
    }
    
    /**
     * will convert the value in runstycken of the calling object
     * @return the sum of all the currencies in the runstycken denomination
     */
    public int convertToRunstycken() {
        return (this.riksdaler*48*16 + this.skilling*16 + this.runstycken);
    }
    
    /**
     * converts a value of runstycken into the bigger denominations
     * @param runstycken the value that we want to convert
     * @return the properly cut up monetary value
     */
    public OldSwedishCurrency convertFromRunstycken(int runstycken) {
        return new OldSwedishCurrency(0,0,runstycken);
    }
    
    /**
     * returns this object's riksdaler
     * @return the riksdaler value as an integer
     */
    public int getRiksdaler() {
        return riksdaler;
    }

    /**
     * sets this object's riksdaler
     * @param riksdaler
     */
    public void setRiksdaler(int riksdaler) {
        this.riksdaler = riksdaler;
        normalize();
    }

    /**
     * returns this object's skilling
     * @return the skilling value as an integer
     */
    public int getSkilling() {
        return skilling;
    }

    /**
     * sets this object's skilling
     * @param skilling
     */
    public void setSkilling(int skilling) {
        this.skilling = skilling;
        normalize();
    }

    /**
     * gets this object's runstycken
     * @return the runstycken value as an integer
     */
    public int getRunstycken() {
        return runstycken;
    }

    /**
     * sets this object's runstycken
     * @param runstycken
     */
    public void setRunstycken(int runstycken) {
        this.runstycken = runstycken;
        normalize();
    }
}