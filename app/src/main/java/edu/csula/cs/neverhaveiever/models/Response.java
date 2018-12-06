package edu.csula.cs.neverhaveiever.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

public class Response {
    private boolean response;
    private String questionId;
    private String userPicture;
    private String userName;
    private String userId;

    public Response(boolean response, String questionId, String userPicture, String userName, String userId) {
        this.response = response;
        this.questionId = questionId;
        this.userPicture = userPicture;
        this.userName = userName;
        this.userId = userId;
    }

    public Response() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isResponse() {
        return response;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public String getUserId() {
        return userId;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
