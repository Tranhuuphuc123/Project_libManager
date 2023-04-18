package model;

import java.util.List;

public class DiscussionRoom {
    private String disID;
    private String disName;
    private String disLocation;
    private String description;
    private String disStatus;
    private List<Reader> readers;

    //contructor


    public DiscussionRoom() {
    }

    public DiscussionRoom(String disID, String disName, String disLocation,
                          String description, String disStatus) {
        this.disID = disID;
        this.disName = disName;
        this.disLocation = disLocation;
        this.description = description;
        this.disStatus = disStatus;
    }


    //get and set

    public String getDisID() {
        return disID;
    }

    public void setDisID(String disID) {
        this.disID = disID;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getDisLocation() {
        return disLocation;
    }

    public void setDisLocation(String disLocation) {
        this.disLocation = disLocation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getDisStatus() {
        return disStatus;
    }

    public void setDisStatus(String disStatus) {
        this.disStatus = disStatus;
    }

    @Override
    public String toString() {
        return disName ;
    }


}
