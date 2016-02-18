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
    private static final String READFILENAME = "PublicationData_Input.txt";
    private static final String READPATH = "src/Assignment2/";
    
    private static File fileToWrite;
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
            reader = new Scanner(new FileReader(READPATH + READFILENAME));
            writer = new BufferedWriter(new FileWriter(fileToWrite, true));
            publicationArray = arrayMaker(READFILENAME);
            if(publicationArray.length <= 1) {
                //handle small file
                System.out.println("File too small to have duplacates");
                //
//                reader.close();
//                writer.close();
            }
            else {
                //correctListOfItems(reader, writer);
                printFileItems(fileToWrite);
//                reader.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("The file " + READFILENAME + " is missing, please place it in \"src/Assignment2/\" and restart the program." + Arrays.toString(ex.getStackTrace()));
            System.exit(0);
        } catch (IOException ex) {
            //handle exception
        }
    }
    
    public static void correctListOfItems(Scanner input, BufferedWriter output) throws IOException {
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
    
    public static void printFileItems(File outputFile) throws FileNotFoundException {
        System.out.println("Original file :\n");
        for(int i = 0 ; i < publicationArray.length ; i++) {
            System.out.print((i + 1) + ". ");
            for(int j = 0 ; j < 6 ; j++) {
                if(reader.hasNext() || true) {
                    System.out.print(reader.next() + "  ");
                }
            }
            System.out.println("");
        }
        System.out.println("Corrected file");
    }
    
    public static int getNumberOfPublications(String path) throws FileNotFoundException, IOException {
        BufferedReader input = new BufferedReader(new FileReader(path));
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
    
    public static Publication[] arrayMaker(String name) throws FileNotFoundException, IOException{
        Scanner input = new Scanner(new FileReader(READPATH + name));
        Publication[] array = new Publication[getNumberOfPublications(READPATH + name)];
        String[] fileContent;
        for(int i = 0; i < array.length; i++) { 
            fileContent = split(input.nextLine(), "whitespace");
            try {    
                array[i] = new Publication(Long.parseLong(fileContent[0]), fileContent[1], 
                                            Integer.parseInt(fileContent[2]), fileContent[3], 
                                            Double.parseDouble(fileContent[4]), Integer.parseInt(fileContent[5]));
            } catch (NumberFormatException ex) {
                publicationArray[i] = new Publication();
                System.out.println("The format of publication no." + (i + 1) + " is incorrect and has been skipped");
            }
        }
        return array;
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