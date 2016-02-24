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
