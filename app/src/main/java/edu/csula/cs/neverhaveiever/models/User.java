package edu.csula.cs.neverhaveiever.models;

public class User {

    private String id;
    private String name;
    private String photoFilename;


    public User(String id, String name, String photoFilename) {
        this.id = id;
        this.name = name;
        this.photoFilename = photoFilename;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoFilename() {
        return photoFilename;
    }

    public void setPhotoFilename(String photoFilename) {
        this.photoFilename = photoFilename;
    }
}
