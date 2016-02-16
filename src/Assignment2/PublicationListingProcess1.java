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
    static Scanner userInput = new Scanner(System.in);
    public static Publication[] publicationArray;
    private static final String READPATH = "src/Assignment2/PublicationData_Input.txt";
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
            fileToWrite = new File("src/Assignment2/" + userInput.next());
            userInput.nextLine();
            if(fileToWrite.exists()){
                System.out.print("That file already exists (" + fileToWrite.length() + "bytes) enter another name > ");
            }
            else {
                break;
            }
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(READPATH));
            BufferedWriter outputStream = new BufferedWriter(new FileWriter(fileToWrite));
            publicationArray = new Publication[getNumberOfPublications()];
            if(publicationArray.length <= 1) {
                //handle small file
                System.out.println("File too small to have duplacates");
                //
                reader.close();
                outputStream.close();
            }
            else {
                correctListOfItems(reader, outputStream);
                reader.close();
                outputStream.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("The file " + READPATH + " is missing, please place it in \"src/Assignment2/\" and restart the program.");
            System.exit(0);
        } catch (IOException ex) {
            //handle exception
        }
    }
    
    public static void correctListOfItems(BufferedReader input, BufferedWriter output) throws IOException {
        String[][] fileContent = new String[publicationArray.length][6];
        for(int i = 0; i < publicationArray.length; i++) {
            fileContent[i] = split(input.readLine(), "whitespace");
            try {    
                publicationArray[i] = new Publication(Long.parseLong(fileContent[i][0]), fileContent[i][1], 
                                                        Integer.parseInt(fileContent[i][2]), fileContent[i][3], 
                                                        Double.parseDouble(fileContent[i][4]), Integer.parseInt(fileContent[i][5]));
            } catch (NumberFormatException ex) {
                publicationArray[i] = new Publication();
                System.out.println("The format of publication no." + (i + 1) + " is incorrect and has been skipped");
            }
        }
        while(true) {
            try {
                for(int i = 0 ; i < publicationArray.length ; i++) {
                    for(int j = i + 1 ; j < publicationArray.length ; j++) {
                        if(publicationArray[i].getPublicationCode() == publicationArray[j].getPublicationCode()) {
                            throw new CopyCodeException();
                        }
                    }
                }
                break;
            } catch (CopyCodeException ex) {
                    System.out.print("Publication code no." + (/*placeholder*/0) + " is the same as publication code no." + (/*placeholder*/0) + ", enter the new code > ");
                    publicationArray[0/*placeholder*/].setPublicationCode(userInput.next());
                    userInput.nextLine();
            }
        }
    }
    
    public void printFileItems(File outputFile) throws FileNotFoundException {
        
    }
    
    public static int getNumberOfPublications() throws FileNotFoundException, IOException {
        BufferedReader input = new BufferedReader(new FileReader(READPATH));
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
        input.close();
        return nbPublications;
    }
    
    private static String[] split(String entry, String seperator) {
        if(seperator.equals("whitespace")) {
            String allSpace = entry.trim().replace('\t', ' ');
            String temp = "";
            for(int i = 0 ; i < allSpace.length() ; i++) {
                if(i > 0 && allSpace.charAt(i) == ' ' && allSpace.charAt(i-1) == ' ') {
                    //do nothing
                }
                else {
                    temp += allSpace.charAt(i);
                }
            }
            return temp.split(" ");
        }
        else {
            return entry.split(seperator);
        }
    }
}