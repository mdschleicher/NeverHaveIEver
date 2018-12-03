package edu.csula.cs.neverhaveiever.models;


public class Game {
    private String name;
    private String joinCode;


    public Game(String name, String joinCode) {

        this.name = name;
        this.joinCode = joinCode;
    }

    public Game() {}


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
