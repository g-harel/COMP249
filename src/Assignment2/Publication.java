package Assignment2;

import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class Publication {
    int publicationYear, publicationPages;
    long publicationCode;
    double publicationCost;
    String publicationName, publicationAuthorname;

    public Publication() {
        this(0, "", 0, "", 0.0, 0);
    }
    
    public Publication(long publicationCode, String publicationName, int publicationYear, String publicationAuthorname, 
                        double publicationCost, int publicationPages) {
        this.publicationYear = publicationYear;
        this.publicationPages = publicationPages;
        this.publicationCode = publicationCode;
        this.publicationCost = publicationCost;
        this.publicationName = publicationName;
        this.publicationAuthorname = publicationAuthorname;
    }

    public String get(int i) {
        if(i == 0) {
            return "" + getPublicationCode();
        }
        else if(i == 1) {
            return getPublicationName();
        }
        else if(i == 2) {
            return "" + getPublicationYear();
        }
        else if(i == 3) {
            return getPublicationAuthorname();
        }
        else if(i == 4) {
            return "" + getPublicationAuthorname();
        }
        else if(i == 5) {
            return "" + getPublicationPages();
        }
        else {
            return null;
        }
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

    public String getPublicationAuthorname() {
        return publicationAuthorname;
    }

    public void setPublicationAuthorname(String publicationAuthorname) {
        this.publicationAuthorname = publicationAuthorname;
    }
    
}
