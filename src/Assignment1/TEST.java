package Assignment1;

public class TEST {
    
    public static void main(String[] args){
        OldSwedishCurrency gg = new OldSwedishCurrency(50,20,10);
        OldSwedishCurrency ff = gg.substract(new OldSwedishCurrency(10,10,10));
        System.out.println(ff.toString());
    }
}
