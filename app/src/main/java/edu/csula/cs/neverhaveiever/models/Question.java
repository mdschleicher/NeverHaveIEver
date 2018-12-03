package edu.csula.cs.neverhaveiever.models;


public class Question {
    private String id;
    private String question;
    private String access_code;
    private String userId;
    private String name;
    private  String profilePic;

    public Question() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {

        return name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public Question(String id, String question, String access_code, String userId, String name, String profilePic) {
        this.id = id;
        this.question = question;
        this.name = name;
        this.profilePic = profilePic;
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
