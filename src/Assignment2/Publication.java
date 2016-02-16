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
}
