package model;

import org.w3c.dom.Node;

import java.time.LocalDate;
import java.util.Date;

public class Book {
    private int index;
    private String barcode;
    private String title;
    private LocalDate renewalDate;
    private String borrowedDate;
    private String returnedDate;
    private String state;

    public Book(){}

    public Book(int index, String barcode, String title, LocalDate renewalDate,
                String borrowedDate, String returnedDate, String state) {
        this.index = index;
        this.barcode = barcode;
        this.title = title;
        this.renewalDate = renewalDate;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
        this.state = state;
    }

    public LocalDate getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(LocalDate renewalDate) {
        this.renewalDate = renewalDate;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBorrowedDate(String borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public void setReturnedDate(String returnedDate) {
        this.returnedDate = returnedDate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getIndex() {
        return index;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getTitle() {
        return title;
    }

    public String getBorrowedDate() {
        return borrowedDate;
    }

    public String getReturnedDate() {
        return returnedDate;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Book{" +
                "index=" + index +
                ", barcode='" + barcode + '\'' +
                ", title='" + title + '\'' +
                ", borrowedDate='" + borrowedDate + '\'' +
                ", returnedDate='" + returnedDate + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
