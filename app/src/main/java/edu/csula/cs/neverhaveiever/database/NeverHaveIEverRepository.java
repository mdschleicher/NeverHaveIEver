package edu.csula.cs.neverhaveiever.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import edu.csula.cs.neverhaveiever.models.Game;
import edu.csula.cs.neverhaveiever.models.Question;
import edu.csula.cs.neverhaveiever.models.Response;
import edu.csula.cs.neverhaveiever.models.User;

public class NeverHaveIEverRepository {
    private GameDao mGameDao;
    private QuestionDao mQuestionDao;
    private ResponseDao mResponseDao;
    private UserDao mUserDao;
    private UserGameJoinDao mUserGameJoinDao;

    private DatabaseReference databaseReference;

    public NeverHaveIEverRepository(Application application){
        NeverHaveIEverDatabase db = NeverHaveIEverDatabase.getDatabase(application);
        mGameDao = db.gameDao();
        mQuestionDao = db.questionDao();
        mResponseDao = db.responseDao();
        mUserDao = db.userDao();
        mUserGameJoinDao = db.userGameJoinDao();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    LiveData<List<Game>> getAllGamesForUser(int userID) {
        return mUserGameJoinDao.loadGameInfoForUser(userID);
    }

    LiveData<List<User>> getAllUserForGame(int gameID) {
        return mUserGameJoinDao.loadUserInfoForGame(gameID);
    }

    LiveData<List<Question>> getAllQuestionForGame(int gameID) {
        return mQuestionDao.loadQuestionsForGame(gameID);
    }

    LiveData<List<Response>> getAllResponseForQuestion(int questionID) {
        return mResponseDao.loadResponsesForQuestion(questionID);
    }

    LiveData<List<User>> getAllUsers() {
        return mUserDao.loadAllUsers();
    }

    //Add async task here to udpate the room from the remote db

    public void CreateUser(String name, String photo) {


        new CreateUserTask(databaseReference, name, photo).execute(mUserDao);

    }


    public static class CreateUserTask extends AsyncTask<UserDao, Void, Void> {

        private  DatabaseReference db;
        private String name;
        private String photo;

        CreateUserTask(DatabaseReference db, String name, String photo) {
            this.db = db;
            this.photo = photo;
            this.name = name;
        }

        @Override
        protected Void doInBackground(UserDao... params) {
            String key = db.push().getKey();
            User user = new User(key ,this.name, this.photo);
            db.child("users").child(key).setValue(user);
            params[0].insertUser(user);
            return null;
        }
    }
}
