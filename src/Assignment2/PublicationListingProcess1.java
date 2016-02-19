package Assignment2;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Gabriel
 */
public class PublicationListingProcess1 {
    
    private final static Scanner userInput = new Scanner(System.in);
    private static final String READFILENAME = "PublicationData_Input.txt";
    private static final String READPATH = "src/Assignment2/";
    
    private enum PublicationTypes{
        PUBLICATIONCODE,
        PUBLICATIONNAME,
        PUBLICATIONYEAR,
        PUBLICATIONAUTHORNAME,
        PUBLICATIONCOST,
        PUBLICATIONNBPAGES
    }
    
    public static void main(String[] args) {
        
        File fileToWrite;
        System.out.print("Specify the name of the output file > ");
        while(true) {
            fileToWrite = new File(READPATH + userInput.next() + ".txt");
            userInput.nextLine();
            if(fileToWrite.exists()){
                System.out.print("That file already exists (" + fileToWrite.length() + "bytes) enter another name > ");
            }
            else {
                break;
            }        
        }
        try {
            fileToWrite.createNewFile();
            if(!(new File(READPATH + READFILENAME)).exists()) {
                throw new FileNotFoundException(READFILENAME);
            }
            Scanner oldFileInput = new Scanner(new File(READPATH + READFILENAME));
            Scanner newFileInput = new Scanner(fileToWrite);
            PrintWriter newFileOutput = new PrintWriter(new File(fileToWrite.getName()));
            System.out.println("Connection to all files established");
            if(false /*|| getLines(oldFileInput) <= 1*/) {
                //handle small file
                System.out.println("File too small to have duplacates");
                //
            }
            else {
                correctListOfItems(oldFileInput, newFileOutput);
                System.out.println("\nOld file :");
                printFileItems(oldFileInput);
                System.out.println("\nNew file :");
                printFileItems(newFileInput);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("The file " + ex.getMessage() + " is missing, please place it in \"" + READPATH + "\" and restart the program." + Arrays.toString(ex.getStackTrace()));
            System.exit(0);
        } catch (IOException ex) {
            //handle exception
        }
    }
    
    /**
     * Checks that there are no duplicated publication codes in an array of Publication objects
     * 
     * @param oldFileInput
     * @param newFileOutput
     * @throws IOException
     */
    public static void correctListOfItems(Scanner oldFileInput, PrintWriter newFileOutput) throws IOException {
        System.out.println();
        Publication[] publicationArray = arrayMaker(oldFileInput);
        while(true) {
            try {
                for(int i = 0 ; i < publicationArray.length ; i++) {
                    for(int j = i + 1 ; j < publicationArray.length ; j++) {
                        if(publicationArray[i].getPublicationCode() == publicationArray[j].getPublicationCode()) {
                            throw new CopyCodeException("Publication code no." + (j + 1) + " is the same as publication code no." + (i + 1), j);
                        }
                    }
                }
                break;
            } catch (CopyCodeException ex) {
                    System.out.print(", enter the new code > ");
                    publicationArray[ex.getToChange()].setPublicationCode(userInput.next());
                    userInput.nextLine();
            }
        }
        for (Publication i : publicationArray) {
            newFileOutput.println(i.toString());
        }
    }
    
    /**
     * Prints the contents of a file to the console
     * 
     * @param outputFile
     * @throws FileNotFoundException 
     */
    private static void printFileItems(Scanner input) throws FileNotFoundException {
        while(input.hasNextLine()) {
            System.out.println(input.nextLine().trim());
        }
    }
    
    /**
     * Returns the number of non-empty lines found in the inputted filename in the READPATH directory
     * 
     * @param name
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private static int getLines(Scanner input) throws FileNotFoundException, IOException {
        int nbPublications = 0;
        String line;
        while(input.hasNextLine()) {
            line = input.nextLine();
            if (!line.equals("")) {
                nbPublications++;
            }
        }
        return nbPublications;
    }
    
    /**
     * Makes an array of Publications from the inputted filename in the READPATH directory
     * 
     * @param name
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private static Publication[] arrayMaker(Scanner input) throws FileNotFoundException, IOException{
        Publication[] array = new Publication[getLines(input)];
        System.out.println(array.length);
        String[] fileContent;
        for(int i = 0; i < array.length; i++) {
            if(input.hasNextLine()) {
                fileContent = split(input.nextLine()); 
                try {    
                    array[i] = new Publication(Long.parseLong(fileContent[0]), fileContent[1], 
                                                Integer.parseInt(fileContent[2]), fileContent[3], 
                                                Double.parseDouble(fileContent[4]), Integer.parseInt(fileContent[5]));
                } catch (NumberFormatException ex) {
                    array[i] = new Publication();
                    System.out.println("The format of publication no." + (i + 1) + " is incorrect and has been skipped");
                }
            }
        }
        return array;
    }
    
    /**
     * Splits inputted strings every whitespace and returns an array of the substrings
     * 
     * @param toSplit
     * @return 
     */
    private static String[] split(String toSplit) {
        System.out.println(toSplit);
        String allSpace = toSplit.trim().replace('\t', ' ').replace('\n', ' ');
        //not sure if necessary
        String temp = "";
        for(int i = 0 ; i < allSpace.length() ; i++) {
            if(!(i > 0 && allSpace.charAt(i) == ' ' && allSpace.charAt(i-1) == ' ')) {
                temp += allSpace.charAt(i);
            }
        }
        return temp.split(" ");
    }
}