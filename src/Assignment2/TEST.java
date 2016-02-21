package Assignment2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class TEST {
    public static void main(String[] args) {
        //randomizes and orders the publication numbers of the file
        //for some reason the biggest number is always at the start, but that can be fixed manually and this is not the point of the assignment
        if(/*CHANGE*/false/*THIS*/) {
            try {
                int counter = 0;
                Publication[] publications = PublicationListingProcess1.arrayMaker("src/Assignment2/test_files/PublicationData_Output.txt");
                for(int i = 0 ; i < publications.length ; i++) {
                    publications[i].setPublicationCode((long)(Math.random() * 900000000 + 100000000));
                }
                boolean bool = true;
                while(bool) {
                    bool = false;
                    int id = 0;
                    Publication temp;
                    for(int i = 0 ; i < publications.length ; i++) {
                        for(int j = i + 1 ; j < publications.length ; j++) {
                            if(publications[j].getPublicationCode() < publications[id].getPublicationCode()) {
                                bool = true;
                                id = j;
                            }
                            else if(publications[j].getPublicationCode() == publications[id].getPublicationCode()) {
                                bool = true;
                                publications[j].setPublicationCode(publications[j].getPublicationCode() + 1);
                            }
                            counter++;
                        }
                        temp = publications[i];
                        publications[i] = publications[id];
                        publications[id] = temp;
                    }
                }
                System.out.println(counter);
                for(Publication pub : publications) {
                    System.out.println(pub.toString());
                }
                PrintWriter pw = new PrintWriter("src/Assignment2/test_files/PublicationData_Output.txt");
                pw.close();
                try(BufferedWriter output = new BufferedWriter(new FileWriter("src/Assignment2/test_files/PublicationData_Output.txt", true))) {
                    for (Publication i : publications) {
                        output.write(i.toString() + "\n");
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(PublicationListingProcess2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
