package model;

import java.util.Date;

public class BorrowAndReturnBook {
    private String bookID;
    private String readerID;
    private Date startDate;
    private Date retdate;
    private String borrStatus;


    //contructor

    public BorrowAndReturnBook() {
    }

    public BorrowAndReturnBook(String bookID, String readerID, Date startDate, Date retdate, String borrStatus) {
        this.bookID = bookID;
        this.readerID = readerID;
        this.startDate = startDate;
        this.retdate = retdate;
        this.borrStatus = borrStatus;
    }

    //get and set

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getRetdate() {
        return retdate;
    }

    public void setRetdate(Date retdate) {
        this.retdate = retdate;
    }

    public String getBorrStatus() {
        return borrStatus;
    }

    public void setBorrStatus(String borrStatus) {
        this.borrStatus = borrStatus;
    }

    // checked button
    boolean isUpdated = false;

    public void setIsUpdated(boolean updated){
        this.isUpdated = updated;
    }

    public boolean getIsUpdated(){
        return isUpdated;
    }


}

