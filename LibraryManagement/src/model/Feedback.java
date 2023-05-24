package model;

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

    public Feedback() {
        this.feedID = feedID;
        this.typeOfFeed = typeOfFeed;
        this.contentOfFeed = contentOfFeed;
        this.contentDetailsOfFeed = contentDetailsOfFeed;
        this.sendingTime = sendingTime;
        this.statusFeed = statusFeed;
    }

    public Feedback(String id, String type, String content, String details, LocalTime time, String statusFeed){
        this.feedID = id;
        this.typeOfFeed = type;
        this.contentOfFeed = details;
        this.contentDetailsOfFeed = details;
        this.sendingTime = time;
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
