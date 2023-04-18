package model;

import java.time.LocalDate;
import java.util.List;

public class Books {
    private String bookID;
    private String bookTitle;
    private String bookPrice;
    private int  bookAmount;
    private Author author;
    private Publisher publisher;
    private CatalogOfBooks catalogOfBooks;
    private String bookBarCode;
    private String bookImg;
    private List<Employee> employeeList;

    //Constructor
    public Books() {}

    public Books(String bookID, String bookTitle, String bookPrice, int bookAmount, Author author,
                 Publisher publisher, CatalogOfBooks catalog, String bookBarCode, String bookImg) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.bookPrice = bookPrice;
        this.bookAmount = bookAmount;
        this.author = author;
        this.publisher = publisher;
        this.catalogOfBooks = catalog;
        this.bookBarCode = bookBarCode;
        this.bookImg = bookImg;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(int bookAmount) {
        this.bookAmount = bookAmount;
    }

    public Author getAuthorID() {
        return author;
    }

    public void setAuthorID(Author authorID) {
        this.author = authorID;
    }

    public Publisher getPublisherID() {
        return publisher;
    }

    public void setPublisherID(Publisher publisherID) {
        this.publisher = publisherID;
    }

    public CatalogOfBooks getCatalogID() {
        return catalogOfBooks;
    }

    public void setCatalogID(CatalogOfBooks catalogID) {
        this.catalogOfBooks = catalogID;
    }

    public String getBookBarCode() {
        return bookBarCode;
    }

    public void setBookBarCode(String bookBarCode) {
        this.bookBarCode = bookBarCode;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }
}
