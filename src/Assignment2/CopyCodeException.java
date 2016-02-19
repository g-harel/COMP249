package Assignment2;

/**
 *
 * @author Gabriel
 */
public class CopyCodeException extends Exception{
    
    private int toChange;
    
    CopyCodeException(String message, int toChange) {
        super(message);
        System.out.print(message);
        this.toChange = toChange;
    }
    
    public int getToChange(){
        return toChange;
    }
}
