package edu.csula.cs.neverhaveiever.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Game {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String joinCode;

    public Game() {

    }

    public Game(String name, String joinCode) {
        this.name = name;
        this.joinCode = joinCode;
    }

    public Game(int id, String name, String joinCode) {
        this.id= id;
        this.name = name;
        this.joinCode = joinCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }
}
