package model;

import java.util.Date;

public class ManagesBook {
    private String empID;
    private  String bookID;
    private Date dateAdd;
    private String activity;


    //contructor

    public ManagesBook(String empID, String bookID, Date dateAdd, String activity) {
        this.empID = empID;
        this.bookID = bookID;
        this.dateAdd = dateAdd;
        this.activity = activity;
    }

    //get and set

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}


