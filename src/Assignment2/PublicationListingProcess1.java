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
            if(getLines(READPATH + READFILENAME) <= 1) {
                //handle small file
                System.out.println("File too small to have duplacates");
                //
            }
            else {
                correctListOfItems(READPATH + READFILENAME, READPATH + fileToWrite.getName());
                System.out.println("\nOld file :");
                printFileItems(READPATH + READFILENAME);
                System.out.println("\nNew file :");
                printFileItems(READPATH + fileToWrite.getName());
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
     * @param inputName
     * @param outputName
     * @throws IOException
     */
    public static void correctListOfItems(String inputName, String outputName) throws IOException {
        System.out.println();
        Publication[] publicationArray = arrayMaker(inputName);
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
        try(BufferedWriter output = new BufferedWriter(new FileWriter(outputName, true))) {
            for (Publication i : publicationArray) {
                output.write(i.toString() + "\n");
            }
        }
    }
    
    /**
     * Prints the contents of a file to the console
     * 
     * @param outputFile
     * @throws FileNotFoundException, IOException
     */
    private static void printFileItems(String inputName) throws FileNotFoundException, IOException {
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
     * Returns the number of non-empty lines found in the inputted filename in the READPATH directory
     * 
     * @param name
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private static int getLines(String inputName) throws FileNotFoundException, IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(inputName))) {
            int nbPublications = 0;
            String line;
            while(true) {
                line = reader.readLine();
                if(line != null && !line.equals("")) {
                    nbPublications++;
                    continue;
                }
                break;
            }
            return nbPublications;
        }
    }
    
    /**
     * Makes an array of Publications from the inputted filename in the READPATH directory
     * 
     * @param name
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private static Publication[] arrayMaker(String inputName) throws FileNotFoundException, IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader(inputName))) {
            Publication[] array = new Publication[getLines(inputName)];
            String[] fileContent;
            for(int i = 0; i < array.length; i++) {
                fileContent = split(reader.readLine()); 
                try {    
                    array[i] = new Publication(Long.parseLong(fileContent[0]), fileContent[1], Integer.parseInt(fileContent[2]), fileContent[3], 
                                                Double.parseDouble(fileContent[4]), Integer.parseInt(fileContent[5]));
                } catch (NumberFormatException ex) {
                    array[i] = new Publication();
                    System.out.println("The format of publication no." + (i + 1) + " is incorrect and has been skipped");
                }
            }
            return array;
        }
        
    }
    
    /**
     * Splits inputted strings every whitespace and returns an array of the substrings
     * 
     * @param toSplit
     * @return 
     */
    public static String[] split(String toSplit) {
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