package Assignment2;

import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class Publication {
    
    private int publicationYear, publicationPages;
    private long publicationCode;
    private double publicationCost;
    private String publicationName, publicationAuthorName;

    public Publication() {
        this(0, "", 0, "", 0.0, 0);
    }
    
    public Publication(long publicationCode, String publicationName, int publicationYear, String publicationAuthorName, 
                        double publicationCost, int publicationPages) {
        this.publicationYear = publicationYear;
        this.publicationPages = publicationPages;
        this.publicationCode = publicationCode;
        this.publicationCost = publicationCost;
        this.publicationName = publicationName;
        this.publicationAuthorName = publicationAuthorName;
    }
    
    public String toString() {
        return (getPublicationCode() + " " + getPublicationName() + " " + getPublicationYear() + " " + getPublicationAuthorName() + " " + getPublicationCost() + " " + getPublicationPages());
    }
    
    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPublicationPages() {
        return publicationPages;
    }

    public void setPublicationPages(int publicationPages) {
        this.publicationPages = publicationPages;
    }
    
    public void setPublicationCode(String publicationCode) {
        Scanner input = new Scanner(System.in);
        String temp = publicationCode;
        while(true) {    
            try {
                setPublicationCode(Long.parseLong(temp));
                break;
            } catch(NumberFormatException ex) {
                System.out.print("The code you entered is invalid, try again > ");
                temp = input.next();
                input.nextLine();
            }
        }
    }

    public long getPublicationCode() {
        return publicationCode;
    }

    public void setPublicationCode(long publicationCode) {
        this.publicationCode = publicationCode;
    }

    public double getPublicationCost() {
        return publicationCost;
    }

    public void setPublicationCost(double publicationCost) {
        this.publicationCost = publicationCost;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public String getPublicationAuthorName() {
        return publicationAuthorName;
    }

    public void setPublicationAuthorname(String publicationAuthorname) {
        this.publicationAuthorName = publicationAuthorname;
    }
    
}
