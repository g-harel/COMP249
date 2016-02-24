package Assignment2;

import java.io.*;
import java.util.*;

/**
 *
 * @author Gabriel
 */
public class PublicationListingProcess2 {
    
    private final static Scanner userInput = new Scanner(System.in);
    private static final String READFILENAME = "PublicationData_Output.txt";
    private static final String READPATH = "src/Assignment2/test_files/";
    private static Publication[] publicationArray;
    
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
            //making an array from the file contents
            publicationArray = PublicationListingProcess1.arrayMaker(READPATH + READFILENAME);
            //asking user for more inputs if they want
            while(true) {    
                System.out.print("Would you like to add any publications to the PublicationData_Ouput.txt (y/n) > ");
                String answer = userInput.nextLine();
                if(answer.equalsIgnoreCase("y")) {
                    insertRowsToFile(READPATH + READFILENAME);
                }
                else if(answer.equalsIgnoreCase("n")){
                    break;
                }
                else {
                    System.out.println("That is not one of the options.");
                }
            }
            //asking the user for the publication number they want to look for
            System.out.print("Enter the publication number you wish to search for > ");
            long toLookFor = userInput.nextLong();
            //binary search call
            long start = System.currentTimeMillis();
            System.out.print("Publication code \"" + toLookFor + "\" has been found after " + binaryPublicationSearch(publicationArray, 0, publicationArray.length, toLookFor) + 
                    " iterations using binary search");
            long end = System.currentTimeMillis();
            System.out.println(" and " + (end - start) + " milliseconds");
            //sequential search call
            start = System.currentTimeMillis();
            System.out.print("Publication code \"" + toLookFor + "\" has been found after " + sequentialPublicationSearch(publicationArray, 0, publicationArray.length, toLookFor) + 
                    " iterations using sequential search");
            end = System.currentTimeMillis();
            System.out.println(" and " + (end - start) + " milliseconds");
            //printing to binary file
            try(FileOutputStream out = new FileOutputStream(READPATH + "byteOut_" + READFILENAME.substring(0, READFILENAME.length() - 4) + ".dat", true)) {
                for(Publication i : publicationArray) {
                    out.write((i.toString() + "\n").getBytes());
                }
            }
        } catch (FileNotFoundException ex) {
            //error if the file is not found or doesn't exist
            System.out.println("File \"" + READFILENAME + "\" was not found, please place place it in \"" + READPATH + "\"");
            System.out.println(Arrays.toString(ex.getStackTrace()));
        } catch (IOException ex) {
            //error for any other IO related exception
            System.out.println("Something went wrong with the file!");
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * places another publication at the end of the publicationArray
     *
     * @param outputName
     */
    public static void insertRowsToFile(String outputName) {
        //making an array of Publications one bigger than the original array and copying the values of the old one
        Publication[] temp = new Publication[publicationArray.length + 1];
        for(int i = 0 ; i < temp.length -  1 ; i++) {
            temp[i] = publicationArray[i];
        }
        //creating the new Publication and changing its values to the user's specifications
        temp[temp.length - 1] = new Publication();
        System.out.print("\tEnter the code > ");
        temp[temp.length - 1].setPublicationCode(userInput.nextLong());
        userInput.nextLine();
        System.out.print("\tEnter the name > ");
        temp[temp.length - 1].setPublicationName(userInput.next());
        userInput.nextLine();
        System.out.print("\tEnter the year > ");
        temp[temp.length - 1].setPublicationYear(userInput.nextInt());
        userInput.nextLine();
        System.out.print("\tEnter the author name > ");
        temp[temp.length - 1].setPublicationAuthorname(userInput.next());
        userInput.nextLine();
        System.out.print("\tEnter the cost > ");
        temp[temp.length - 1].setPublicationCost(userInput.nextDouble());
        userInput.nextLine();
        System.out.print("\tEnter the number of pages > ");
        temp[temp.length - 1].setPublicationPages(userInput.nextInt());
        userInput.nextLine();
        //setting the original array reference to the new one
        publicationArray = temp;
    }
    
    /**
     * prints out the contents of a text document to the console
     * 
     * @param inputName
     * @throws FileNotFoundException
     * @throws IOException
     */
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
    
    /**
     * searches the array using the binary search method
     *
     * @param publications
     * @param start
     * @param end
     * @param publicationCode
     * @return
     */
    public static int binaryPublicationSearch(Publication[] publications, int start, int end, long publicationCode) {
        if(publicationCode > publications[(start + end)/2].getPublicationCode()) {
            return 1 + binaryPublicationSearch(publications, (start + end)/2, end, publicationCode);
        }
        else if (publicationCode < publications[(start + end)/2].getPublicationCode()) {
            return 1 + binaryPublicationSearch(publications, start, (start + end)/2, publicationCode);
        }
        else {
            System.out.println(publications[(start + end)/2].toString());
            return 1;
        }
    }
    
    /**
     * searches the array using the sequential search method
     *
     * @param publications
     * @param start
     * @param end
     * @param publicationCode
     * @return
     */
    public static int sequentialPublicationSearch(Publication[] publications, int start, int end, long publicationCode) {
        if(publications[start].getPublicationCode() == publicationCode) {
            System.out.println(publications[start].toString());
            return 1;
        }
        else {
            return 1 + sequentialPublicationSearch(publications, start + 1, end, publicationCode);
        }
    }
}
