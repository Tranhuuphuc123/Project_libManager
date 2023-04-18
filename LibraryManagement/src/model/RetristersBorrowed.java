package model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class RetristersBorrowed {
    private String disID;
    private String readerID;
    private String borrowedByShift;
    private Date disDate;
    private String description;
    private String activity;

    //contructor


    public RetristersBorrowed() {
    }

    public RetristersBorrowed(String disID, String readerID, String borrowedByShift,
                              Date disDate, String description, String activity) {
        this.disID = disID;
        this.readerID = readerID;
        this.borrowedByShift = borrowedByShift;
        this.disDate = disDate;
        this.description = description;
        this.activity = activity;
    }


    //get and set

    public String getDisID() {
        return disID;
    }

    public void setDisID(String disID) {
        this.disID = disID;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public String getBorrowedByShift() {
        return borrowedByShift;
    }

    public void setBorrowedByShift(String borrowedByShift) {
        this.borrowedByShift = borrowedByShift;
    }

    public Date getDisDate() {
        return disDate;
    }

    public void setDisDate(Date disDate) {
        this.disDate = disDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
