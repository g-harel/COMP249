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
            Publication[] publicationArray = PublicationListingProcess1.arrayMaker(READPATH + READFILENAME);
            System.out.print("Enter the publication number you wish to search for > ");
            long toLookFor = userInput.nextLong();
            long start = System.currentTimeMillis();
            System.out.print("Publication code \"" + toLookFor + "\" has been found after " + binaryPublicationSearch(publicationArray, 0, publicationArray.length, toLookFor) + 
                    " iterations using binary search");
            long end = System.currentTimeMillis();
            System.out.println(" and " + (end - start) + " milliseconds");
            start = System.currentTimeMillis();
            System.out.print("Publication code \"" + toLookFor + "\" has been found after " + sequentialPublicationSearch(publicationArray, 0, publicationArray.length, toLookFor) + 
                    " iterations using sequential search");
            end = System.currentTimeMillis();
            System.out.println(" and " + (end - start) + " milliseconds");
        } catch (FileNotFoundException ex) {
            System.out.println("File \"" + READFILENAME + "\" was not found, please place place it in \"" + READPATH + "\"");
            System.out.println(Arrays.toString(ex.getStackTrace()));
        } catch (IOException ex) {
            System.out.println("Something went wrong with the file!");
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * places 
     *
     * @param outputName
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void insertRowsToFile(String outputName) throws FileNotFoundException, IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputName, true))) {
            System.out.print("\tEnter the code > ");
            writer.write("\n" + userInput.next() + " ");
            userInput.nextLine();
            System.out.print("\tEnter the name > ");
            writer.write(userInput.next() + " ");
            userInput.nextLine();
            System.out.print("\tEnter the year > ");
            writer.write(userInput.next() + " ");
            userInput.nextLine();
            System.out.print("\tEnter the author name > ");
            writer.write(userInput.next() + " ");
            userInput.nextLine();
            System.out.print("\tEnter the cost > ");
            writer.write(userInput.next() + " ");
            userInput.nextLine();
            System.out.print("\tEnter the number of pages > ");
            writer.write(userInput.next());
            userInput.nextLine();
            
        }
    }
    
    /**
     * prints out the contents of a file to the console
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
