package model;

import java.util.List;

public class Publisher {
    private String publisherID;
    private String publisherName;
    private String publisherPhone;
    private String publisherAddress;
    private List<Books> booksList;

    //contructor


    public Publisher() {
    }

    public Publisher(String publisherID, String publisherName, String publisherPhone, String publisherAddress) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.publisherPhone = publisherPhone;
        this.publisherAddress = publisherAddress;
    }


    //get and set


    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }
}


