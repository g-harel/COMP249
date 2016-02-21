package Assignment2;

import java.io.*;

/**
 *
 * @author Gabriel
 */
public class PublicationListingProcess2 {
    
    private enum PublicationTypes{
        PUBLICATIONCODE,
        PUBLICATIONNAME,
        PUBLICATIONYEAR,
        PUBLICATIONAUTHORNAME,
        PUBLICATIONCOST,
        PUBLICATIONNBPAGES
    }
    
    public static void main(String[] args) {
        
    }
    
    public void insertRowsToFile(String outputName) {
        
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
    
    public void binaryPublicationSearch(Publication[] publications, int start, int end, int publicationCode) {
        
    }
    
    public void sequenrialPublicationSearch(Publication[] publications, int start, int end, int publicationCode) {
        
    }
}
