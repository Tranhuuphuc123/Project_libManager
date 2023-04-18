package model;

public class BookShelf {
    private String boShelfID;
    private String boShelfTitle;
    private CatalogOfBooks catalogOfBooks;

    // contructor


    public BookShelf() {
    }

    public BookShelf(String boShelfID, String boShelfTitle, CatalogOfBooks catalogOfBooks) {
        this.boShelfID = boShelfID;
        this.boShelfTitle = boShelfTitle;
        this.catalogOfBooks = catalogOfBooks;
    }

    public BookShelf(String boshelfID) {
    }

    // getter and setter

    public String getBoShelfID() {
        return boShelfID;
    }

    public void setBoShelfID(String boShelfID) {
        this.boShelfID = boShelfID;
    }

    public String getBoShelfTitle() {
        return boShelfTitle;
    }

    public void setBoShelfTitle(String boShelfTitle) {
        this.boShelfTitle = boShelfTitle;
    }
}
