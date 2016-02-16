package Assignment1;

// -------------------------------------------------------
// Assignment 1
// Written by: Gabriel Harel - 40006459
// For COMP 249-UJ-X – Winter 2016
// --------------------------------------------------------
/**
 * This class is intended to be used as an object of inventory and offers some functions to that effect
 * 
 * @author Gabriel
 */
public class HouseholdGoods {
    
    private static int count = 0;
    private int thisCount;
    private String type;
    private String description;
    private OldSwedishCurrency price;

    //constructor that will set the values to whatever is inputed as parameters
    HouseholdGoods(String type, String description, OldSwedishCurrency price) {
        this.type = type;
        this.description = description;
        this.price = new OldSwedishCurrency(price);
        thisCount = count;
        count++;
    }
    
    //default constructor that sets the values to the defaults whan none are provided
    HouseholdGoods() {
        this(null, null, new OldSwedishCurrency());
    }
    
    /**
     * checks that the calling and parameter HouseholdGoods objects have the same values
     * @param otherValue the parameter object that will be compared
     * @return the value of the comparison
     */
    public boolean equals(HouseholdGoods otherValue) {
        return (this.type.equals(otherValue.getType()) && this.description.equals(otherValue.getDescription()) && this.price.equals(otherValue.price));
    }
    
    /**
     * returns the values of the calling object in a formatted string
     * @return a string that describes all the values
     */
    @Override
    public String toString() {
        return ("Item # : " + (thisCount + 1) + "\n\tType : " + type + "\n\tDescription : " + description + "\n\tPrice : " + price.toString());
    }

    /**
     * returns the value of the static variable counting the number of HouseholdGoods objects
     * @return the integer value of the amount of this HouseholdGoods created
     */
    public static int getCount() {
        return count;
    }

    /**
     * returns the type value of the calling object
     * @return the type of the object in the form of a string
     */
    public String getType() {
        return type;
    }

    /**
     * sets the string value of type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * returns the description value of the calling object
     * @return the description of the object in the form of a string
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the string value of the description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * returns the price value of the calling object
     * @return a new object of type OldSwedishCurrency that holds the same values as the calling object's
     */
    public OldSwedishCurrency getPrice() {
        return new OldSwedishCurrency(this.price);
    }

    /**
     * sets the value of the price
     * @param price
     */
    public void setPrice(OldSwedishCurrency price) {
        this.price = price;
    }
}
