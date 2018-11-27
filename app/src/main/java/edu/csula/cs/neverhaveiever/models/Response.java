package edu.csula.cs.neverhaveiever.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Question.class,
                                           parentColumns = "id",
                                           childColumns = "questionId",
                                           onDelete = CASCADE))
public class Response {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private boolean response;
    private int questionId;

    @Ignore
    public Response() {
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Ignore
    public Response(boolean response, int questionId) {
        this.response = response;
        this.questionId = questionId;
    }

    public Response(int id, boolean response, int questionId) {
        this.id = id;
        this.response = response;
        this.questionId = questionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public int getQuestionId() { return questionId; }
}
