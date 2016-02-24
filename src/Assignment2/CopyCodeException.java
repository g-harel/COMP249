// -------------------------------------------------------
// Assignment 2
// Written by: Gabriel Harel - 40006459
// For COMP 249-UJ-X – Winter 2016
// --------------------------------------------------------

//this class is a child of the Exception class and is thrown when two publication codes are the same

package Assignment2;

/**
 *
 * @author Gabriel
 */
public class CopyCodeException extends Exception{
    
    //variable that stores the id of the publication causing the error
    private int toChange;
    
    //custom contructor to initialize the to Change variable
    CopyCodeException(String message, int toChange) {
        super(message);
        System.out.print(message);
        this.toChange = toChange;
    }
    
    //returns toChange variable
    public int getToChange(){
        return toChange;
    }
}
