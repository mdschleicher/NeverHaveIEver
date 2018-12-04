package edu.csula.cs.neverhaveiever.models;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;

@Entity(primaryKeys = {"userId", "gameId"},
        foreignKeys = {
                        @ForeignKey(entity = User.class,
                                                parentColumns = "id",
                                                childColumns = "userId"),

                        @ForeignKey(entity = Game.class,
                                                parentColumns = "id",
                                                childColumns = "gameId")
        })
public class UserGameJoin {
    private int userId;
    private int gameId;

    @Ignore
    public UserGameJoin() {

    }

    public UserGameJoin(int userId, int gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
