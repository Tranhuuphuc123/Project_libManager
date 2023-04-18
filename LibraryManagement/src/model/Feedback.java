package model;

import java.security.KeyStore;
import java.time.LocalTime;
import java.util.List;

public class Feedback {
    private String feedID;
    private List<Reader> readerList;
    private String typeOfFeed;
    private String contentOfFeed;
    private String contentDetailsOfFeed;
    private LocalTime sendingTime;
    private String statusFeed;

    public Feedback(String feedID, List<Reader> readerList, String typeOfFeed, String contentOfFeed,
                    String contentDetailsOfFeed, LocalTime sendingTime, String statusFeed) {
        this.feedID = feedID;
        this.readerList = readerList;
        this.typeOfFeed = typeOfFeed;
        this.contentOfFeed = contentOfFeed;
        this.contentDetailsOfFeed = contentDetailsOfFeed;
        this.sendingTime = sendingTime;
        this.statusFeed = statusFeed;
    }

    public String getFeedID() {
        return feedID;
    }

    public void setFeedID(String feedID) {
        this.feedID = feedID;
    }

    public List<Reader> getReaderList() {
        return readerList;
    }

    public void setReaderList(List<Reader> readerList) {
        this.readerList = readerList;
    }

    public String getTypeOfFeed() {
        return typeOfFeed;
    }

    public void setTypeOfFeed(String typeOfFeed) {
        this.typeOfFeed = typeOfFeed;
    }

    public String getContentOfFeed() {
        return contentOfFeed;
    }

    public void setContentOfFeed(String contentOfFeed) {
        this.contentOfFeed = contentOfFeed;
    }

    public String getContentDetailsOfFeed() {
        return contentDetailsOfFeed;
    }

    public void setContentDetailsOfFeed(String contentDetailsOfFeed) {
        this.contentDetailsOfFeed = contentDetailsOfFeed;
    }

    public LocalTime getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(LocalTime sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getStatusFeed() {
        return statusFeed;
    }

    public void setStatusFeed(String statusFeed) {
        this.statusFeed = statusFeed;
    }
}
