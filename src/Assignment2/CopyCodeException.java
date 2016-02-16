package Assignment2;

/**
 *
 * @author Gabriel
 */
public class CopyCodeException extends Exception{
    CopyCodeException(String message) {
        super(message);
    }
    
    public String[] getCulprits(){
        return this.getMessage().split(" ");
    }
}
