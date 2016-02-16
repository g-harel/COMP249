package Assignment1;

// -------------------------------------------------------
// Assignment 1
// Written by: Gabriel Harel - 40006459
// For COMP 249-UJ-X – Winter 2016
// --------------------------------------------------------

import java.util.*;

/**
 * This program is made to keep track of all the items in inventory as well as provide a way to add to/edit the
 * list of items.
 * It also offer some features to analyze the contents of this inventory with the help of statistics and
 * custom searches.
 * 
 * @author Gabriel
 */
public class InventoryManager {
    
    private static final String PASSWORD = "comp249";
    
    static Scanner input = new Scanner(System.in);
    static private HouseholdGoods[] inventory = new HouseholdGoods[100];
    
    /**
     * main method contains the menus and some of the simple operations
     * @param args
     */
    public static void main(String[] args) {
        int answer;
        System.out.println("Welcome to Nancy's Household Goods Store");
        //this loops the main menu back again after the choice executes
        while(true){
            //the main menu print
            System.out.print("What would you like to do : \n" + 
                                "\t1. Enter a new item in inventory (password required)\n" + 
                                "\t2. Change information of an item in inventory (password required)\n" + 
                                "\t3. Display all items of a specific type\n" + 
                                "\t4. Display all items under a certain price\n" + 
                                "\t5. Statistics on your inventory\n" + 
                                "\t6. Quit\n" + 
                                "Enter your choice > ");
            answer = validate();
            //when the answer is option one
            if(answer == 1) {
                //checks the password
                if(validate(PASSWORD)) {
                    //will prevent from creating more objects if the array is already full
                    if(HouseholdGoods.getCount() >= inventory.length) {
                        //creates a new HouseholdGoods item with the user's values
                        System.out.print("Please enter the type of the item > ");
                        String type = validate(new String[]{"Electronics", "Apliance", "Furniture"});
                        System.out.print("Please enter the description of the item > ");
                        String description = input.nextLine();
                        System.out.print("Please enter the price (riksdaler skilling runstycken) > ");
                        inventory[HouseholdGoods.getCount()] = new HouseholdGoods(type, description, validateCurrency());           
                    }
                    else {
                        System.out.println("Sorry, but your inventory is full");
                    }
                    input.nextLine();
                }
            }
            //if the array is empty, tell the user that they cannot access any other option
            else if(inventory[0] == null){
                System.out.println("Sorry, that option is only available after you have created at least one entry");
            }
            //when the answer is option two
            else if(answer == 2) {
                //checks the password
                if(validate(PASSWORD)) {
                    //identifies the index to edit and verifies it is not an empty one
                    System.out.print("What index would you like to edit > ");
                    int toEdit = validate(); 
                    while(inventory[toEdit - 1] == null) {
                        //gives the option to return to the main if the index is empty
                        input.nextLine();
                        System.out.print("Sorry, that index is empty, do you want to enter another value (a) or return to the menu (m) ? > ");
                        if(validate(new String[]{"a", "m"}).equals("a")){
                            System.out.print("Please enter a new value > ");
                            toEdit = validate();
                        }
                        else {
                            toEdit = -1;
                            break;
                        }
                    }
                    //otherwise, will display the contents of the specific index and offer options as to what to change
                    if(toEdit != - 1){
                        System.out.println(inventory[toEdit - 1].toString());
                        System.out.print("What would you like to change : \n" + 
                                            "\t1. Type\n" + 
                                            "\t2. Description\n" +
                                            "\t3. Price\n" +
                                            "\t4. Quit\n" +
                                            "Enter your choice > ");
                        answer = validate();
                        //each of these if statements handle the answers
                        if(answer == 1) {
                            System.out.print("What would you like to change the type to > ");
                            inventory[toEdit - 1].setType(validate(new String[]{"Electronics", "Apliance", "Furniture"}));
                        }
                        else if(answer == 2) {
                            System.out.print("What would you like to change the description to > ");
                            inventory[toEdit - 1].setDescription(input.nextLine());
                        }
                        else if(answer == 3) {
                            System.out.print("Enter the new price (riksdaler skilling runstycken) > ");
                            inventory[toEdit -1].setPrice(validateCurrency());
                        }
                        else if(answer != 4) {
                            System.out.println("That is not one of the choices, lets try that again");
                        }
                    }
                }                
            }
            //when the answer is option three
            else if(answer == 3) {
                //asks what type is to be listed
                System.out.print("What type do you want me to list > ");
                String type = validate(new String[]{"Electronics", "Apliance", "Furniture"});
                //looks in the array for the required types
                for(int i = 0 ; i < HouseholdGoods.getCount() ; i++) {
                    if(inventory[i].getType().equals(type)) {
                        System.out.println(inventory[i].toString());
                    }
                }
            }
            //when the answer is option four
            else if(answer == 4) {
                //asking the upper bound of the prices
                System.out.print("Under what price do you want me to list > ");
                OldSwedishCurrency threshold = validateCurrency();
                //looking for prices under the threshold
                for(int i = 0 ; i < HouseholdGoods.getCount() ; i++) {
                    if(threshold.compareTo(inventory[i].getPrice()) == 1) {
                        System.out.println(inventory[i].toString());
                    }
                }
            }
            //when the answer is option five
            else if(answer == 5) {
                //this loop will repeat the sub-menu until option 5 is asked
                while(true) {
                    //the statistics sub-menu
                    System.out.print("What information would you like?\n" +
                                        "\t1. Cost and details of cheapest item\n" +
                                        "\t2. Cost and details of most costly item\n" +
                                        "\t3. Number of items of a each type\n" +
                                        "\t4. Average cost of items in inventory\n" +
                                        "\t5. Quit\n" +
                                        "Enter your choice > ");
                    answer = validate();
                    //when option one is asked, call the lowestPrice() method
                    if(answer == 1) {
                        System.out.println(inventory[lowestPrice()].toString());
                    }
                    //when option two is pressed, call the highestPrice() method
                    else if(answer == 2) { 
                        System.out.println(inventory[highestPrice()].toString());
                    }
                    //when answer is three
                    else if(answer == 3) { 
                        int electronics = 0;
                        int apliance = 0;
                        int furniture = 0;
                        //go through the array and count each time a type is encountered
                        for(int i = 0 ; i < HouseholdGoods.getCount() ; i++) {
                            if(inventory[i].getType().equalsIgnoreCase("electronics")) {
                                electronics++;
                            }
                            else if(inventory[i].getType().equalsIgnoreCase("apliance")) {
                                apliance++;
                            }
                            else {
                                furniture++;
                            }
                        }
                        //printing the result
                        System.out.println("There are :\n" +
                                            electronics + " electronics\n" +
                                            apliance + "apliances\n" +
                                            furniture + "furniture");
                    }
                    //when answer is four
                    else if(answer == 4) { 
                        //computing the sum with a new OldSwedishCurrency object
                        OldSwedishCurrency runstyckenSum = new OldSwedishCurrency(0,0,0);
                        for(int i = 0 ; i < HouseholdGoods.getCount() ; i++) {
                            runstyckenSum = runstyckenSum.add(inventory[i].getPrice());
                        }
                        //converting the runstycken sum to make the divison and then back to the optimal denominations before printing the result
                        System.out.println(new OldSwedishCurrency(0,0,runstyckenSum.convertToRunstycken()/HouseholdGoods.getCount()).toString());
                    }
                    //when answer is five, return to the main menu
                    else if(answer == 5) { 
                        break;
                    }
                    //if the answer is not recognized, ask for another
                    else {
                        System.out.println("That is not one of the choices, lets try that again");
                    }
                }
            }
            //when the answer is option six, quit the program
            else if(answer == 6) {
                System.out.println("Goodbye and see you soon!");
                System.exit(0);
            }
            //if the answer is not recognized, ask for another
            else {
                System.out.println("That is not one of the choices, lets try that again");
            }
        }
    }
    
