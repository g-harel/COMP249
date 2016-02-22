package Assignment2;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class PublicationListingProcess2 {
    
    private final static Scanner userInput = new Scanner(System.in);
    private static final String READFILENAME = "PublicationData_Output.txt";
    private static final String READPATH = "src/Assignment2/test_files/";
    
    private enum PublicationTypes{
        PUBLICATIONCODE,
        PUBLICATIONNAME,
        PUBLICATIONYEAR,
        PUBLICATIONAUTHORNAME,
        PUBLICATIONCOST,
        PUBLICATIONNBPAGES
    }
    
    public static void main(String[] args) {
        try {
            while(true) {    
                System.out.print("Would you like to add any publications to the PublicationData_Ouput.txt (y/n) > ");
                String answer = userInput.nextLine();
                if(answer.equalsIgnoreCase("y")) {
                    try {
                        insertRowsToFile(READPATH + READFILENAME);
                    } catch (FileNotFoundException ex) {
                        System.out.println("File \"" + READFILENAME + "\" was not found, please place place it in \"" + READPATH + "\"");
                    } catch (IOException ex) {
                        System.out.println("Something went wrong with the file!");
                        System.out.println(ex.getMessage());
                    }
                }
                else if(answer.equalsIgnoreCase("n")){
                    break;
                }
                else {
                    System.out.println("That is not one of the options.");
                }
            }
            Publication[] publicationArray = PublicationListingProcess1.arrayMaker(READFILENAME);
            int randomIndex = (int)(Math.random()*publicationArray.length);
            binaryPublicationSearch(publicationArray, 0, publicationArray.length, publicationArray[randomIndex].getPublicationCode());
            sequenrialPublicationSearch(publicationArray, 0, publicationArray.length, publicationArray[randomIndex].getPublicationCode());
        } catch (FileNotFoundException ex) {
            System.out.println("File \"" + READFILENAME + "\" was not found, please place place it in \"" + READPATH + "\"");
        } catch (IOException ex) {
            System.out.println("Something went wrong with the file!");
            System.out.println(ex.getMessage());
        }
    }
    
    public static void insertRowsToFile(String outputName) throws FileNotFoundException, IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputName, true))) {
            System.out.print("\tEnter the code > ");
            writer.write(userInput.next());
            userInput.nextLine();
            System.out.print("\tEnter the name > ");
            writer.write(userInput.next());
            userInput.nextLine();
            System.out.print("\tEnter the year > ");
            writer.write(userInput.next());
            userInput.nextLine();
            System.out.print("\tEnter the author name > ");
            writer.write(userInput.next());
            userInput.nextLine();
            System.out.print("\tEnter the cost > ");
            writer.write(userInput.next());
            userInput.nextLine();
            System.out.print("\tEnter the number of pages > ");
            writer.write(userInput.next() + "\n");
            userInput.nextLine();
            
        }
    }
    
    public static void printFileItems(String inputName) throws FileNotFoundException, IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(inputName))) {
            String line;
            while(true) {
                line = reader.readLine();
                if(line != null && !line.equals("")) {
                    System.out.println(line);
                    continue;
                }
                break;
            } 
        }
    }
    
    public static void binaryPublicationSearch(Publication[] publications, int start, int end, long publicationCode) {
        
    }
    
    public static void sequenrialPublicationSearch(Publication[] publications, int start, int end, long publicationCode) {
        
    }
}
