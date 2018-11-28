package edu.csula.cs.neverhaveiever.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {

    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String photoFilename;

    @Ignore
    public User(String name, String photoFilename) {
        this.name = name;
        this.photoFilename = photoFilename;
    }

    public User(String id, String name, String photoFilename) {
        this.id = id;
        this.name = name;
        this.photoFilename = photoFilename;
    }

    @Ignore
    public User() {

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
