package model;

import java.util.Date;

public class Book_view {
    private String book_ID;
    private String catalog_ID;
    private String author_ID;
    private String publisher_ID;
    private String book_Title;
    private String book_Price;
    private int book_Amount;
    private String book_BarCode;
    private String book_Img;
    private String emp_ID;
    private Date date_Add;
    private String acTivity;

    //contructor

    public Book_view() {
    }

    public Book_view(String book_ID, String catalog_ID,
                     String author_ID, String publisher_ID,
                     String book_Title, String book_Price,
                     int book_Amount, String book_BarCode,
                     String book_Img, String emp_ID,
                     Date date_Add, String acTivity) {
        this.book_ID = book_ID;
        this.catalog_ID = catalog_ID;
        this.author_ID = author_ID;
        this.publisher_ID = publisher_ID;
        this.book_Title = book_Title;
        this.book_Price = book_Price;
        this.book_Amount = book_Amount;
        this.book_BarCode = book_BarCode;
        this.book_Img = book_Img;
        this.emp_ID = emp_ID;
        this.date_Add = date_Add;
        this.acTivity = acTivity;
    }

    //getter and setter

    public String getBook_ID() {
        return book_ID;
    }

    public void setBook_ID(String book_ID) {
        this.book_ID = book_ID;
    }

    public String getCatalog_ID() {
        return catalog_ID;
    }

    public void setCatalog_ID(String catalog_ID) {
        this.catalog_ID = catalog_ID;
    }

    public String getAuthor_ID() {
        return author_ID;
    }

    public void setAuthor_ID(String author_ID) {
        this.author_ID = author_ID;
    }

    public String getPublisher_ID() {
        return publisher_ID;
    }

    public void setPublisher_ID(String publisher_ID) {
        this.publisher_ID = publisher_ID;
    }

    public String getBook_Title() {
        return book_Title;
    }

    public void setBook_Title(String book_Title) {
        this.book_Title = book_Title;
    }

    public String getBook_Price() {
        return book_Price;
    }

    public void setBook_Price(String book_Price) {
        this.book_Price = book_Price;
    }

    public int getBook_Amount() {
        return book_Amount;
    }

    public void setBook_Amount(int book_Amount) {
        this.book_Amount = book_Amount;
    }

    public String getBook_BarCode() {
        return book_BarCode;
    }

    public void setBook_BarCode(String book_BarCode) {
        this.book_BarCode = book_BarCode;
    }

    public String getBook_Img() {
        return book_Img;
    }

    public void setBook_Img(String book_Img) {
        this.book_Img = book_Img;
    }

    public String getEmp_ID() {
        return emp_ID;
    }

    public void setEmp_ID(String emp_ID) {
        this.emp_ID = emp_ID;
    }

    public java.sql.Date getDate_Add() {
        return (java.sql.Date) date_Add;
    }

    public void setDate_Add(Date date_Add) {
        this.date_Add = date_Add;
    }

    public String getAcTivity() {
        return acTivity;
    }

    public void setAcTivity(String acTivity) {
        this.acTivity = acTivity;
    }
}
