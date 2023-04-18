package model;

import java.util.List;

public class Department {
    private String depID;
    private String depName;
    private String depStatus;
    private String description;
    private List<Employee> employeeList;

    //contructor


    public Department() {
    }

    public Department(String depID, String depName, String description, String depStatus) {
        this.depID = depID;
        this.depName = depName;
        this.description = description;
        this.depStatus = depStatus;
    }

    //get and set

    public String getDepID() {
        return depID;
    }

    public void setDepID(String depID) {
        this.depID = depID;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepStatus() {
        return depStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDepStatus(String depStatus) {
        this.depStatus = depStatus;
    }
}
