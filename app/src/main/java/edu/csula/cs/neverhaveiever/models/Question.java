package edu.csula.cs.neverhaveiever.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Game.class,
                                            parentColumns = "id",
                                            childColumns = "gameId",
                                            onDelete = CASCADE))
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;
    private int gameId;

    public Question() {

    }

    public Question(String text, int gameId) {
        this.text = text;
        this.gameId = gameId;
    }

    public Question(int id, String text, int gameId) {
        this.id = id;
        this.text = text;
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
