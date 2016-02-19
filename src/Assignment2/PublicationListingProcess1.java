package Assignment2;

import java.util.*;
import java.io.*;
/**
 *
 * @author Gabriel
 */
public class PublicationListingProcess1 {
    
    private final static Scanner userInput = new Scanner(System.in);
    private static final String READFILENAME = "PublicationData_Input.txt";
    private static final String READPATH = "src/Assignment2/";
    
    private static File fileToWrite;
    
    private enum PublicationTypes{
        PUBLICATIONCODE,
        PUBLICATIONNAME,
        PUBLICATIONYEAR,
        PUBLICATIONAUTHORNAME,
        PUBLICATIONCOST,
        PUBLICATIONNBPAGES
    }
    
    public static void main(String[] args) {
        
        Publication[] publicationArray;
        System.out.print("Specify the name of the output file > ");
        while(true) {
            fileToWrite = new File("src/Assignment2/" + userInput.next() + ".txt");
            userInput.nextLine();
            if(fileToWrite.exists()){
                System.out.print("That file already exists (" + fileToWrite.length() + "bytes) enter another name > ");
            }
            else {
                break;
            }
        }
        try {
            publicationArray = arrayMaker(READFILENAME);
            if(publicationArray.length <= 1) {
                //handle small file
                System.out.println("File too small to have duplacates");
                //
            }
            else {
                correctListOfItems(publicationArray);
                System.out.println("\n\nOld file :");
                printFileItems(new File(READPATH + READFILENAME));
                System.out.println("\n\nNew file :");
                printFileItems(fileToWrite);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("The file " + READFILENAME + " is missing, please place it in \"src/Assignment2/\" and restart the program." + Arrays.toString(ex.getStackTrace()));
            System.exit(0);
        } catch (IOException ex) {
            //handle exception
        }
    }
    
    /**
     * Checks that there are no duplicated publication codes in an array of Publication objects
     * 
     * @param array
     * @throws IOException
     */
    public static void correctListOfItems(Publication[] array) throws IOException {
        System.out.println();
        while(true) {
            try {
                for(int i = 0 ; i < array.length ; i++) {
                    for(int j = i + 1 ; j < array.length ; j++) {
                        if(array[i].getPublicationCode() == array[j].getPublicationCode()) {
                            throw new CopyCodeException("Publication code no." + (j + 1) + " is the same as publication code no." + (i + 1), j);
                        }
                    }
                }
                break;
            } catch (CopyCodeException ex) {
                    System.out.print(", enter the new code > ");
                    array[ex.getToChange()].setPublicationCode(userInput.next());
                    userInput.nextLine();
            }
        }
        PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(fileToWrite, true)));
        for(int i = 0 ; i < array.length ; i++) {
            output.println(array[i].toString());
        }
        output.close();
    }
    
    /**
     * Prints the contents of a file to the console
     * 
     * @param outputFile
     * @throws FileNotFoundException 
     */
    private static void printFileItems(File inputFile) throws FileNotFoundException {
        Scanner input = new Scanner(inputFile);
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
    private static int getLines(String name) throws FileNotFoundException, IOException {
        BufferedReader input = new BufferedReader(new FileReader(READPATH + name));
        int nbPublications = 0;
        String line;
        while(true) {
            line = input.readLine();
            if(line == null) {
                break;
            }
            else if (!line.equals("")) {
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
    private static Publication[] arrayMaker(String name) throws FileNotFoundException, IOException{
        Scanner input = new Scanner(new FileReader(READPATH + name));
        Publication[] array = new Publication[getLines(name)];
        String[] fileContent;
        for(int i = 0; i < array.length; i++) { 
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
        return array;
    }
    
    /**
     * Splits inputted strings every whitespace and returns an array of the substrings
     * 
     * @param toSplit
     * @return 
     */
    private static String[] split(String toSplit) {
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