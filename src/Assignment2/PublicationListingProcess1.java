package Assignment2;

import java.util.*;
import java.io.*;
/**
 *
 * @author Gabriel
 */
public class PublicationListingProcess1 {
    static Scanner userInput = new Scanner(System.in);
    public static Publication[] publicationArray;
    private static final String READPATH = "src/Assignment2/PublicationData_Input.txt";
    
    private static Scanner reader;
    private static BufferedWriter writer;
    
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
            reader = new Scanner(new FileReader(READPATH));
            writer = new BufferedWriter(new FileWriter(fileToWrite));
            publicationArray = new Publication[getNumberOfPublications()];
            if(publicationArray.length <= 1) {
                //handle small file
                System.out.println("File too small to have duplacates");
                //
                reader.close();
                writer.close();
            }
            else {
                correctListOfItems(reader, writer);
                reader.close();
                writer.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("The file " + READPATH + " is missing, please place it in \"src/Assignment2/\" and restart the program.");
            System.exit(0);
        } catch (IOException ex) {
            //handle exception
        }
    }
    
    public static void correctListOfItems(Scanner input, BufferedWriter output) throws IOException {
        for(int i = 0; i < publicationArray.length; i++) { 
            publicationArray[i] = new Publication(reader.nextLong(), reader.next(), reader.nextInt(), reader.next(), reader.nextDouble(), reader.nextInt());
        }
        while(true) {
            try {
                for(int i = 0 ; i < publicationArray.length ; i++) {
                    for(int j = i + 1 ; j < publicationArray.length ; j++) {
                        if(publicationArray[i].getPublicationCode() == publicationArray[j].getPublicationCode()) {
                            throw new CopyCodeException(i + " " + j);
                        }
                    }
                }
                break;
            } catch (CopyCodeException ex) {
                    System.out.print("Publication code no." + (Integer.parseInt(ex.getCulprits()[1]) + 1) + " is the same as publication code no." + (Integer.parseInt(ex.getCulprits()[0]) + 1) + ", enter the new code > ");
                    publicationArray[Integer.parseInt(ex.getCulprits()[1])].setPublicationCode(userInput.next());
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
    
    static String[] split(String entry, String seperator) {
        if(seperator.equals("whitespace")) {
            String allSpace = entry.trim().replace('\t', ' ');
            String temp = "";
            for(int i = 0 ; i < allSpace.length() ; i++) {
                if(!(i > 0 && allSpace.charAt(i) == ' ' && allSpace.charAt(i-1) == ' ')) {
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