    //returns the id of the item in inventory with the lowest price
    private static int lowestPrice() {
        int lowestID = 0;
        //goes through the array
        for(int i = 0 ; i < HouseholdGoods.getCount() ; i++) {
            //replaces the lowestID's value if a lower priced item is found
            if(inventory[lowestID].getPrice().compareTo(inventory[i].getPrice()) == 1) {
                lowestID = i;
            }
        }
        return lowestID;
    }
    
    //returns the id of the item in inventory with the highest price
    private static int highestPrice() {
        int highestID = 0;
        //goes through the array
        for(int i = 0 ; i < HouseholdGoods.getCount() ; i++) {
            //replaces the value of highestID's value if a more expensive item is found
            if(inventory[highestID].getPrice().compareTo(inventory[i].getPrice()) == -1) {
                highestID = i;
            }
        }
        return highestID;
    }
    
    //returns user input that is part of an array passed as parameter
    private static String validate(String[] accepted) {
        String suplied = input.next();
        input.nextLine();
        //will loop until a valid string is found
        while(true){
            //returns the user input if it is a valid entry
            for(int i = 0 ; i < accepted.length ; i++){
                if(suplied.equalsIgnoreCase(accepted[i])){
                    return suplied;
                }
            }
            //otherwise, confirm the expected values and ask for another
            System.out.print("Sorry, that is not one of the accepted inputs : ");
            for(int i = 0 ; i < accepted.length ; i++){
                System.out.print("\"" + accepted[i] + "\" ");
            }
            System.out.print("\nplease try again > ");
            suplied = input.nextLine();
        }
    }
    
    //returns true if the user submits the right password in under 4 tries
    private static boolean validate(String password){
        int counter = 0;
        input.nextLine();
        //will loop 3 times
        while(counter < 3){
            //asks for the password and checks it to the constant variable
            System.out.print("Enter the password > ");
            String suplied = input.nextLine();
            if(suplied.equals(password)){
                return true;
            }
            else{
                System.out.println("Wrong password");
                counter++;
            }
        }
        return false;
    }
    
    //cheacks user input to always return a valid integer
    private static int validate(){
       int answer; 
       //loops until a valid int is found
       while(true){   
           //making sure that the integer is valid 
           try{
                //this line will throw an exception if anything but an int is inputed
                answer = input.nextInt();
                //this block will throw an exception if the integer is negative
                if(answer >= 0){
                    return answer;
                }
                else{
                    throw new Exception("negative integer");
                }
            }catch(Exception e){
                //displays an error message if the input is not what is needed
                input.nextLine();
                System.out.print("Sorry that is not a valid int, please try again > ");
            }
        }
    }
    
    //used to standardize the input of currency
    private static OldSwedishCurrency validateCurrency() {
        //ask for and validate the inputs for each denomination
        int riksdaler, skilling, runstycken;
        System.out.print("\n\t\triskdaler  > ");
        riksdaler = validate();
        System.out.print("\t\tskilling   > ");
        skilling = validate();
        System.out.print("\t\trunstycken > ");
        runstycken = validate();
        //returning the new value
        return new OldSwedishCurrency(riksdaler, skilling, runstycken);
    }
}
