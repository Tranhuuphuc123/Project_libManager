package model;

import java.util.ArrayList;
import java.util.List;

public class CatalogOfBooks {
    private String catalogID;
    private String catalogTitle;
    private String boShelfID;
    private List<BookShelf> bookShelfID;

    private List<Books> booksList;


    //contructor
    public CatalogOfBooks() {
    }

    public CatalogOfBooks(String catalogID, String catalogTitle,String boShelfID) {
        this.catalogID = catalogID;
        this.catalogTitle = catalogTitle;
        this.boShelfID = boShelfID;
    }

    //getter and setter

    public String getCatalogID() {
        return catalogID;
    }

    public void setCatalogID(String catalogID) {
        this.catalogID = catalogID;
    }

    public String getCatalogTitle() {
        return catalogTitle;
    }

    public void setCatalogTitle(String catalogTitle) {
        this.catalogTitle = catalogTitle;
    }

    public String getBoShelfID() {
        return boShelfID;
    }

    public void setBoShelfID(String boShelfID) {
        this.boShelfID = boShelfID;
    }
}