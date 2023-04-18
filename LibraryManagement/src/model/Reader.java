package model;

import java.util.Date;
import java.util.List;

public class Reader {
    private String readerID;
    private String readerName;
    private Date birth;
    private String readerGender;
    private String readerAddress;
    private String readerPhone;
    private String readerBarcode;
    private Date dateCreated;
    private Date outOfDate;

    private String readerPass;
    private String readerImg;

    private String readerStatus;

    private List<Employee> employees;
    private DiscussionRoom discussionRoom;
    private Feedback feedback;

    //contructor

    public Reader() {
    }

    public Reader(String readerID, String readerName, Date birth, String readerGender,
                  String readerAddress, String readerPhone, String readerBarcode, Date dateCreated,
                  Date outOfDate, String readerPass, String readerImg, String readerStatus) {
        this.readerID = readerID;
        this.readerName = readerName;
        this.birth = birth;
        this.readerGender = readerGender;
        this.readerAddress = readerAddress;
        this.readerPhone = readerPhone;
        this.readerBarcode = readerBarcode;
        this.dateCreated = dateCreated;
        this.outOfDate = outOfDate;
        this.readerPass = readerPass;
        this.readerImg = readerImg;
        this.readerStatus = readerStatus;
    }

    //get and set


    public String getReaderID() {
        return readerID;
    }

    public void setReaderID(String readerID) {
        this.readerID = readerID;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public java.sql.Date getBirth() {
        return (java.sql.Date) birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getReaderGender() {
        return readerGender;
    }

    public void setReaderGender(String readerGender) {
        this.readerGender = readerGender;
    }

    public String getReaderAddress() {
        return readerAddress;
    }

    public void setReaderAddress(String readerAddress) {
        this.readerAddress = readerAddress;
    }

    public String getReaderPhone() {
        return readerPhone;
    }

    public void setReaderPhone(String readerPhone) {
        this.readerPhone = readerPhone;
    }

    public String getReaderBarcode() {
        return readerBarcode;
    }

    public void setReaderBarcode(String readerBarcode) {
        this.readerBarcode = readerBarcode;
    }

    public java.sql.Date getDateCreated() {
        return (java.sql.Date) dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public java.sql.Date getOutOfDate() {
        return (java.sql.Date) outOfDate;
    }

    public void setOutOfDate(Date outOfDate) {
        this.outOfDate = outOfDate;
    }

    public String getReaderPass() {
        return readerPass;
    }

    public void setReaderPass(String readerPass) {
        this.readerPass = readerPass;
    }

    public String getReaderImg() {
        return readerImg;
    }

    public void setReaderImg(String readerImg) {
        this.readerImg = readerImg;
    }

    public String getReaderStatus() {
        return readerStatus;
    }

    public void setReaderStatus(String readerStatus) {
        this.readerStatus = readerStatus;
    }
}
