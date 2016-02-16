package Assignment2;

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
        while(true) {    
            try {
                setPublicationCode(Long.parseLong(publicationCode));
                break;
            } catch(NumberFormatException ex) {
                System.out.print("The code you entered is invalid, try again > ");
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
