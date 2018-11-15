package edu.csula.cs.neverhaveiever.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import edu.csula.cs.neverhaveiever.models.Game;
import edu.csula.cs.neverhaveiever.models.Question;
import edu.csula.cs.neverhaveiever.models.Response;
import edu.csula.cs.neverhaveiever.models.User;
import edu.csula.cs.neverhaveiever.models.UserGameJoin;

@Database(entities = {Game.class, Question.class, Response.class, User.class, UserGameJoin.class}, version = 1)
public abstract class NeverHaveIEverDatabase extends RoomDatabase {

    public abstract GameDao gameDao();
    public abstract QuestionDao questionDao();
    public abstract ResponseDao responseDao();
    public abstract UserDao userDao();
    public abstract UserGameJoinDao userGameJoinDao();

    private static volatile NeverHaveIEverDatabase INSTANCE;

    public static  NeverHaveIEverDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized ((NeverHaveIEverDatabase.class)) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NeverHaveIEverDatabase.class, "never_have_i_ever").build();
                }
            }
        }
        return INSTANCE;
    }


}
