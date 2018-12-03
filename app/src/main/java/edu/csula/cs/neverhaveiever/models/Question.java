package edu.csula.cs.neverhaveiever.models;


public class Question {
    private String id;
    private String question;
    private String access_code;
    private String userId;

    public Question() {

    }

    public Question(String id, String question, String access_code, String userId) {
        this.id = id;
        this.question = question;
        this.access_code = access_code;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAccess_code() {
        return access_code;
    }

    public String getUserId() {
        return userId;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAccess_code(String access_code) {
        this.access_code = access_code;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
