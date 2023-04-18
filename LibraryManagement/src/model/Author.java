package model;

import java.util.List;

public class Author {
    private String authorID;
    private String authorName;
    private String authorSubject;
    private String description;
    private List<Books> booksList;

    //contructor


    public Author() {
    }

    public Author(String authorID, String authorName, String authorSubject, String description) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.authorSubject = authorSubject;
        this.description = description;
    }

    //get and set

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSubject() {
        return authorSubject;
    }

    public void setAuthorSubject(String authorSubject) {
        this.authorSubject = authorSubject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
